package TD2;

public class Player {
    public static final int START_LIVES = 3;
    public static final int START_RESISTANCE = 1;
    private int nbLives;
    private int resistance;
    private int nbPoints;
    public static final Player MARIO = new Player();
    private Player() {
        this.nbLives = START_LIVES;
        this.resistance = START_RESISTANCE;
        this.nbPoints = 0;
    }
    public int getNbLives() {
        return this.nbLives;
    }
    public void changeNbLives(int delta) {
        this.nbLives += delta;
    }
    public int getResistance() {
        return this.resistance;
    }
    public void modifyResistance(int delta) {
        this.resistance += delta;
    }
    public int getNbPoints() {
        return this.nbPoints;
    }
    public void addPoints(int points) {
        this.nbPoints += points;
    }
}
