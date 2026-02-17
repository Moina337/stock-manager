package gestion_produit_crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion_produit_crud.dto.CategorieDTO;
import gestion_produit_crud.dto.CategorieUpdateDTO;
import gestion_produit_crud.entity.CategorieEntity;
import gestion_produit_crud.mappers.Mappers;
import gestion_produit_crud.repository.CategorieRepo;

@Service
public class CategorieServiceImp implements CategorieService {
	
	@Autowired
	CategorieRepo categorieRepo;
	
	@Autowired
	Mappers mappers;

	@Override
	public List<CategorieDTO> listeCategorie() {
		
		List<CategorieEntity> entity = categorieRepo.findAll();
		
		List<CategorieDTO> categories = entity.stream().map(mappers::CategorieEntityToDTO).toList();
		
		return categories;
	}

	

	@Override
	public CategorieDTO ajouterCategorie(CategorieDTO dto) {
		
		CategorieEntity entity= mappers.CategorieDtoToEntity(dto);
		
		CategorieEntity saved = categorieRepo.save(entity);
		
		return mappers.CategorieEntityToDTO(saved) ;
	}

	@Override
	public CategorieDTO modifierCategorie(CategorieUpdateDTO categorieUpdateDTO, Integer id) {
	 
		CategorieEntity entity = categorieRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("categorie non trouvé !") );
		
		entity.setNom(categorieUpdateDTO.getNom());
		
		return mappers.CategorieEntityToDTO(categorieRepo.save(entity));
	}

	@Override
	public CategorieDTO afficherCategorieParId(Integer id) {
		
		CategorieEntity entity = categorieRepo.getById(id);
		
		return mappers.CategorieEntityToDTO(entity) ;
		
	}

	@Override
	public void supprimerCategorie(Integer id) {
		
		categorieRepo.deleteById(id);
		
	}
	
	
	
	

}
