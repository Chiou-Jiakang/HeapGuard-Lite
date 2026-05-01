public class TestEventAnalyzer {
    public static void main(String[] args) {
        SecurityEvent event = new SecurityEvent(
                1,
                "LOGIN_FAIL",
                "admin",
                "192.168.1.10",
                "failed login attempt with password=123456");

        EventAnalyzer analyzer = new EventAnalyzer();
        analyzer.analyze(event);

        System.out.println(event.getDetailedSummary());
    }
}
