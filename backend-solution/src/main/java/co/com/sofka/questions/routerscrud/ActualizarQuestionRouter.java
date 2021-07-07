package co.com.sofka.questions.routerscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecasecrud.UseCaseActualizarQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarQuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> modify(UseCaseActualizarQuestion useCaseActualizarQuestion) {
        return route(PUT("/modificarpregunta").and(accept(MediaType.APPLICATION_JSON)),//uso json
                request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> useCaseActualizarQuestion.modificar(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)//tipo respuesta texto plano o json
                                        .bodyValue(result))
                        )
        );
    }
}
