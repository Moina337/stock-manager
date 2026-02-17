package gestion_produit_crud.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProduitUpdateDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-16T00:19:24.782253500Z[Atlantic/Reykjavik]", comments = "Generator version: 7.19.0")
public class ProduitUpdateDTO {

  private @Nullable String nom;

  private @Nullable BigDecimal prix;

  private @Nullable Integer categorieId;

  private @Nullable String image;

  public ProduitUpdateDTO nom(@Nullable String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
   */
  
  @Schema(name = "nom", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nom")
  public @Nullable String getNom() {
    return nom;
  }

  public void setNom(@Nullable String nom) {
    this.nom = nom;
  }

  public ProduitUpdateDTO prix(@Nullable BigDecimal prix) {
    this.prix = prix;
    return this;
  }

  /**
   * Get prix
   * @return prix
   */
  @Valid 
  @Schema(name = "prix", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prix")
  public @Nullable BigDecimal getPrix() {
    return prix;
  }

  public void setPrix(@Nullable BigDecimal prix) {
    this.prix = prix;
  }

  public ProduitUpdateDTO categorieId(@Nullable Integer categorieId) {
    this.categorieId = categorieId;
    return this;
  }

  /**
   * Get categorieId
   * @return categorieId
   */
  
  @Schema(name = "categorieId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categorieId")
  public @Nullable Integer getCategorieId() {
    return categorieId;
  }

  public void setCategorieId(@Nullable Integer categorieId) {
    this.categorieId = categorieId;
  }

  public ProduitUpdateDTO image(@Nullable String image) {
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
    ProduitUpdateDTO produitUpdateDTO = (ProduitUpdateDTO) o;
    return Objects.equals(this.nom, produitUpdateDTO.nom) &&
        Objects.equals(this.prix, produitUpdateDTO.prix) &&
        Objects.equals(this.categorieId, produitUpdateDTO.categorieId) &&
        Objects.equals(this.image, produitUpdateDTO.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, prix, categorieId, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProduitUpdateDTO {\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prix: ").append(toIndentedString(prix)).append("\n");
    sb.append("    categorieId: ").append(toIndentedString(categorieId)).append("\n");
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

