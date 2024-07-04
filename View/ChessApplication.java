package View;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Model.chess.ChessGameService;
import Model.chess.PlayerManagerService;

import java.io.IOException;

public class ChessApplication {
    private static final Logger logger = Logger.getLogger(ChessApplication.class.getName());

    

    public static void main(String[] args) {
        logger.info("Program started");

        Scanner sc = new Scanner(System.in);
        PlayerManagerService playerManagerService = new PlayerManagerService();
        ChessGameService chessGameService = new ChessGameService();

        while (true) {
            System.out.println("Chess Game Menu:");
            System.out.println("1. Player Management");
            System.out.println("2. Start Chess Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (mainChoice) {
                    case 1:
                        playerManagerService.managePlayers(sc);
                        break;
                    case 2:
                        chessGameService.startChessGame(sc);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        sc.close();
                        logger.info("Program finished");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "An error occurred during player management", e);
                System.err.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An unexpected error occurred", e);
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
