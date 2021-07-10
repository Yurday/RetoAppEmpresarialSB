package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;

import co.com.sofka.questions.routerscrud.ObtenerQuestionRouter;
import co.com.sofka.questions.usecasecrud.UseCaseObtenerQuestion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObtenerQuestionRouter.class})
class ObtenerQuestionRouterTest {

    @MockBean
    private UseCaseObtenerQuestion useCaseObtenerQuestion;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router obtener pregunta por su id")
    public void obtenerQuestionTest() {
        //arrange
        QuestionDTO questionDTO = new QuestionDTO("12", "1", "que fue primero", "open", "xxx");

        when(useCaseObtenerQuestion.obtenerPorId("12")).thenReturn(Mono.just(questionDTO));

        webTestClient.get()
                .uri("/consultarpregunta/{id}","12")
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(userResponse -> {
                            //comparar el questionDTO con el con el test http del cliente.

                            Assertions.assertThat(userResponse.getId()).isEqualTo(questionDTO.getId());
                            Assertions.assertThat(userResponse.getUserId()).isEqualTo(questionDTO.getUserId());
                            Assertions.assertThat(userResponse.getQuestion()).isEqualTo(questionDTO.getQuestion());
                            Assertions.assertThat(userResponse.getType()).isEqualTo(questionDTO.getType());
                            Assertions.assertThat(userResponse.getCategory()).isEqualTo(questionDTO.getCategory());
                        }


                );
    }


}