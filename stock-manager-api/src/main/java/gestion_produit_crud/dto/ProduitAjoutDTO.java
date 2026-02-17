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
 * ProduitAjoutDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-16T00:19:24.782253500Z[Atlantic/Reykjavik]", comments = "Generator version: 7.19.0")
public class ProduitAjoutDTO {

  private String nom;

  private BigDecimal prix;

  private Integer categorieId;

  public ProduitAjoutDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProduitAjoutDTO(String nom, BigDecimal prix, Integer categorieId) {
    this.nom = nom;
    this.prix = prix;
    this.categorieId = categorieId;
  }

  public ProduitAjoutDTO nom(String nom) {
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

  public ProduitAjoutDTO prix(BigDecimal prix) {
    this.prix = prix;
    return this;
  }

  /**
   * Get prix
   * @return prix
   */
  @NotNull @Valid 
  @Schema(name = "prix", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prix")
  public BigDecimal getPrix() {
    return prix;
  }

  public void setPrix(BigDecimal prix) {
    this.prix = prix;
  }

  public ProduitAjoutDTO categorieId(Integer categorieId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProduitAjoutDTO produitAjoutDTO = (ProduitAjoutDTO) o;
    return Objects.equals(this.nom, produitAjoutDTO.nom) &&
        Objects.equals(this.prix, produitAjoutDTO.prix) &&
        Objects.equals(this.categorieId, produitAjoutDTO.categorieId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, prix, categorieId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProduitAjoutDTO {\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    prix: ").append(toIndentedString(prix)).append("\n");
    sb.append("    categorieId: ").append(toIndentedString(categorieId)).append("\n");
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

