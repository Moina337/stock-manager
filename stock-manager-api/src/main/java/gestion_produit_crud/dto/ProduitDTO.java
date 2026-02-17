package gestion_produit_crud.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import gestion_produit_crud.dto.CategorieDTO;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProduitDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-16T00:19:24.782253500Z[Atlantic/Reykjavik]", comments = "Generator version: 7.19.0")
public class ProduitDTO {

  private @Nullable Integer id;

  private String nom;

  private Double prix;

  private Integer categorieId;

  private @Nullable CategorieDTO categorie;

  private @Nullable String image;

  public ProduitDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProduitDTO(String nom, Double prix, Integer categorieId) {
    this.nom = nom;
    this.prix = prix;
    this.categorieId = categorieId;
  }

  public ProduitDTO id(@Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable Integer getId() {
    return id;
  }

  public void setId(@Nullable Integer id) {
    this.id = id;
  }

  public ProduitDTO nom(String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
   */
  @NotNull 
  @Schema(name = "nom", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nom")
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public ProduitDTO prix(Double prix) {
    this.prix = prix;
    return this;
  }

  /**
   * Get prix
   * @return prix
   */
  @NotNull 
  @Schema(name = "prix", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prix")
  public Double getPrix() {
    return prix;
  }

  public void setPrix(Double prix) {
    this.prix = prix;
  }

  public ProduitDTO categorieId(Integer categorieId) {
    this.categorieId = categorieId;
    return this;
  }

  /**
   * Get categorieId
   * @return categorieId
   */
  @NotNull 
  @Schema(name = "categorieId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categorieId")
  public Integer getCategorieId() {
    return categorieId;
  }

  public void setCategorieId(Integer categorieId) {
    this.categorieId = categorieId;
  }

  public ProduitDTO categorie(@Nullable CategorieDTO categorie) {
    this.categorie = categorie;
    return this;
  }

  /**
   * Get categorie
   * @return categorie
   */
  @Valid 
  @Schema(name = "categorie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categorie")
  public @Nullable CategorieDTO getCategorie() {
    return categorie;
  }

  public void setCategorie(@Nullable CategorieDTO categorie) {
    this.categorie = categorie;
  }

  public ProduitDTO image(@Nullable String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   */
  
  @Schema(name = "image", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("image")
  public @Nullable String getImage() {
    return image;
  }

  public void setImage(@Nullable String image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProduitDTO produitDTO = (ProduitDTO) o;
    return Objects.equals(this.id, produitDTO.id) &&
        Objects.equals(this.nom, produitDTO.nom) &&
        Objects.equals(this.prix, produitDTO.prix) &&
        Objects.equals(this.categorieId, produitDTO.categorieId) &&
        Objects.equals(this.categorie, produitDTO.categorie) &&
        Objects.equals(this.image, produitDTO.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, prix, categorieId, categorie, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProduitDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prix: ").append(toIndentedString(prix)).append("\n");
    sb.append("    categorieId: ").append(toIndentedString(categorieId)).append("\n");
    sb.append("    categorie: ").append(toIndentedString(categorie)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

