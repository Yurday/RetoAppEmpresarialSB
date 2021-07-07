package co.com.sofka.questions.usecasecrud;

import co.com.sofka.questions.mappers.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseBorrarQuestion {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseBorrarQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper,AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.answerRepository=answerRepository;
    }

    //Borrar question and answer por id
    public Mono<Void> borrarPorId(String id) {
        return questionRepository.deleteById(id).switchIfEmpty(answerRepository.deleteByQuestionId(id));
    }

}
