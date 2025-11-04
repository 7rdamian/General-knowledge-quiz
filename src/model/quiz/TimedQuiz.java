package model.quiz;

import model.Question;

import java.util.Scanner;

// Child of class Quiz, adds a time element to the game

public class TimedQuiz extends Quiz {
    private int timeLimit;

    public TimedQuiz(int size, String difficulty, String category, int timeLimit) {
        super(size, difficulty, category);
        this.timeLimit = timeLimit;
    }

    // Asks user for a time limit to quiz and ends the game if time is reached
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Timed Quiz! You have " + timeLimit + " seconds for the entire quiz.\n");

        int temporaryScore = 0;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeLimit * 1000L;

        for (Question q : super.createdQuiz) {
            if (System.currentTimeMillis() > endTime) {
                System.out.println("\nTime's up! Quiz ended.");
                break;
            }

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
}
