package technical.test.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.record.FlightRecord;
import technical.test.api.repository.FlightRepository;
import technical.test.api.repository.FlightSortingRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {
    private final FlightRepository flightRepository;

    private final FlightSortingRepository flightSortingRepository;

    public Flux<FlightRecord> getAllFlights() {
        return flightRepository.findAll();
    }

    public Mono<FlightRecord> saveFlight(FlightRecord flightRecord) {
        log.info("save new document flight");
        return flightRepository.save(flightRecord);
    }

    public Flux<FlightRecord> flightsByCriteria(String sortColumn, String sortDirection, int page, int sizePerPage) {

        if(Objects.isNull(sortColumn))
            return flightRepository.findAll();
        else{
            Pageable pageable = PageRequest.of(page, sizePerPage, Sort.Direction.valueOf(sortDirection), sortColumn);
            return flightSortingRepository.findAllBy(pageable);
        }
    }
}
