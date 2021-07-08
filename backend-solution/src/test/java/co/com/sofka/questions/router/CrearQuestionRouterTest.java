package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.routerscrud.CrearQuestionRouter;
import co.com.sofka.questions.usecasecrud.UseCaseCrearQuestion;
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
@ContextConfiguration(classes = {CrearQuestionRouter.class})
class CrearQuestionRouterTest {

    @MockBean
    private UseCaseCrearQuestion useCaseCrearQuestion;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void crearQuestionTest(){

        QuestionDTO questionDTO = new QuestionDTO("12","1","que fue primero","open","xxx");
        when(useCaseCrearQuestion.insertar(questionDTO)).thenReturn(Mono.just(questionDTO));

        webTestClient.post()
                .uri("/crearpregunta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(questionDTO), QuestionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(questionDTO.getId());
                            Assertions.assertThat(userResponse.getUserId()).isEqualTo(questionDTO.getUserId());
                            Assertions.assertThat(userResponse.getQuestion()).isEqualTo(questionDTO.getQuestion());
                            Assertions.assertThat(userResponse.getType()).isEqualTo(questionDTO.getType());
                            Assertions.assertThat(userResponse.getCategory()).isEqualTo(questionDTO.getCategory());
                        }
                );


    }

}