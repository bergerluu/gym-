package Controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Model.chess.ChessException;
import Model.chess.ChessMatch;
import Model.chess.ChessPiece;
import Model.chess.ChessPosition;
import Model.chess.Player;
import Model.chess.PlayerManager;
import Model.chess.UI;

public class ChessGameService {
    private static final Logger logger = Logger.getLogger(ChessGameService.class.getName());
    private PlayerManager playerManager = new PlayerManager();

    static {
        try {
            // Configurar o FileHandler para registrar logs em um arquivo
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO); // Definir o nível de logging desejado
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize logger handler", e);
        }
    }

    public void startChessGame(Scanner sc) throws IOException {
        System.out.print("Enter ID for Player 1 (White): ");
        int player1Id = sc.nextInt();
        Player player1 = playerManager.readPlayerById(player1Id);
        if (player1 == null) {
            System.out.println("Player 1 not found. Please create the player first.");
            return;
        }

        System.out.print("Enter ID for Player 2 (Black): ");
        int player2Id = sc.nextInt();
        Player player2 = playerManager.readPlayerById(player2Id);
        if (player2 == null) {
            System.out.println("Player 2 not found. Please create the player first.");
            return;
        }

        ChessMatch chessMatch = new ChessMatch();
        chessMatch.setWhitePlayer(player1);
        chessMatch.setBlackPlayer(player2);

        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);

                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);
                logger.info("Source position read: " + source);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                logger.info("Target position read: " + target);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                logger.info("Move performed from " + source + " to " + target);

                fileMove(chessMatch.getWhitePlayer(), chessMatch.getBlackPlayer(), source, target);


                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                    logger.info("Captured piece: " + capturedPiece);
                }

            } catch (ChessException e) {
                logger.log(Level.WARNING, "ChessException occurred", e);
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                logger.log(Level.WARNING, "InputMismatchException occurred", e);
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
        logger.info("Checkmate! Game over.");
    }

    private void fileMove(Player player1, Player player2, ChessPosition source, ChessPosition target) {
        try (FileWriter fw = new FileWriter("chess_moves.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("Player 1 (White): " + player1.getName() + " (ID: " + player1.getId() + ")");
            pw.println("Player 2 (Black): " + player2.getName() + " (ID: " + player2.getId() + ")");
            pw.println("Move from " + source + " to " + target);
            pw.println();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to log move", e);
        }
    }
}


