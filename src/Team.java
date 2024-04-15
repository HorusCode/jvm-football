import java.util.Locale;

public class Team {
    private String name;
    private int wins, losses, ties, pointsScored, pointsAllowed;

    public Team(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
        this.pointsScored = 0;
        this.pointsAllowed = 0;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public int getPointsScored() {
        return pointsScored;
    }

    public int getPointsAllowed() {
        return pointsAllowed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWin()
    {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public void addTie() {
        ties++;
    }

    public void addPointsScored(int points) {
        pointsScored += points;
    }

    public void addPointsAllowed(int points) {
        pointsAllowed += points;
    }


    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s\nWins: %d, Losses: %d, Ties: %d\nTotal goals: %d, Goals Allowed: %d\n",
                getName(), getWins(), getLosses(), getTies(), getPointsScored(), getPointsAllowed());
    }
}
