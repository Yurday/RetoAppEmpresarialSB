package co.com.sofka.questions.router;

import static org.junit.jupiter.api.Assertions.*;

import co.com.sofka.questions.routerscrud.BorrarQuestionRouter;
import co.com.sofka.questions.usecasecrud.UseCaseBorrarQuestion;
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
@ContextConfiguration(classes = {BorrarQuestionRouter.class})
class BorrarQuestionRouterTest {
    @MockBean
    private UseCaseBorrarQuestion useCaseBorrarQuestion;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router de borrar preguntas por su id")
    public void borraQuestionTest(){

        when(useCaseBorrarQuestion.borrarPorId("12")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/borrarpregunta/{id}","12")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo(null);

                        }
                );
    }


}