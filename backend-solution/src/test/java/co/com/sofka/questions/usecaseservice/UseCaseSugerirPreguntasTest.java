package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import static org.mockito.Mockito.when;

@SpringBootTest
class UseCaseSugerirPreguntasTest {

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private UseCaseSugerirPreguntas useCaseSugerirPreguntas;

    @Test
    @DisplayName("Sugerir preguntas por coincidencia")
    public void sugerirPreguntasTest(){

        var questionDTO = new QuestionDTO("1","12345", "Que es SpringBoot?","OPEN","Programming");

        var question = new Question();
        question.setId("1");
        question.setUserId("12345");
        question.setQuestion("Ques es SpringBoot?");
        question.setType("OPEN");
        question.setCategory("Programming");

        when(questionRepository.findByquestionLike(Mockito.any(String.class))).thenReturn(Flux.just(question));

        var resultado = useCaseSugerirPreguntas.suggest("que fue");

        Assertions.assertEquals(resultado.blockFirst().getQuestion(),"Ques es SpringBoot?");
        Assertions.assertEquals(resultado.blockFirst().getId(),"1");
        Assertions.assertEquals(resultado.blockFirst().getUserId(),"12345");

    }

}