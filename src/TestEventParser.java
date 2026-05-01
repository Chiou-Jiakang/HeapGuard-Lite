import java.util.ArrayList;

public class TestEventParser {
    public static void main(String[] args) {
        EventParser parser = new EventParser();

        ArrayList<SecurityEvent> events = parser.loadEventsFromFile("data/sample_events.txt");

        System.out.println("Loaded events: " + events.size());
        System.out.println();

        for (SecurityEvent event : events) {
            System.out.println(event.getShortSummary());
        }
    }
}
