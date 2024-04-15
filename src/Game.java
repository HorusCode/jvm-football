public class Game {
    private int gameID;
    private Team homeTeam, awayTeam;
    private int homeScore, awayScore;
    private int temperature;

    // Конструктор для инициализации игры
    public Game(int gameID, Team homeTeam, Team awayTeam, int temperature) {
        this.gameID = gameID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
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

    public int getTemperature() {
        return temperature;
    }


    public void playGame() {
        // Генерация случайного результата на основе температуры
        if (temperature > 0) { // Игра проходит, если температура выше 0
            homeScore = (int) (Math.random() * (temperature / 10 + 1)); // Счет пропорционален температуре
            awayScore = (int) (Math.random() * (temperature / 10 + 1));
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
    }
}
