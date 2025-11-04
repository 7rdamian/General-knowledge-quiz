package model.quiz;

// Child of class Quiz, adds a more extended opportunity for review at the end of the game

import model.Question;

public class ReviewQuiz extends Quiz {

    public ReviewQuiz(int size, String difficulty, String category) {
        super(size, difficulty, category);
    }

    // Modifies original start method to add the questions together with the correct answers at the end of running quiz
    @Override
    public void start() {
        super.start();
        review();
    }

    // Handles question printing at the end of quiz
    public void review() {
        System.out.println("\n--- REVIEW: Correct Answers ---\n");
        for (Question q : createdQuiz) {
            System.out.println(q.getText());
            for (String option : q.getOptions()) {
                System.out.println(option);
            }
            System.out.println("Correct answer: " + q.getCorrectAnswer() + "\n");
        }
    }
}
