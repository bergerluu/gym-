package Model.chess;

public class Player {
    private int id;
    private String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }

    public static Player fromString(String playerString) {
        String[] parts = playerString.split(",");
        return new Player(Integer.parseInt(parts[0]), parts[1]);
    }
}
