import java.util.List;

public class Game {
    private static int nextGameID = 1;
    private final int gameID;
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeScore, awayScore;
    private final double temperature;

    // Измененный конструктор для инициализации игры
    public Game(List<Team> teams, double temperature) {
        this.gameID = nextGameID++;
        this.homeTeam = teams.get(0);
        this.awayTeam = teams.get(1);
        this.temperature = temperature;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public int getGameID() {
        return gameID;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public double getTemperature() {
        return temperature;
    }

    public void play() {
        if (temperature <= 0) {
            System.out.println("Game ID " + gameID + ": It's too cold to play.");
            return;
        }

        simulateGame();
        updateTeamRecords();
    }

    private void simulateGame() {
        // Динамичное определение максимального количества голов в зависимости от температуры
        int maxGoals = 1 + (int)(temperature / 10);
        homeScore = (int) (Math.random() * maxGoals);
        awayScore = (int) (Math.random() * maxGoals);
    }

    private void updateTeamRecords() {
        homeTeam.addPointsScored(homeScore);
        homeTeam.addPointsAllowed(awayScore);
        awayTeam.addPointsScored(awayScore);
        awayTeam.addPointsAllowed(homeScore);

        if (homeScore > awayScore) {
            homeTeam.addWin();
            awayTeam.addLoss();
        } else if (homeScore < awayScore) {
            homeTeam.addLoss();
            awayTeam.addWin();
        } else {
            homeTeam.addTie();
            awayTeam.addTie();
        }
    }

    public String toString() {
        return "Game #" + gameID + "\nTemperature: " + temperature +
                "\nAway Team: " + awayTeam.getName() + ", " + awayScore +
                "\nHome Team: " + homeTeam.getName() + ", " + homeScore + "\n";
    }
}