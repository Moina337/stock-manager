package gestion_produit_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import gestion_produit_crud.api.UtilisateurApi;
import gestion_produit_crud.dto.UtilisateurDTO;
import gestion_produit_crud.dto.UtilisateurUpdateDTO;
import gestion_produit_crud.services.UtilisateurServiceImp;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class UtilisateurController implements UtilisateurApi {
	
	@Autowired
	UtilisateurServiceImp serviceImp;

	@Override
	public ResponseEntity<Void> _deleteUtilisateur(@NotNull Integer id) {
		
		serviceImp.supprimerUtilisateur(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UtilisateurDTO> _getUtilisateurById(@NotNull Integer id) {
		
		UtilisateurDTO dto = serviceImp.AfficherUtilisateurParId(id);
		
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<List<UtilisateurDTO>> _listeUtilisateur() {
		
		List<UtilisateurDTO> dtos = serviceImp.listeUtilisateur();
		
		
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<UtilisateurDTO> _modifierUtilisateur(@NotNull Integer id,
			@Valid @RequestBody UtilisateurUpdateDTO utilisateurUpdateDTO) {
		
		UtilisateurDTO dto = serviceImp.modifierUtilisateur(utilisateurUpdateDTO, id);
		
		return ResponseEntity.ok(dto);
	}
	
	

}
