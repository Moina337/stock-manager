package gestion_produit_crud.services;

import java.util.List;

import gestion_produit_crud.dto.CategorieDTO;
import gestion_produit_crud.dto.CategorieUpdateDTO;

public interface CategorieService {
	
	List<CategorieDTO> listeCategorie();
	
	CategorieDTO ajouterCategorie(CategorieDTO dto); 
	
	CategorieDTO modifierCategorie(CategorieUpdateDTO categorieUpdateDTO, Integer id);
	
	CategorieDTO afficherCategorieParId(Integer id);
	
	void supprimerCategorie(Integer id);
     
}
