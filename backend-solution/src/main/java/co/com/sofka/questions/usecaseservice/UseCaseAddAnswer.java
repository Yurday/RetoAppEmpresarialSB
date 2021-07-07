package co.com.sofka.questions.usecaseservice;


import co.com.sofka.questions.mappers.AnswerMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseAddAnswer {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Autowired
    public UseCaseAddAnswer(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    //Crear respuesta-se relacionan con la pregunta  por el id de la la pregunta(questionid)


    public Mono<AnswerDTO> createAnswer(AnswerDTO answerDTO) {
        var respuesta = answerRepository.save(answerMapper.fromAnswerDtoToAnswer(null)
                .apply(answerDTO));
        return respuesta.map(answerMapper.fromAnswerToAnswerDTO());
    }
}
