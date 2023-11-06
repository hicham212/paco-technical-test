package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import technical.test.api.record.AirportRecord;

@Repository
public interface AirportRepository extends ReactiveMongoRepository<AirportRecord, String> {
    Mono<AirportRecord> findAirportRecordByIata(final String iata);
}
