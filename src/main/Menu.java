package main;

import java.util.Scanner;

import service.Leaderboard;
import model.Player;
import model.quiz.Quiz;
import model.quiz.TimedQuiz;
import model.quiz.ReviewQuiz;

// This class displays the menu and handles the user input

public class Menu {
    private Quiz quiz;
    private Leaderboard leaderboard;
    private Player player;
    private int numQuestions;
    private String category;
    private String difficulty;

    public Menu() {
        this.leaderboard = new Leaderboard();
    }

    public void showMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            // Nice header
            System.out.println("\n==================================");
            System.out.println("     GENERAL KNOWLEDGE QUIZ       ");
            System.out.println("==================================\n");

            // main.Menu options
            System.out.println("1) Start a New Quiz");
            System.out.println("2) Start a Timed Quiz");
            System.out.println("3) Start a Review Quiz");
            System.out.println("4) View Leaderboard");
            System.out.println("5) Exit");

            // Input with safety check
            System.out.print("\nEnter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\n----------------------------------\n");

            switch (choice) {
                // Normal Quiz
                case 1:
                    readParameters(scanner);
                    quiz = new Quiz(numQuestions, difficulty, category);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // Timed Quiz
                case 2:
                    readParameters(scanner);
                    System.out.print("Enter the total time limit for the quiz (seconds): ");
                    int timeLimit = scanner.nextInt();
                    scanner.nextLine();
                    quiz = new TimedQuiz(numQuestions, difficulty, category, timeLimit);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // Review Quiz
                case 3:
                    readParameters(scanner);
                    quiz = new ReviewQuiz(numQuestions, difficulty, category);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // View leaderboard
                case 4:
                    System.out.println(leaderboard.toString());
                    break;

                // Exit
                case 5:
                    System.out.println("Thank you for playing!");
                    System.exit(0);
                    break;

                // Handles wrong input
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            clearScreen(scanner);
        } while (choice != 6);
    }

    public void readParameters(Scanner scanner) {
        System.out.print("Enter the number of questions: ");
        numQuestions = scanner.nextInt();
        scanner.nextLine();
        if (numQuestions < 1 || numQuestions > 100) {
            System.out.println("Invalid number of questions");
            readParameters(scanner);
        }

        System.out.print("Enter the difficulty (easy, medium, hard): ");
        difficulty = scanner.nextLine();
        if (difficulty != "easy" && category != "medium" && category != "hard") {
            System.out.println("Invalid difficulty");
            readParameters(scanner);
        }

        System.out.print("Enter the category (\n" +
                "9: General Knowledge\n" +
                "10: Entertainment: Books\n" +
                "11: Entertainment: Film\n" +
                "12: Entertainment: Music\n" +
                "13: Entertainment: Musicals & Theatres\n" +
                "14: Entertainment: Television\n" +
                "15: Entertainment: Video Games\n" +
                "16: Entertainment: Board Games\n" +
                "17: Science & Nature\n" +
                "18: Science: Computers\n" +
                "19: Science: Mathematics\n" +
                "20: Mythology\n" +
                "21: Sports\n" +
                "22: Geography\n" +
                "23: History\n" +
                "24: Politics\n" +
                "25: Art\n" +
                "26: Celebrities\n" +
                "27: Animals\n" +
                "28: Vehicles\n" +
                "29: Entertainment: Comics\n" +
                "30: Science: Gadgets\n" +
                "31: Entertainment: Japanese Anime & Manga\n" +
                "32: Entertainment: Cartoon & Animations\n" +
                "): ");

        category = scanner.nextLine();
        if (Integer.parseInt(category) < 9 || Integer.parseInt(category) > 32) {
            System.out.println("Invalid category");
            readParameters(scanner);
        }

        System.out.println();
    }

    // Starts the quiz and saves player data
    private void runQuizAndSavePlayer(Scanner scanner, Quiz quiz) {
        quiz.start();
        System.out.println("Your score is: " + quiz.getScore());

        player = new Player();
        System.out.print("Enter your name: ");
        player.setName(scanner.nextLine());
        player.setScore(quiz.getScore());

        leaderboard.savePlayer(player);
    }

    // Clears the screen by printing 50 new lines
    public static void clearScreen(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
