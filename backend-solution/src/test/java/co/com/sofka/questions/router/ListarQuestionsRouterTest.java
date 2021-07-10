package co.com.sofka.questions.router;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mappers.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import co.com.sofka.questions.routerscrud.ListarQuestionsRouter;
import co.com.sofka.questions.usecasecrud.UseCaseListarQuestions;
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
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarQuestionsRouter.class, UseCaseListarQuestions.class, QuestionMapper.class})
class ListarQuestionsRouterTest {
    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Test del router de listar preguntas")
    public void testListarPreguntas() {
        Question question1 = new Question();
        question1.setId("1");
        question1.setUserId("123");
        question1.setQuestion("¿que es DDD?");
        question1.setType("opinion");
        question1.setCategory("software development");

        Question question2 = new Question();
        question2.setId("2");
        question2.setUserId("123");
        question2.setQuestion("¿que es TDD?");
        question2.setType("opinion");
        question2.setCategory("software development");

        when(questionRepository.findAll()).thenReturn(Flux.just(question1, question2));

        webTestClient.get()
                .uri("/consultarpreguntas")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(question1.getId());
                            Assertions.assertThat(userResponse.get(0).getUserId()).isEqualTo(question1.getUserId());
                            Assertions.assertThat(userResponse.get(0).getQuestion()).isEqualTo(question1.getQuestion());
                            Assertions.assertThat(userResponse.get(0).getType()).isEqualTo(question1.getType());
                            Assertions.assertThat(userResponse.get(0).getCategory()).isEqualTo(question1.getCategory());

                            Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(question2.getId());
                            Assertions.assertThat(userResponse.get(1).getUserId()).isEqualTo(question2.getUserId());
                            Assertions.assertThat(userResponse.get(1).getQuestion()).isEqualTo(question2.getQuestion());
                            Assertions.assertThat(userResponse.get(1).getType()).isEqualTo(question2.getType());
                            Assertions.assertThat(userResponse.get(1).getCategory()).isEqualTo(question2.getCategory());

                        }
                );


    }
}

