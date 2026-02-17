package gestion_produit_crud.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UtilisateurUpdateDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-16T00:19:24.782253500Z[Atlantic/Reykjavik]", comments = "Generator version: 7.19.0")
public class UtilisateurUpdateDTO {

  private String nom;

  private String email;

  public UtilisateurUpdateDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UtilisateurUpdateDTO(String nom, String email) {
    this.nom = nom;
    this.email = email;
  }

  public UtilisateurUpdateDTO nom(String nom) {
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

  public UtilisateurUpdateDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @NotNull @jakarta.validation.constraints.Email 
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtilisateurUpdateDTO utilisateurUpdateDTO = (UtilisateurUpdateDTO) o;
    return Objects.equals(this.nom, utilisateurUpdateDTO.nom) &&
        Objects.equals(this.email, utilisateurUpdateDTO.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nom, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtilisateurUpdateDTO {\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

