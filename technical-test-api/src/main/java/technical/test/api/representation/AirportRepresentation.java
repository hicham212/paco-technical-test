package technical.test.api.representation;

import lombok.Data;

@Data
public class AirportRepresentation {
    private String iata;
    private String name;
    private String country;
}
