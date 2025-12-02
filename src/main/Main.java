import utils.DayRegistry;
import utils.DayRunner;

public class Main {

    public static void main(String[] args) {
        DayRegistry.setMode(true);
        DayRunner dayRunner = new DayRunner();
        //dayRunner.executeYear("2025");
        dayRunner.executeNewest();
    }
}
