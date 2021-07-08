package co.com.sofka.questions.usecaseservice;

import co.com.sofka.questions.mappers.AnswerMapper;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PlusVote {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public PlusVote(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }



}
