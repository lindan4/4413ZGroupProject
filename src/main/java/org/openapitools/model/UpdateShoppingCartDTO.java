package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateShoppingCartDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-01T17:41:52.282094-05:00[America/Toronto]")

public class UpdateShoppingCartDTO   {
  @JsonProperty("shoppingCart")
  @Valid
  private List<String> shoppingCart = new ArrayList<>();

  public UpdateShoppingCartDTO shoppingCart(List<String> shoppingCart) {
    this.shoppingCart = shoppingCart;
    return this;
  }

  public UpdateShoppingCartDTO addShoppingCartItem(String shoppingCartItem) {
    this.shoppingCart.add(shoppingCartItem);
    return this;
  }

  /**
   * List of book ids
   * @return shoppingCart
  */
  @ApiModelProperty(required = true, value = "List of book ids")
  @NotNull


  public List<String> getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(List<String> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateShoppingCartDTO updateShoppingCartDTO = (UpdateShoppingCartDTO) o;
    return Objects.equals(this.shoppingCart, updateShoppingCartDTO.shoppingCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shoppingCart);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateShoppingCartDTO {\n");
    
    sb.append("    shoppingCart: ").append(toIndentedString(shoppingCart)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

