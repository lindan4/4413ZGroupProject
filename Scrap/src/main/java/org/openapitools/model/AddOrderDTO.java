package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.Address;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AddOrderDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-01T17:41:52.282094-05:00[America/Toronto]")

public class AddOrderDTO   {
  @JsonProperty("bids")
  @Valid
  private List<String> bids = new ArrayList<>();

  @JsonProperty("shippingAddress")
  private Address shippingAddress;

  @JsonProperty("billingAddress")
  private Address billingAddress;

  public AddOrderDTO bids(List<String> bids) {
    this.bids = bids;
    return this;
  }

  public AddOrderDTO addBidsItem(String bidsItem) {
    this.bids.add(bidsItem);
    return this;
  }

  /**
   * List of book identifiers
   * @return bids
  */
  @ApiModelProperty(required = true, value = "List of book identifiers")
  @NotNull


  public List<String> getBids() {
    return bids;
  }

  public void setBids(List<String> bids) {
    this.bids = bids;
  }

  public AddOrderDTO shippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  /**
   * Get shippingAddress
   * @return shippingAddress
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public AddOrderDTO billingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
    return this;
  }

  /**
   * Get billingAddress
   * @return billingAddress
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Address getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Address billingAddress) {
    this.billingAddress = billingAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddOrderDTO addOrderDTO = (AddOrderDTO) o;
    return Objects.equals(this.bids, addOrderDTO.bids) &&
        Objects.equals(this.shippingAddress, addOrderDTO.shippingAddress) &&
        Objects.equals(this.billingAddress, addOrderDTO.billingAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bids, shippingAddress, billingAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddOrderDTO {\n");
    
    sb.append("    bids: ").append(toIndentedString(bids)).append("\n");
    sb.append("    shippingAddress: ").append(toIndentedString(shippingAddress)).append("\n");
    sb.append("    billingAddress: ").append(toIndentedString(billingAddress)).append("\n");
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

