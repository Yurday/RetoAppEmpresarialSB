package co.com.sofka.questions.mappers;

import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.MensajeDTO;
import co.com.sofka.questions.model.UserVoteDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MensajeMapper {



    public Function<AnswerDTO, MensajeDTO> votar(String mensaje){
        return answer ->{
            var mensajeDTO = new MensajeDTO();
            mensajeDTO.setAnswer(answer.getAnswer());
            mensajeDTO.setVoto(answer.getVote());
            mensajeDTO.setMensaje(mensaje);
            return mensajeDTO;

        };
    }

}
