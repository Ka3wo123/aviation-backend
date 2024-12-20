package aviation.repository;

import aviation.models.AviationUserFlight;
import aviation.models.stats.UserFlightStat;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface FlightUserRepository extends CrudRepository<AviationUserFlight, Long> {
  @Query(value = "SELECT * FROM aviation_user_flight WHERE aviation_user_email = :email")
  Flux<AviationUserFlight> findByUserEmail(String email);

  @Query(value = "DELETE FROM aviation_user_flight WHERE aviation_user_email = :email AND id = :id")
  Mono<Integer> deleteFlightByEmailAndID(String email, Long id);

  @Query(value = "SELECT COUNT(1) > 0 FROM aviation_user_flight WHERE aviation_user_email = :email AND flight_id = :flightId")
  Boolean ifExists(String email, String flightId);

  @Query(value = "SELECT airline AS airline, COUNT(*) AS count FROM aviation_user_flight GROUP BY airline")
  Flux<UserFlightStat> countUsersFlightByAirline();
}
