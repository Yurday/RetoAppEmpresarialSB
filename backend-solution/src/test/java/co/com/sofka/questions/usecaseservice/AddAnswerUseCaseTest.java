package co.com.sofka.questions.usecaseservice;

import static org.junit.jupiter.api.Assertions.*;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.usecaseservice.UseCaseAddAnswer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class AddAnswerUseCaseTest {
    @SpyBean
    private UseCaseAddAnswer useCaseAddAnswer;
    @MockBean
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("Test del caso de uso crear respuesta a una pregunta por id")
    public void testCrearRespuesta(){

        AnswerDTO answerDTO=new AnswerDTO("1","2","la gallina");
        Answer answer=new Answer();
        answer.setId("1");
        answer.setQuestionId("1");
        answer.setUserId("2");
        answer.setAnswer("la gallina");


        when(answerRepository.save(any(Answer.class))).thenReturn(Mono.just(answer));

        var respuesta=useCaseAddAnswer.createAnswer(answerDTO);

        Assertions.assertEquals(respuesta.block().getQuestionId(),answerDTO.getQuestionId());
        Assertions.assertEquals(respuesta.block().getUserId(),"2");
        Assertions.assertEquals(respuesta.block().getAnswer(),"la gallina");

    }

}