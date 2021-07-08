package co.com.sofka.questions.model;

public class UserVoteDTO {

    private String id;
    private Boolean habilitado;
    private Boolean tipoVoto;
    private String answerId;


    public UserVoteDTO() {

    }

    public UserVoteDTO(String id, Boolean habilitado, Boolean tipoVoto, String answerId) {
        this.id = id;
        this.habilitado = habilitado;
        this.tipoVoto = tipoVoto;
        this.answerId = answerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
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
