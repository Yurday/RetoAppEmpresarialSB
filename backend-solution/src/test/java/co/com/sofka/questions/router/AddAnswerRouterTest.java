package co.com.sofka.questions.router;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.routerservice.AddAnswerRouter;
import co.com.sofka.questions.usecaseservice.UseCaseAddAnswer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AddAnswerRouter.class})
class AddAnswerRouterTest {
    @MockBean
    private UseCaseAddAnswer useCaseAddAnswer;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void crearQuestionTest(){

        AnswerDTO answerDTO=new AnswerDTO("1","2","la gallina");
        Answer answer=new Answer();
        answer.setId("1");
        answer.setQuestionId("1");
        answer.setUserId("2");
        answer.setAnswer("la gallina");

        when(useCaseAddAnswer.createAnswer(answerDTO)).thenReturn(Mono.just(answerDTO));

        webTestClient.post()
                .uri("/añadirrespuesta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(answerDTO), AnswerDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AnswerDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getUserId()).isEqualTo(answerDTO.getUserId());
                            Assertions.assertThat(userResponse.getQuestionId()).isEqualTo(answerDTO.getQuestionId());
                            Assertions.assertThat(userResponse.getAnswer()).isEqualTo(answerDTO.getAnswer());
                        }
                );

    }

}