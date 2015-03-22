package com.lorack.astroquizz.domain.module;

import com.lorack.astroquizz.domain.question.Question;
import com.lorack.astroquizz.domain.question.QuestionNumber;

import java.util.HashMap;
import java.util.Map;

public class Module {

    private Map<QuestionNumber, Question> questions;
    private ModuleName moduleName;

    public Module(ModuleName moduleName) {
        this.moduleName = moduleName;
        questions = new HashMap<>();
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean hasName(ModuleName moduleName) {
        return this.moduleName.equals(moduleName);
    }

    public void addQuestion(Question question) {
        questions.put(question.getQuestionNumber(), question);
    }

    public int size() {
        return 1;
    }

    public Question getQuestionByNumber(QuestionNumber questionNumber) {
        return questions.get(questionNumber);
    }
}
