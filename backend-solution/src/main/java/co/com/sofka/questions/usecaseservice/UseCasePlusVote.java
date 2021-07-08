package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.mappers.AnswerMapper;
import co.com.sofka.questions.mappers.UserMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCasePlusVote {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UseCasePlusVote(AnswerRepository answerRepository, AnswerMapper answerMapper, UserRepository userRepository, UserMapper userMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Mono<UserVoteDTO> plusPosition(String Answerid, String userId){


        return answerRepository.findById(Answerid).flatMap(rs ->{

            rs.setVote(rs.getVote() +1);
            return answerRepository.save(rs);
        }).map(answerMapper.fromAnswerToAnswerDTO()).flatMap( us -> {

            var userVote = new UserVoteDTO();
            userVote.setAnswerId(Answerid);
            userVote.setId(userId);
            userVote.setHabilitado(false);
            userVote.setTipoVoto(false);

            return  userRepository.save(userMapper.fromUserVoteDtoToUserVote(null).apply(userVote)).map(userMapper.fromUserVoteToUserVoteDTO());

        });



    }



}
