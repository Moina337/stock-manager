package gestion_produit_crud.services;

import java.util.List;

import gestion_produit_crud.dto.ProduitAjoutDTO;
import gestion_produit_crud.dto.ProduitDTO;
import gestion_produit_crud.dto.ProduitUpdateDTO;

public interface ProduitServive {
	
	List<ProduitDTO> listeProduit();
	
	ProduitDTO ajoutProduit(ProduitAjoutDTO dto, String filName);
	
	ProduitDTO afficherProduitParID(Integer id);
	
	ProduitDTO modifierProduit(ProduitUpdateDTO dto, Integer id, String filName);
	
	void supprimerProdui(Integer id);

}
 