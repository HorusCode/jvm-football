import java.util.*;

public class Scheduler {
    private static final int NOT_PLAYABLE_DAYS_IN_A_ROW_TO_FINISH = 3;
    private static final double COLD_BOUND = 0.0;
    private static final int DAY_GAMES_COUNT = 3;
    private Scanner scanner;
    private int notPlayableDaysInRow;
    private List<Team> allAvailableTeams;
    private List<Double> temperatures;
    private List<Game> games;

    public Scheduler() {
        this.scanner = new Scanner(System.in);
        this.allAvailableTeams = new ArrayList<>(Arrays.asList(
                new Team("Team1"), new Team("Team2"), new Team("Team3"), new Team("Team4")
        ));
        this.temperatures = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public void inputTodayTemperature() {
        System.out.print("Set today temperature (Celsius): ");
        try {
            double temp = Double.parseDouble(scanner.nextLine());
            temperatures.add(temp);
            processTemperature(temp);
        } catch (NumberFormatException e) {
            System.out.println("Temperature format is wrong. Please enter a valid number.");
        }
    }

    private void processTemperature(double temp) {
        if (temp <= COLD_BOUND) {
            System.out.printf(Locale.getDefault(), "[%.2f°C] Too cold to play.%n", temp);
            notPlayableDaysInRow++;
        } else {
            notPlayableDaysInRow = 0;
            scheduleGamesForDay(temp);
        }

    }

    private void scheduleGamesForDay(double temperature) {
        List<Team> selectedTeams = new ArrayList<>(allAvailableTeams);
        Collections.shuffle(selectedTeams);
        for (int i = 0; i < DAY_GAMES_COUNT && selectedTeams.size() > 2; i++) {
            Game game = new Game(Arrays.asList(selectedTeams.removeFirst(), selectedTeams.removeFirst()), temperature);
            games.add(game);
            game.play();
        }
        printSeasonStatistics();
    }

    public boolean isSeasonOver() {
        return notPlayableDaysInRow >= NOT_PLAYABLE_DAYS_IN_A_ROW_TO_FINISH;
    }

    public void printSeasonStatistics() {
        System.out.println("*********РЕЗУЛЬТАТЫ*********");
        for (Team team : allAvailableTeams) {
            System.out.println(team.toString());
        }
        for (Game game : games) {
            System.out.println(game.toString());
        }
        printTemperatureStatistics();
    }

    private void printTemperatureStatistics() {
        double maxTemp = Collections.max(temperatures);
        double minTemp = Collections.min(temperatures);
        double avgTemp = temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        System.out.printf(Locale.getDefault(), "Hottest Temperature: %.2f°C%nColdest Temperature: %.2f°C%nAverage Temperature: %.2f°C%n", maxTemp, minTemp, avgTemp);
    }



    public void finishSeason() {
        scanner.close();
        scanner = null;
        System.out.println("Season is over.");
        printSeasonStatistics();
    }
}