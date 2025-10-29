
package service;

import model.Question;
import java.io.*;
import java.util.*;

// Reads and writes questions from the questions file. Loads them into memory

public class QuestionLoader {
    private String path = "data/questions.txt";
    private List<Question> list;
    private File file;

    public QuestionLoader() {
        this.list =  new ArrayList<>();
        file = new File(path);
    }

    // Reads questions from file, separates them into different fields for easy access
    public void loadQuestions() {
        StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();

                if (text.isEmpty()) {
                    continue;
                }

                String[] answers = new String[4];
                for (int i = 0; i < answers.length; i++) {
                    answers[i] = scanner.nextLine();
                }
                String correctAnswer = scanner.nextLine().trim();

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                Question q = new Question(text, answers, correctAnswer);
                list.add(q);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find " + path);
            e.printStackTrace();
        }
    }

    public List<Question> getQuestions() {
        return list;
    }

    // Writes a new question to the file
    public void addQuestion() {
            Scanner  sc = new Scanner(System.in);
            String path = "data/questions.txt";

            String questionText;
            System.out.print("Enter question text: ");
            questionText = sc.nextLine().trim();

            String[] options = new String[4];
            for(int i = 0; i < options.length; i++) {
                System.out.print("Option " + (char)(i + 65) + ": ");
                options[i] = (char)(i + 65) + ") " + sc.nextLine().trim();
            }

            System.out.print("Enter correct answer: ");
            String correctAnswer = sc.nextLine().trim();

            try (FileWriter writer = new FileWriter(path, true)) {
                writer.write(questionText + "\n");
                for (String option : options) {
                    writer.write(option + "\n");
                }
                writer.write(correctAnswer + "\n");
                writer.write("\n");
                System.out.println("Question added!");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    // Prints all questions
    public void printQuestions() {
        for (Question q : list) {
            System.out.println(q.toString());
        }
    }
}

