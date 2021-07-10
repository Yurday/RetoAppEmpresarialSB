package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.usecaseservice.UseCaseEliminarVoto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarVotoRouter.class})
class EliminarVotoRouterTest {

    @MockBean
    private UseCaseEliminarVoto useCaseEliminarVoto;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router eliminar voto por id de respuesta y id de usuario")
    public void deleteVoteRouterTest(){

        when(useCaseEliminarVoto.borrarVoto("123","345")).thenReturn(Mono.empty());
        webTestClient.put()
                .uri("/eliminarvoto/{answerid}/{userid}","123","345")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo(null);
                        }
                );
    }
}