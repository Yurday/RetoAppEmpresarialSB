package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecasecrud.UseCaseObtenerQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class GetUseCaseTest {

    // mockear dependencias que dependen del caso de uso
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    UseCaseObtenerQuestion useCaseObtenerQuestion;

    @Test
    @DisplayName("Test del caso de uso obtener pregunta por su id")
    public void obtenerQuestionTest(){

        //arrange

        QuestionDTO questionDTO= new QuestionDTO("12","1","que fue primero", "OPEN","xxx");
        Question question=new Question();
        question.setId("12");
        question.setUserId("1");
        question.setQuestion("que fue primero");
        question.setType("open");
        question.setCategory("xxx");

        //act
        //simular dependecia findById que retornan un Mono<QuestionDTO>.

        Mockito.when(questionRepository.findById("12")).thenReturn(Mono.just(question));

        //assert

        var datos = useCaseObtenerQuestion.obtenerPorId("12");

        Assertions.assertEquals(datos.block().getId(),"12");
        Assertions.assertEquals(datos.block().getQuestion(),"que fue primero");
        Assertions.assertEquals(datos.block().getCategory(), "xxx");
    }

}