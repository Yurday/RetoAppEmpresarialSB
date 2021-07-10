package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.mappers.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;


@Service
@Validated
public class UseCaseSugerirPreguntas {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseSugerirPreguntas(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    //sugerir preguntas
    public Flux<QuestionDTO> suggest(String pregunta){
        return questionRepository.findByquestionLike(pregunta).map(questionMapper.mapQuestionToDTO());
    }
}
