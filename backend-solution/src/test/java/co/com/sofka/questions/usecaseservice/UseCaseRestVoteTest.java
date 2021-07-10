package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.MensajeDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UseCaseRestVoteTest {

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    UseCaseRestVote useCaseRestVoteVote;

    @Test
    @DisplayName("Test de restar voto a respuesta por usuario")
    public void restarVotoTest(){

        //Arrange

        UserVoteDTO userVoteDTO = new UserVoteDTO("12", "34", true, "12");

        UserVote userVote = new UserVote();
        userVote.setId("12");
        userVote.setUserId("34");
        userVote.setTipoVoto(true);
        userVote.setAnswerId("12");

        Answer answer = new Answer();
        answer.setId("12");
        answer.setQuestionId("1");
        answer.setUserId("34");
        answer.setAnswer("la gallina");
        answer.setVote(1);

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId("12");
        answerDTO.setUserId("34");
        answerDTO.setAnswer("la gallina");
        answerDTO.setVote(1);

        MensajeDTO mensajeDTO = new MensajeDTO("El usuario no puede realizar mas de una votacion", "Qué fue primero?", 5);

        //Act
        Mockito.when(userRepository.findByUserId("34")).thenReturn(Flux.just(userVote));
        Mockito.when(answerRepository.findById("12")).thenReturn(Mono.just(answer));

        //Asserts
        var datos = useCaseRestVoteVote.restPosition("12", "34");

        Assertions.assertEquals(datos.block().getMensaje(),mensajeDTO.getMensaje());

    }
}