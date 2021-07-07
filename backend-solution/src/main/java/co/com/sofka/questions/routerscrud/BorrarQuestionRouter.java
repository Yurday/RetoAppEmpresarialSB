package co.com.sofka.questions.routerscrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecasecrud.UseCaseBorrarQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BorrarQuestionRouter {
    @Bean
    public RouterFunction<ServerResponse> deleteById(UseCaseBorrarQuestion useCaseBorrarQuestion) {
        return route(
                DELETE("/borrarpregunta/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .body(useCaseBorrarQuestion.borrarPorId(request.pathVariable("id")), QuestionDTO.class)
        );
    }


}
