package model;

import service.QuestionLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Handles the creation and running of a quiz, calculates score, picks random questions from the loaded list

public class Quiz {
    protected QuestionLoader questionLoader;
    protected List<Question> questionList;
    protected List<Question> createdQuiz;
    protected int size;
    protected String score;

    // Constructor, takes as argument the number of questions in the quiz and calls method to create it
    public Quiz(int size) {
        this.size = size;
        this.createdQuiz = new ArrayList<>();

        this.questionLoader = new QuestionLoader();
        questionLoader.loadQuestions();
        this.questionList = questionLoader.getQuestions();

        this.createdQuiz = createQuiz();
    }

    // Creates quiz by picking random questions from the existing list
    public List<Question> createQuiz() {
        Random rand = new Random();
        List<Question> copy = new ArrayList<>(questionList);
        createdQuiz.clear();

        for (int i = 0; i < Math.min(size, copy.size()); i++) {
            int randomIndex = rand.nextInt(copy.size());
            createdQuiz.add(copy.get(randomIndex));
            copy.remove(randomIndex);
        }
        return createdQuiz;
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
