package repository;

import com.lorack.astroquizz.domain.Question;

import java.util.Optional;

public interface QuestionRepository {

    void addQuestion(Question question);

    Optional<Question> findQuestionByNumber(int questionNumber);
}
