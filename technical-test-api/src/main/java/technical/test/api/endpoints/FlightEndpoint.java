package technical.test.api.endpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.facade.FlightFacade;
import technical.test.api.representation.FlightRepresentation;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
@Slf4j
public class FlightEndpoint {
    private final FlightFacade flightFacade;

    @GetMapping
    public Flux<FlightRepresentation> getFlights(
            @RequestParam(required = false, defaultValue = "price") String column,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "100") int sizePerPage) {
        return flightFacade.flightsByCriteria(column, direction, page, sizePerPage);
    }

    @PostMapping
    public Mono<FlightRepresentation> createFlight(@RequestBody FlightRepresentation flightRepresentation) {
        return flightFacade.saveFlight(flightRepresentation);
    }
}
