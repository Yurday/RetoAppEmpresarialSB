package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.mappers.AnswerMapper;
import co.com.sofka.questions.mappers.MensajeMapper;
import co.com.sofka.questions.mappers.UserMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.MensajeDTO;
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
    private final MensajeMapper mensajeMapper;

    @Autowired
    public UseCasePlusVote(AnswerRepository answerRepository, AnswerMapper answerMapper, UserRepository userRepository, UserMapper userMapper, MensajeMapper mensajeMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mensajeMapper = mensajeMapper;
    }


    public Mono<MensajeDTO> plusPosition(String answerId, String userId) {

            var respuesta = userRepository.findByUserId(userId).next()
                    .switchIfEmpty(Mono.just(new UserVote())).map(userMapper.fromUserVoteToUserVoteDTO())
                    .flatMap(response ->{
                        if(response.getUserId() == null){

                            var userVote = new UserVoteDTO();
                            userVote.setAnswerId(answerId);
                            userVote.setUserId(userId);
                            userVote.setTipoVoto(true);

                            return userRepository
                                    .save(userMapper.fromUserVoteDtoToUserVote(null).apply(userVote))
                                    .map(userMapper.fromUserVoteToUserVoteDTO())
                                    .flatMap( user -> sumar(answerId)).map(mensajeMapper.votar("Voto Realizado"));
                        }
                            return userRepository.findByUserId(response.getUserId()).next()
                                    .flatMap(rs -> answerRepository.findById(rs.getAnswerId())
                                            .map(answerMapper.fromAnswerToAnswerDTO()))
                                    .map(mensajeMapper.votar("El usuario no puede realizar mas de una votacion"));
                    });

          return  respuesta;
    }

    public Mono<AnswerDTO> sumar(String answerId) {
        return answerRepository.findById(answerId)
                .flatMap(rs -> {
                    rs.setVote(rs.getVote() + 1);
                    return answerRepository.save(rs);
                }).map(answerMapper.fromAnswerToAnswerDTO());
    }


    }