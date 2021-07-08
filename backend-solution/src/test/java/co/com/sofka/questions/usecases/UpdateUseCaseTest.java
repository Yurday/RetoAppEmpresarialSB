package co.com.sofka.questions.usecases;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecasecrud.UseCaseActualizarQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class UpdateUseCaseTest {

    @SpyBean
    private UseCaseActualizarQuestion useCaseActualizarQuestion;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void testActualizarPregunta() {


        QuestionDTO questionDTO = new QuestionDTO("12", "1", "que fue primero", "open", "xxx");

        Question question = new Question();
        question.setId("12");
        question.setUserId("1");
        question.setQuestion("que fue primero");
        question.setType("open");
        question.setCategory("xxx");

        //act
        //simular dependecia save que retorna un mono question

        when(questionRepository.save(any(Question.class))).thenReturn(Mono.just(question));

        var respuesta = useCaseActualizarQuestion.modificar(questionDTO);

        Assertions.assertEquals(respuesta.block().getId(), "12");


    }
}