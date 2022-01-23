package health.hbp.api.stub.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A nutrition fact
 */
@ApiModel(description = "A nutrition fact")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-01-23T11:37:42.623-03:00[America/Argentina/Buenos_Aires]")
public class Facts   {
  @JsonProperty("portion")
  private Double portion = null;

  @JsonProperty("sodium")
  private Double sodium = null;

  @JsonProperty("potassium")
  private Double potassium = null;

  public Facts portion(Double portion) {
    this.portion = portion;
    return this;
  }

  /**
   * The amount of edible expressed in grams or milliliters
   * @return portion
  **/
  @ApiModelProperty(value = "The amount of edible expressed in grams or milliliters")
  
    public Double getPortion() {
    return portion;
  }

  public void setPortion(Double portion) {
    this.portion = portion;
  }

  public Facts sodium(Double sodium) {
    this.sodium = sodium;
    return this;
  }

  /**
   * The amount of sodium expressed in milligrams
   * @return sodium
  **/
  @ApiModelProperty(value = "The amount of sodium expressed in milligrams")
  
    public Double getSodium() {
    return sodium;
  }

  public void setSodium(Double sodium) {
    this.sodium = sodium;
  }

  public Facts potassium(Double potassium) {
    this.potassium = potassium;
    return this;
  }

  /**
   * The amount of potassium expressed in milligrams
   * @return potassium
  **/
  @ApiModelProperty(value = "The amount of potassium expressed in milligrams")
  
    public Double getPotassium() {
    return potassium;
  }

  public void setPotassium(Double potassium) {
    this.potassium = potassium;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Facts facts = (Facts) o;
    return Objects.equals(this.portion, facts.portion) &&
        Objects.equals(this.sodium, facts.sodium) &&
        Objects.equals(this.potassium, facts.potassium);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portion, sodium, potassium);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Facts {\n");
    
    sb.append("    portion: ").append(toIndentedString(portion)).append("\n");
    sb.append("    sodium: ").append(toIndentedString(sodium)).append("\n");
    sb.append("    potassium: ").append(toIndentedString(potassium)).append("\n");
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
