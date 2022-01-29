package health.hbp.api.stub.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import health.hbp.api.stub.model.Facts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A fit or suitable to be eaten element
 */
@ApiModel(description = "A fit or suitable to be eaten element")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-29T12:28:12.156-03:00[America/Argentina/Buenos_Aires]")
public class Edible   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("brand")
  private String brand = null;

  @JsonProperty("upc")
  private String upc = null;

  @JsonProperty("facts")
  private Facts facts = null;

  public Edible id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the edible
   * @return id
  **/
  @ApiModelProperty(value = "The identifier of the edible")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Edible name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the edible
   * @return name
  **/
  @ApiModelProperty(value = "The name of the edible")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Edible brand(String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Brand of the edible
   * @return brand
  **/
  @ApiModelProperty(value = "Brand of the edible")
  
    public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Edible upc(String upc) {
    this.upc = upc;
    return this;
  }

  /**
   * UPC of the edible
   * @return upc
  **/
  @ApiModelProperty(value = "UPC of the edible")
  
    public String getUpc() {
    return upc;
  }

  public void setUpc(String upc) {
    this.upc = upc;
  }

  public Edible facts(Facts facts) {
    this.facts = facts;
    return this;
  }

  /**
   * Get facts
   * @return facts
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Facts getFacts() {
    return facts;
  }

  public void setFacts(Facts facts) {
    this.facts = facts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Edible edible = (Edible) o;
    return Objects.equals(this.id, edible.id) &&
        Objects.equals(this.name, edible.name) &&
        Objects.equals(this.brand, edible.brand) &&
        Objects.equals(this.upc, edible.upc) &&
        Objects.equals(this.facts, edible.facts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brand, upc, facts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Edible {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    upc: ").append(toIndentedString(upc)).append("\n");
    sb.append("    facts: ").append(toIndentedString(facts)).append("\n");
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
