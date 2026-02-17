package gestion_produit_crud.services;

import java.util.List;

import gestion_produit_crud.dto.LoginRequestDTO;
import gestion_produit_crud.dto.LoginResponseDTO;
import gestion_produit_crud.dto.UtilisateurDTO;
import gestion_produit_crud.dto.UtilisateurUpdateDTO;

public interface UtilisateurService {
	
	UtilisateurDTO inscription(UtilisateurDTO dto);
	
	LoginResponseDTO login(LoginRequestDTO dto);
	
	List<UtilisateurDTO> listeUtilisateur();
	
	UtilisateurDTO AfficherUtilisateurParId(Integer id);
	
	UtilisateurDTO modifierUtilisateur(UtilisateurUpdateDTO dto, Integer id);
	
	void supprimerUtilisateur(Integer id);

}
