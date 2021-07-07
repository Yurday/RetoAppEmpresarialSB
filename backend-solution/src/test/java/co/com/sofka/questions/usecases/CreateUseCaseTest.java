package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecasecrud.UseCaseCrearQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateUseCaseTest {

    @SpyBean
    private UseCaseCrearQuestion useCaseCrearQuestion;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void testCrearPregunta(){

        //Arrange

        QuestionDTO questionDTO = new QuestionDTO("12","1","que fue primero","open","xxx");

        Question question = new Question();
        question.setId("12");
        question.setUserId("1");
        question.setQuestion("¿Qué fue primero?");
        question.setType("open");
        question.setCategory("xxx");

        //Act

        when(questionRepository.save(any(Question.class))).thenReturn(Mono.just(question));

        //Assert

        var respuesta=useCaseCrearQuestion.insertar(questionDTO);

        Assertions.assertEquals(respuesta.block().getId(),"12");
        Assertions.assertEquals(respuesta.block().getUserId(),"1");

    }
}