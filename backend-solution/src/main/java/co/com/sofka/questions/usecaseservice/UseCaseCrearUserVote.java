package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.mappers.UserMapper;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCrearUserVote {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UseCaseCrearUserVote(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    //Crear UsuarioVotos

    public Mono<UserVoteDTO> insertarUserVote(UserVoteDTO userVoteDTO) {
        return userRepository
                .save(userMapper.fromUserVoteDtoToUserVote(null)
                        .apply(userVoteDTO))
                .map(user -> userVoteDTO);
    }
}
