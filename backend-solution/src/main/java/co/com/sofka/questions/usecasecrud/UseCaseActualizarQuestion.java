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
public class UseCaseActualizarQuestion {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseActualizarQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }
    //modificar una pregunta

    public Mono<QuestionDTO> modificar(QuestionDTO questionDTO) {

        return questionRepository.save(questionMapper.mapperToQuestion(questionDTO.getId())
                .apply(questionDTO))
                .map(questionMapper.mapQuestionToDTO());
    }
}
