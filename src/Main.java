public class Main {
    public static void main(String[] args) {
        // Создадим экземпляр планировщика
        Scheduler scheduler = new Scheduler();


        while (!scheduler.isSeasonOver())
            scheduler.inputTodayTemperature();


        scheduler.finishSeason();
    }
}