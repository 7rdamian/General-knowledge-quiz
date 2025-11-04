package model.quiz;

import model.Question;
import service.QuestionLoaderApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Handles the creation and running of a quiz, calculates score, picks random questions from the loaded list

public class Quiz {
    protected QuestionLoaderApi questionLoader;
    protected List<Question> createdQuiz;
    protected int size;
    protected String difficulty;
    protected String category;
    protected String score;

    // Constructor, takes as argument the number of questions in the quiz and calls method to create it
    public Quiz(int size, String difficulty, String category) {
        this.size = size;
        this.difficulty = difficulty;
        this.category = category;

        questionLoader = new QuestionLoaderApi();
        createdQuiz = new ArrayList<>();

        questionLoader.fetchQuestions(difficulty, size, category);
        createdQuiz = questionLoader.getQuestions();
    }

    // Starts and runs the quiz
    public void start() {
        Scanner sc = new Scanner(System.in);

        int temporaryScore = 0;
        for (Question q : createdQuiz) {
            System.out.println(q.toString());
            System.out.print("Type your answer here: ");
            String answer = sc.nextLine().trim().toUpperCase();

            if (q.isCorrect(answer)) {
                temporaryScore++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! Correct answer: " + q.getCorrectAnswer() + "\n");
            }
        }
        score = temporaryScore + "/" + size;
    }

    public String getScore() {
        return score;
    }
}
