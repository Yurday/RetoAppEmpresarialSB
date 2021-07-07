package co.com.sofka.questions.usecasecrud;

import co.com.sofka.questions.mappers.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseObtenerQuestion {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseObtenerQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }
    //obtner question por id
    public Mono<QuestionDTO> obtenerPorId(String id) {
        return questionRepository.findById(id).map(questionMapper.mapQuestionToDTO());

    }

}
