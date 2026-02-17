package gestion_produit_crud.entity;

import org.springframework.lang.Nullable;

import gestion_produit_crud.dto.CategorieDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produits")
public class ProduitEntity {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private @Nullable Integer id;

	  private String nom;

	  private Double prix;
      
	  @ManyToOne
	  @JoinColumn(name = "categorieId")
	  private CategorieEntity categorie;

	  private @Nullable String image;

}
