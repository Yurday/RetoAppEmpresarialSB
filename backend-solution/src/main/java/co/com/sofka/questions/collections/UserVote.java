package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserVote {
    @Id
    private String id;
    private String userId;
    private Boolean tipoVoto;
    private String answerId;// respuesta votada

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getTipoVoto() {
        return tipoVoto;
    }

    public void setTipoVoto(Boolean tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
