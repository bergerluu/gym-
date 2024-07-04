package Model.chess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static final String FILE_NAME = "players.txt";

    public void createPlayer(Player player) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(player.toString());
            writer.newLine();
        }
    }

    public List<Player> readAllPlayers() throws IOException {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                players.add(Player.fromString(line));
            }
        }
        return players;
    }

    public Player readPlayerById(int id) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Player player = Player.fromString(line);
                if (player.getId() == id) {
                    return player;
                }
            }
        }
        return null;
    }

    public void updatePlayer(Player updatedPlayer) throws IOException {
        List<Player> players = readAllPlayers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Player player : players) {
                if (player.getId() == updatedPlayer.getId()) {
                    writer.write(updatedPlayer.toString());
                } else {
                    writer.write(player.toString());
                }
                writer.newLine();
            }
        }
    }

    public void deletePlayer(int id) throws IOException {
        List<Player> players = readAllPlayers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Player player : players) {
                if (player.getId() != id) {
                    writer.write(player.toString());
                    writer.newLine();
                }
            }
        }
    }
}
