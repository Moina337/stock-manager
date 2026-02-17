package gestion_produit_crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion_produit_crud.dto.ProduitAjoutDTO;
import gestion_produit_crud.dto.ProduitDTO;
import gestion_produit_crud.dto.ProduitUpdateDTO;
import gestion_produit_crud.entity.ProduitEntity;
import gestion_produit_crud.mappers.Mappers;
import gestion_produit_crud.repository.CategorieRepo;
import gestion_produit_crud.repository.ProduitRepo;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProduitServiceImpl implements ProduitServive {

	@Autowired
	ProduitRepo produitRepo;

	@Autowired
	Mappers mappers;

	@Autowired
	CategorieRepo categorieRepo;

	@Override
	public List<ProduitDTO> listeProduit() {

		List<ProduitEntity> entities = produitRepo.findAll();

		List<ProduitDTO> dto = entities.stream().map(mappers::produitEntityToDTO).toList();

		return dto;
	}

	@Override
	public ProduitDTO ajoutProduit(ProduitAjoutDTO dto, String fileName) {

		ProduitEntity entity = mappers.produitDtoToEntity(dto);

		entity.setImage(fileName);

		ProduitEntity saved = produitRepo.save(entity);

		return mappers.produitEntityToDTO(saved);
	}

	@Override
	public ProduitDTO afficherProduitParID(Integer id) {

		ProduitDTO dto = mappers.produitEntityToDTO(produitRepo.getById(id));

		return dto;
	}
	

	@Override
	public ProduitDTO modifierProduit(ProduitUpdateDTO dto, Integer id, String filName) {

		ProduitEntity entity = produitRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));
		
		

		// 1. Vérification de la catégorie
		if (dto.getCategorieId() != null && !categorieRepo.existsById(dto.getCategorieId())) {
			throw new RuntimeException("La nouvelle catégorie n'existe pas");
		}

		// 2. Gestion physique du fichier
		if (filName != null && !filName.isEmpty()) {
			// On récupère le nom de l'ancienne image
			String ancienneImage = entity.getImage();

			if (ancienneImage != null) {
				try {
					// Chemin vers le dossier physique (à adapter selon votre config)
					Path cheminFichier = Paths.get("uploads/produits").resolve(ancienneImage);
					Files.deleteIfExists(cheminFichier);
				} catch (Exception e) {
					// On log l'erreur mais on ne bloque pas la modification
					System.err.println("Erreur lors de la suppression de l'ancien fichier : " + e.getMessage());
				}
			}
			// Mise à jour du nouveau nom dans l'entité
			entity.setImage(filName);
			
			
		}

		// 3. Mise à jour des autres champs et sauvegarde
		mappers.updateDtoToEntity(dto, entity);
		
		
		return mappers.produitEntityToDTO(produitRepo.save(entity));
	}
	

	@Override
	public void supprimerProdui(Integer id) {

		ProduitEntity entity = produitRepo.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));

		// On récupère le nom de l'ancienne image
		String ancienneImage = entity.getImage();

		if (ancienneImage != null) {
			try {
				// Chemin vers le dossier physique (à adapter selon votre config)
				Path cheminFichier = Paths.get("uploads/produits").resolve(ancienneImage);
				Files.deleteIfExists(cheminFichier);
			} catch (Exception e) {
				// On log l'erreur mais on ne bloque pas la modification
				System.err.println("Erreur lors de la suppression de l'ancien fichier : " + e.getMessage());
			}
		}

		produitRepo.deleteById(id);

	}

}
