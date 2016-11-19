package trainSimulator.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Entity
@Table(name = "generator_parameters")
public class GeneratorParameter extends BaseEntity {
    private String parameterName;
    private String parameterValue;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}
