package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.repositories.UserRepository;
import co.com.sofka.questions.usecasecrud.UseCaseBorrarQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UseCaseEliminarVotoTest {

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    UseCaseEliminarVoto useCaseEliminarVoto;

    @Test
    @DisplayName("XX")
    public void eliminarVotoTest(){
        //arrange

        UserVoteDTO userVoteDTO = new UserVoteDTO("12", "34", true, "56");

        UserVote userVote = new UserVote();
        userVote.setId("12");
        userVote.setUserId("34");
        userVote.setTipoVoto(true);
        userVote.setAnswerId("56");

        Answer answer = new Answer();
        answer.setId("12");
        answer.setQuestionId("1");
        answer.setUserId("21");
        answer.setAnswer("la gallina");


        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId("12");
        answerDTO.setUserId("21");
        answerDTO.setAnswer("la gallina");

        //act
        //simular dependencia deleteByid y deleteByquestionid que retornan un mono vacio.

        Mockito.when(answerRepository.findById("12")).thenReturn(Mono.just(answer));
        Mockito.when(userRepository.findById("34")).thenReturn(Mono.just(userVote));
        Mockito.when(answerRepository.save(any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(userRepository.deleteById("12")).thenReturn(Mono.empty());

        //assert

        var datos = useCaseEliminarVoto.borrarVoto("12","12").block();

        Assertions.assertEquals(datos,null);
    }
}