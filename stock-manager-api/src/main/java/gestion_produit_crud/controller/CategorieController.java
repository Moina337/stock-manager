package gestion_produit_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion_produit_crud.api.CategoriesApi;
import gestion_produit_crud.dto.CategorieDTO;
import gestion_produit_crud.dto.CategorieUpdateDTO;
import gestion_produit_crud.services.CategorieServiceImp;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class CategorieController implements CategoriesApi {
	
	@Autowired
	CategorieServiceImp categorieServiceImp;

	@Override
	public ResponseEntity<CategorieDTO> _ajoutCategorie(@RequestBody CategorieDTO categorieDTO) {
		
		 categorieServiceImp.ajouterCategorie(categorieDTO);
		
		return new ResponseEntity<CategorieDTO>(HttpStatus.CREATED);
	}
	
	

	@Override
	public ResponseEntity<List<CategorieDTO>> _listCategories() {
		
		List<CategorieDTO> list = categorieServiceImp.listeCategorie();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}



	@Override
	public ResponseEntity<CategorieDTO> _afficheCategorieParId(@NotNull Integer id) {
		
		CategorieDTO dto = categorieServiceImp.afficherCategorieParId(id);
		
		return ResponseEntity.ok(dto);
	}



	@Override
	public ResponseEntity<CategorieDTO> _modifierCategorie(@NotNull Integer id,
			@Valid @RequestBody CategorieUpdateDTO categorieUpdateDTO) {
		
		CategorieDTO dto = categorieServiceImp.modifierCategorie(categorieUpdateDTO, id);
		
		return ResponseEntity.ok(dto);
	}



	@Override
	public ResponseEntity<Void> _supprimerCategorie(@NotNull Integer id) {
		
		categorieServiceImp.supprimerCategorie(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	

}
