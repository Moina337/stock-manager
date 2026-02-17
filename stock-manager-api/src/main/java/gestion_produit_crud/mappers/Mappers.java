package gestion_produit_crud.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import gestion_produit_crud.dto.CategorieDTO;
import gestion_produit_crud.dto.ProduitAjoutDTO;
import gestion_produit_crud.dto.ProduitDTO;
import gestion_produit_crud.dto.ProduitUpdateDTO;
import gestion_produit_crud.dto.UtilisateurDTO;
import gestion_produit_crud.entity.CategorieEntity;
import gestion_produit_crud.entity.ProduitEntity;
import gestion_produit_crud.entity.UtilisateurEntity;

@Mapper(componentModel = "spring")
public interface Mappers {

	// utilisateur mapper

	UtilisateurDTO UtilisateurEntityToDTO(UtilisateurEntity entity);// entite en dto

	UtilisateurEntity UtilisateurDTOEnTotity(UtilisateurDTO dto);

	// Categorie mapper

	CategorieDTO CategorieEntityToDTO(CategorieEntity entity);

	CategorieEntity CategorieDtoToEntity(CategorieDTO dto);

	// Produi mapper

	@Mapping(source = "categorie.id", target = "categorieId")
	ProduitDTO produitEntityToDTO(ProduitEntity entity);

	@Mapping(source = "categorieId", target = "categorie.id")
	ProduitEntity produitDtoToEntity(ProduitAjoutDTO dto);
	
	// On extrait l'ID de l'objet categorie de l'entité pour le mettre dans categorieId du DTO
	@Mapping(source = "categorie.id", target = "categorieId")
	ProduitAjoutDTO ProduiEntityToAjouDTO(ProduitEntity entity);

	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "image", ignore = true) // Empêche d'écraser l'image existante
	@Mapping(source = "categorieId", target = "categorie.id")
	void updateDtoToEntity(ProduitUpdateDTO dto, @MappingTarget ProduitEntity entity);

	 @Mapping(source = "categorie.id", target = "categorieId")
	 ProduitUpdateDTO toResponseDto(ProduitEntity entity);


}
