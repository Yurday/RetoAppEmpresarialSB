package co.com.sofka.questions.model;

public class UserVoteDTO {

    private String id;
    private String userId;
    private Boolean tipoVoto;
    private String answerId;


    public UserVoteDTO() {

    }

    public UserVoteDTO(String id, String userId, Boolean tipoVoto, String answerId) {
        this.id = id;
        this.userId = userId;
        this.tipoVoto = tipoVoto;
        this.answerId = answerId;
    }

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
