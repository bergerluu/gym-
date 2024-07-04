package Model.chess;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class PlayerManagerService {

    private PlayerManager playerManager = new PlayerManager();

    public void managePlayers(Scanner sc) throws IOException {
        while (true) {
            System.out.println("Player Management Menu:");
            System.out.println("1. Create Player");
            System.out.println("2. Read All Players");
            System.out.println("3. Update Player");
            System.out.println("4. Delete Player");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter player ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter player name: ");
                    String name = sc.nextLine();
                    Player newPlayer = new Player(id , name);
                    playerManager.createPlayer(newPlayer);
                    System.out.println("Player created successfully.");
                    UI.clearScreen();
                    break;
                case 2:
                    List<Player> players = playerManager.readAllPlayers();
                    for (Player player : players) {
                        System.out.println(player);
                    }
                    break;
                case 3:
                    System.out.print("Enter player ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new player name: ");
                    String newName = sc.nextLine();
                    Player updatedPlayer = new Player(updateId, newName);
                    playerManager.updatePlayer(updatedPlayer);
                    System.out.println("Player updated successfully.");
                    UI.clearScreen();
                    break;
                case 4:
                    System.out.print("Enter player ID to delete: ");
                    int deleteId = sc.nextInt();
                    playerManager.deletePlayer(deleteId);
                    System.out.println("Player deleted successfully.");
                    UI.clearScreen();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}