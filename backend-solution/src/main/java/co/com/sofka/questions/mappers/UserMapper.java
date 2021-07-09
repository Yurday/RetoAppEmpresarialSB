package co.com.sofka.questions.mappers;

import co.com.sofka.questions.collections.UserVote;
import co.com.sofka.questions.model.UserVoteDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper {

    public Function<UserVoteDTO, UserVote> fromUserVoteDtoToUserVote(String id){
        return updateUser->{
            var user=new UserVote();
            user.setId(id);
            user.setUserId(updateUser.getUserId());
            user.setTipoVoto(updateUser.getTipoVoto());
            user.setAnswerId(updateUser.getAnswerId());
            return user;
        };

    }
    public Function<UserVote, UserVoteDTO> fromUserVoteToUserVoteDTO(){
        return entity->
                new UserVoteDTO(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getTipoVoto(),
                        entity.getAnswerId()
                );

    }
}
