public class TestSecurityEvent {
    public static void main(String[] args) {
        SecurityEvent event = new SecurityEvent(
                1,
                "LOGIN_FAIL",
                "admin",
                "192.168.1.10",
                "failed login attempt");

        event.setRiskScore(60);
        event.setRiskLevel("HIGH");
        event.addReason("Failed login event detected.");
        event.addReason("Privileged account detected: admin.");

        System.out.println(event.getDetailedSummary());
    }
}
