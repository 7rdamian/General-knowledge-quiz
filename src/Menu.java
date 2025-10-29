import java.util.Scanner;

import service.Leaderboard;
import model.Player;
import service.QuestionLoader;
import model.Quiz;
import model.TimedQuiz;
import model.ReviewQuiz;

// This class displays the menu and handles the user input

public class Menu {
    private Quiz quiz;
    private Leaderboard leaderboard;
    private QuestionLoader questionLoader;
    private Player player;

    public Menu() {
        this.leaderboard = new Leaderboard();
        this.questionLoader = new QuestionLoader();
    }

    public void showMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            // Nice header
            System.out.println("\n==================================");
            System.out.println("     GENERAL KNOWLEDGE QUIZ       ");
            System.out.println("==================================\n");

            // Menu options
            System.out.println("1) Start a New Quiz");
            System.out.println("2) Start a Timed Quiz");
            System.out.println("3) Start a Review Quiz");
            System.out.println("4) View Leaderboard");
            System.out.println("5) Add a New Question");
            System.out.println("6) Exit");

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
                    System.out.print("Enter the number of questions: ");
                    int numQuestions = scanner.nextInt();
                    scanner.nextLine();
                    quiz = new Quiz(numQuestions);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // Timed Quiz
                case 2:
                    System.out.print("Enter the number of questions: ");
                    int timedQuestions = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the total time limit for the quiz (seconds): ");
                    int timeLimit = scanner.nextInt();
                    scanner.nextLine();
                    quiz = new TimedQuiz(timedQuestions, timeLimit);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // Review Quiz
                case 3:
                    System.out.print("Enter the number of questions: ");
                    int reviewQuestions = scanner.nextInt();
                    scanner.nextLine();
                    quiz = new ReviewQuiz(reviewQuestions);
                    runQuizAndSavePlayer(scanner, quiz);
                    break;

                // View leaderboard
                case 4:
                    System.out.println(leaderboard.toString());
                    break;

                // Add a new question
                case 5:
                    questionLoader.addQuestion();
                    break;

                // Exit
                case 6:
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
