package ca.lorack.astroquizz.persistence;

import com.lorack.astroquizz.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionRepositoryInMemory {

    private List<Question> questions;

    public QuestionRepositoryInMemory() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int count() {
        return questions.size();
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }

    public Optional<Question> findQuestionByNumber(int questionNumber) {
        return questions.stream().filter(question -> question.hasNumber(questionNumber)).findFirst();
    }
}
