package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.usecasecrud.UseCaseBorrarQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class DeleteUseCaseTest {

    // mockear ambas dependencias que dependen del caso de uso
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    UseCaseBorrarQuestion useCaseBorrarQuestion;

    @Test
    public void eliminarQuestionTest(){

        //arrange

        QuestionDTO questionDTO= new QuestionDTO("12","1","que fue primero", "OPEN","xxx");
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId("12");
        answerDTO.setUserId("1");
        answerDTO.setAnswer("la gallina");

        //act
        //simular dependencia deleteByid y deleteByquestionid que retornan un mono vacio.

        Mockito.when(questionRepository.deleteById("12")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("12")).thenReturn(Mono.empty());

        //assert

        var datos = useCaseBorrarQuestion.borrarPorId("12").block();

        Assertions.assertEquals(datos,null);
    }

}