package gestion_produit_crud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gestion_produit_crud.api.ProduitsApi;
import gestion_produit_crud.dto.ProduitAjoutDTO;
import gestion_produit_crud.dto.ProduitDTO;
import gestion_produit_crud.dto.ProduitUpdateDTO;
import gestion_produit_crud.services.ProduitServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;




@RestController
public class ProduitController implements ProduitsApi {

	@Autowired
	ProduitServiceImpl impl;

	@Override
	public ResponseEntity<ProduitDTO> _afficherProduitParId(@NotNull Integer id) {

		ProduitDTO dto = impl.afficherProduitParID(id);

		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<ProduitDTO> _ajouterProduit(
			@RequestPart("produitAjoutDTO") @Valid ProduitAjoutDTO produitAjoutDTO,
			@RequestPart("image") MultipartFile image) {
		// 1. Dossier pour stocker les images
		Path root = Paths.get("uploads/produits");

		try {
			// 2. Créer le dossier si inexistant
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			// 3. Générer un nom unique pour l’image
			String originalFilename = image.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + extension;

			// 4. Sauvegarder le fichier sur le disque
			Files.copy(image.getInputStream(), root.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

			// 5. Appeler le service pour créer le produit en base
			ProduitDTO produitCree = impl.ajoutProduit(produitAjoutDTO, newFileName);

			return new ResponseEntity<>(produitCree, HttpStatus.CREATED);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<ProduitDTO> _modifierProduit(@NotNull Integer id,
			@RequestPart("produitUpdateDTO") @Valid ProduitUpdateDTO produitUpdateDTO,
			@RequestPart(value = "image", required = false) MultipartFile image) {

		String newFileName = null;

		// 1. On ne traite l'image que si l'utilisateur en a choisi une nouvelle
		if (image != null && !image.isEmpty()) {
			try {
				// Générer un nom unique pour éviter les doublons
				String originalFilename = image.getOriginalFilename();
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
				newFileName = UUID.randomUUID().toString() + extension;

				// Sauvegarder le fichier physiquement
				Path targetPath = Paths.get("uploads/produits").resolve(newFileName);
				Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException("Erreur lors de la sauvegarde de l'image", e);
			}
		}

		// 2. On appelle le service. Si newFileName est null, le service gardera
		// l'ancienne image.
		ProduitDTO result = impl.modifierProduit(produitUpdateDTO, id, newFileName);

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<List<ProduitDTO>> _listrTousProduits() {

		List<ProduitDTO> dtos = impl.listeProduit();

		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<Void> _supprimerProduit(@NotNull Integer id) {

		impl.supprimerProdui(id);

		return new ResponseEntity(HttpStatus.OK);
	}

}
