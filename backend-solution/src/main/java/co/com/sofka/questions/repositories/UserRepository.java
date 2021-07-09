package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.UserVote;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserVote, String> {

    Flux<UserVote> findByUserId(String userId);
}
