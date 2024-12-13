import utils.DayRegistry;
import utils.DayRunner;

public class Main {

    public static void main(String[] args) {
        DayRegistry.setMode(true); // Latest mode
        DayRunner dayRunner = new DayRunner();
        dayRunner.executeYear("2024");
        //dayRunner.executeNewest();

        //dayRunner.executeDay(new year2022.Day06());
    }
}
