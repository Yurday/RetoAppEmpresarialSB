package co.com.sofka.questions.repositories;

import co.com.sofka.questions.collections.UserVote;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserVote, String> {


}
