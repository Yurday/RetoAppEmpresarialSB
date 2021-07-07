package co.com.sofka.questions.routerscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecasecrud.UseCaseObtenerQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ObtenerQuestionRouter {
    @Bean
    public RouterFunction<ServerResponse> getById(UseCaseObtenerQuestion useCaseObtenerQuestion) {
        return route(
                GET("/consultarquestion/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .body(useCaseObtenerQuestion.obtenerPorId(request.pathVariable("id")), QuestionDTO.class)
        );
    }
}
