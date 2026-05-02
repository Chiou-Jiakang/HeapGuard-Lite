public class TestMinHeap {
    public static void main(String[] args) {
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

        event1.setRiskScore(10);
        event1.setRiskLevel("LOW");

        event2.setRiskScore(60);
        event2.setRiskLevel("HIGH");

        event3.setRiskScore(100);
        event3.setRiskLevel("CRITICAL");

        Minheap heap = new Minheap();

        heap.insert(event1);
        heap.insert(event2);
        heap.insert(event3);

        System.out.println("Heap size: " + heap.size());
        System.out.println();

        System.out.println("Extract events by priority:");
        while (!heap.isEmpty()) {
            SecurityEvent event = heap.extractMin();
            System.out.println(event.getShortSummary());
        }
    }
}
