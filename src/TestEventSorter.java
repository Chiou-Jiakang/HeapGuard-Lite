import java.util.ArrayList;

public class TestEventSorter {
    public static void main(String[] args) {
        ArrayList<SecurityEvent> events = new ArrayList<>();

        SecurityEvent event1 = new SecurityEvent(
                1,
                "NORMAL_LOGIN",
                "mary",
                "192.168.1.12",
                "login successful");

        SecurityEvent event2 = new SecurityEvent(
                2,
                "LOGIN_FAIL",
                "admin",
                "192.168.1.10",
                "failed login attempt");

        SecurityEvent event3 = new SecurityEvent(
                3,
                "SQL_INJECTION",
                "guest",
                "10.0.0.5",
                "' OR 1=1 --");

        SecurityEvent event4 = new SecurityEvent(
                4,
                "WEAK_PASSWORD",
                "tim",
                "local",
                "password=123456");

        event1.setRiskScore(10);
        event1.setRiskLevel("LOW");

        event2.setRiskScore(60);
        event2.setRiskLevel("HIGH");

        event3.setRiskScore(100);
        event3.setRiskLevel("CRITICAL");

        event4.setRiskScore(90);
        event4.setRiskLevel("CRITICAL");

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);

        EventSorter sorter = new EventSorter();
        ArrayList<SecurityEvent> sortedEvents = sorter.sortByRiskScore(events);

        System.out.println("Events sorted by risk score:");
        System.out.println();

        for (SecurityEvent event : sortedEvents) {
            System.out.println(event.getShortSummary());
        }
    }
}