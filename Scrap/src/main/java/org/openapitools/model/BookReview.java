package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BookReview
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-03-01T17:41:52.282094-05:00[America/Toronto]")

public class BookReview   {
  @JsonProperty("id")
  private String id;

  @JsonProperty("bid")
  private String bid;

  @JsonProperty("title")
  private String title;

  @JsonProperty("rating")
  private BigDecimal rating;

  @JsonProperty("authorCustomerId")
  private String authorCustomerId;

  @JsonProperty("content")
  private String content;

  @JsonProperty("publicationDate")
  private OffsetDateTime publicationDate;

  @JsonProperty("lastUpdated")
  private OffsetDateTime lastUpdated;

  public BookReview id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BookReview bid(String bid) {
    this.bid = bid;
    return this;
  }

  /**
   * Get bid
   * @return bid
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public BookReview title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BookReview rating(BigDecimal rating) {
    this.rating = rating;
    return this;
  }

  /**
   * Get rating
   * @return rating
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }

  public BookReview authorCustomerId(String authorCustomerId) {
    this.authorCustomerId = authorCustomerId;
    return this;
  }

  /**
   * Get authorCustomerId
   * @return authorCustomerId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAuthorCustomerId() {
    return authorCustomerId;
  }

  public void setAuthorCustomerId(String authorCustomerId) {
    this.authorCustomerId = authorCustomerId;
  }

  public BookReview content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BookReview publicationDate(OffsetDateTime publicationDate) {
    this.publicationDate = publicationDate;
    return this;
  }

  /**
   * Get publicationDate
   * @return publicationDate
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(OffsetDateTime publicationDate) {
    this.publicationDate = publicationDate;
  }

  public BookReview lastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
    return this;
  }

  /**
   * Get lastUpdated
   * @return lastUpdated
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OffsetDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookReview bookReview = (BookReview) o;
    return Objects.equals(this.id, bookReview.id) &&
        Objects.equals(this.bid, bookReview.bid) &&
        Objects.equals(this.title, bookReview.title) &&
        Objects.equals(this.rating, bookReview.rating) &&
        Objects.equals(this.authorCustomerId, bookReview.authorCustomerId) &&
        Objects.equals(this.content, bookReview.content) &&
        Objects.equals(this.publicationDate, bookReview.publicationDate) &&
        Objects.equals(this.lastUpdated, bookReview.lastUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bid, title, rating, authorCustomerId, content, publicationDate, lastUpdated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookReview {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bid: ").append(toIndentedString(bid)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    authorCustomerId: ").append(toIndentedString(authorCustomerId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    publicationDate: ").append(toIndentedString(publicationDate)).append("\n");
    sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
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

