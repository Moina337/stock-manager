package gestion_produit_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import gestion_produit_crud.api.AuthApi;
import gestion_produit_crud.dto.LoginRequestDTO;
import gestion_produit_crud.dto.LoginResponseDTO;
import gestion_produit_crud.dto.UtilisateurDTO;
import gestion_produit_crud.services.UtilisateurService;
import gestion_produit_crud.services.UtilisateurServiceImp;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class AuthController implements AuthApi  {
	
	@Autowired
	UtilisateurService imp;
	

	@Override
	public ResponseEntity<LoginResponseDTO> _login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		
		LoginResponseDTO dto =imp.login(loginRequestDTO);
		
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<UtilisateurDTO> _registerUser(@Valid @RequestBody UtilisateurDTO utilisateurDTO) {
		
		UtilisateurDTO dto = imp.inscription(utilisateurDTO);
		
		return ResponseEntity.ok(dto);
	}
	
	
 
}
