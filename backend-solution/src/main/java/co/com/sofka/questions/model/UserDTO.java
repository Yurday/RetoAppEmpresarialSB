package co.com.sofka.questions.model;

public class UserDTO {

    private String id;
    private Boolean voto;


    public UserDTO() {

    }

    public UserDTO(String id, Boolean voto) {
        this.id = id;
        this.voto = voto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }
}
