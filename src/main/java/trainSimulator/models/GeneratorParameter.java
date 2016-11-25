package trainSimulator.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Entity
@Table(name = "generator_parameters")
public class GeneratorParameter implements Serializable {
    private static final long serialVersionUID = 1L;

    public GeneratorParameter() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parameter_id")
    private long id;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "parameter_value")
    private String parameterValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
