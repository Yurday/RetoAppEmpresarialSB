package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.mappers.QuestionMapper;
import co.com.sofka.questions.mappers.UserMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseCrearUser {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UseCaseCrearUser(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }




    //Crear Usuario

    public Mono<UserDTO> insertaruser(UserDTO userDTO) {
        return userRepository
                .save(userMapper.fromUserDtoToUser(null)
                        .apply(userDTO))
                .map(user -> userDTO);
    }
}
