package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.UserRepository;
import co.com.sofka.questions.usecasecrud.UseCaseBorrarQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UseCaseEliminarVotoTest {

    @SpyBean
    UseCaseEliminarVoto useCaseEliminarVoto;

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test eliminar voto por id")
    public void eliminarVotoTest(){
        //arrange

        UserVoteDTO userVoteDTO = new UserVoteDTO("12", "12", true, "12");

        UserVote userVote = new UserVote();
        userVote.setId("12");
        userVote.setUserId("12");
        userVote.setTipoVoto(true);
        userVote.setAnswerId("12");

        Answer answer = new Answer();
        answer.setId("12");
        answer.setQuestionId("12");
        answer.setUserId("21");
        answer.setAnswer("la gallina");
        answer.setVote(1);


        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId("12");
        answerDTO.setUserId("12");
        answerDTO.setAnswer("la gallina");

        //Act

        Mockito.when(answerRepository.findById("12")).thenReturn(Mono.just(answer));
        Mockito.when(userRepository.findById("12")).thenReturn(Mono.just(userVote));
        Mockito.when(answerRepository.save(any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(userRepository.deleteById("12")).thenReturn(Mono.empty());
        //Assert

        var datos = useCaseEliminarVoto.borrarVoto("12", "12");

        Assertions.assertEquals(datos.block(),null);
    }
}