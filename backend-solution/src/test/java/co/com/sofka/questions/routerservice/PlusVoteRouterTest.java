package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.model.MensajeDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import co.com.sofka.questions.usecaseservice.UseCasePlusVote;
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
@ContextConfiguration(classes = {PlusVoteRouter.class})
class PlusVoteRouterTest {

    @MockBean
    private UseCasePlusVote useCasePlusVote;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router sumar voto de usuario a una respuesta")
    public void plusVoteRouterTest(){

        MensajeDTO mensajeDTO = new MensajeDTO("Mensaje...", "answer...", 5);
        when(useCasePlusVote.plusPosition("123","245")).thenReturn(Mono.just(mensajeDTO));

        webTestClient.put()
                .uri("/sumarvoto/{answerid}/{userid}","123","245")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mensajeDTO), UserVoteDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MensajeDTO.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.getMensaje()).isEqualTo(mensajeDTO.getMensaje());
                    Assertions.assertThat(userResponse.getAnswer()).isEqualTo(mensajeDTO.getAnswer());
                    Assertions.assertThat(userResponse.getVoto()).isEqualTo(mensajeDTO.getVoto());
                });
    }
}