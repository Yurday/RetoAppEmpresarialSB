package co.com.sofka.questions.model;

public class MensajeDTO {

    private String mensaje;
    private String answer;
    private Integer voto;

    public MensajeDTO() {

    }

    public MensajeDTO(String mensaje, String answer, Integer voto) {
        this.mensaje = mensaje;
        this.answer = answer;
        this.voto = voto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }
}
