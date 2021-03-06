package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

public class AnswerDTO {


    @NotBlank
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;

    private Integer vote;

    //constructor

    public AnswerDTO() {

    }

    public AnswerDTO( @NotBlank String questionId, @NotBlank String userId, @NotBlank String answer) {

        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public AnswerDTO(String userId, String questionId, String answer, Integer vote) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.vote = vote;
    }

    //getter and setter


    public Integer getVote() {
        return Optional.ofNullable(vote).orElse(1);
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //validate

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    //tostring

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
