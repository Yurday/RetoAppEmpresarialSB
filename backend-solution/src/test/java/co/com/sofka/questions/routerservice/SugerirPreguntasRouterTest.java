package co.com.sofka.questions.routerservice;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecaseservice.UseCaseEliminarVoto;
import co.com.sofka.questions.usecaseservice.UseCaseSugerirPreguntas;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SugerirPreguntasRouter.class})
class SugerirPreguntasRouterTest {

    @MockBean
    private UseCaseSugerirPreguntas useCaseSugerirPreguntas;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router sugerir preguntas al usuario por coincidencias de palabras")
    public void sugerirPregRouterTest(){

        QuestionDTO questionDTO1 = new QuestionDTO("12","1","Qué fue primero","open","xxx");
        QuestionDTO questionDTO2 = new QuestionDTO("34","2","Qué fue segundo","open","xxx");
        when(useCaseSugerirPreguntas.suggest("Qué fue primero")).thenReturn(Flux.just(questionDTO1, questionDTO2));
        webTestClient.get()
                .uri("/sugerirpreguntas/{pregunta}","Qué fue primero")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(questionDTO1.getId());
                            Assertions.assertThat(userResponse.get(0).getUserId()).isEqualTo(questionDTO1.getUserId());
                            Assertions.assertThat(userResponse.get(0).getQuestion()).isEqualTo(questionDTO1.getQuestion());
                            Assertions.assertThat(userResponse.get(0).getType()).isEqualTo(questionDTO1.getType());
                            Assertions.assertThat(userResponse.get(0).getCategory()).isEqualTo(questionDTO1.getCategory());
                            Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(questionDTO2.getId());
                            Assertions.assertThat(userResponse.get(1).getUserId()).isEqualTo(questionDTO2.getUserId());
                            Assertions.assertThat(userResponse.get(1).getQuestion()).isEqualTo(questionDTO2.getQuestion());
                            Assertions.assertThat(userResponse.get(1).getType()).isEqualTo(questionDTO2.getType());
                            Assertions.assertThat(userResponse.get(1).getCategory()).isEqualTo(questionDTO2.getCategory());
                        }
                );


    }

}