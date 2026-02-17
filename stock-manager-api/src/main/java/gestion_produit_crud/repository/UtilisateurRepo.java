package gestion_produit_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion_produit_crud.entity.UtilisateurEntity;

public interface UtilisateurRepo extends JpaRepository<UtilisateurEntity, Integer> {
	
	UtilisateurEntity findByEmail(String email);
	boolean existsByEmail(String email);

}
