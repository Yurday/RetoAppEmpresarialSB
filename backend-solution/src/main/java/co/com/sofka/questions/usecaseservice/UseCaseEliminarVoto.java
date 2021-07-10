package co.com.sofka.questions.usecaseservice;
import co.com.sofka.questions.mappers.AnswerMapper;
import co.com.sofka.questions.mappers.UserMapper;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseEliminarVoto {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private  final UserMapper userMapper;

    public UseCaseEliminarVoto(UserRepository userRepository, AnswerRepository answerRepository, AnswerMapper answerMapper,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.userMapper=userMapper;
    }

    //Borrar registro votacion
    public Mono<Void> borrarVoto(String answerId,String userVoteId) {

        return answerRepository.findById(answerId).flatMap(rs -> buscarUsuario(userVoteId).flatMap(user -> {
             if (user.getTipoVoto()) {
                 rs.setVote(rs.getVote() - 1);
                 return answerRepository.save(rs);
             } else {
                 rs.setVote(rs.getVote() + 1);
                 return answerRepository.save(rs);
             }

         })).map(answerMapper.fromAnswerToAnswerDTO()).flatMap(a -> userRepository.deleteById(userVoteId));

    }
    public Mono<UserVoteDTO> buscarUsuario(String userVoteId){
        return  userRepository.findById(userVoteId)
                .map(userMapper.fromUserVoteToUserVoteDTO());
    }


}
