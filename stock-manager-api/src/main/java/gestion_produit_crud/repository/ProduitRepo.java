package gestion_produit_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion_produit_crud.entity.ProduitEntity;

@Repository
public interface ProduitRepo extends JpaRepository<ProduitEntity, Integer> {

}
