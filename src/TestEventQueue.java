public class TestEventQueue {
    public static void main(String[] args) {
        EventQueue queue = new EventQueue();

        SecurityEvent event1 = new SecurityEvent(
                1,
                "LOGIN_FAIL",
                "admin",
                "192.168.1.10",
                "failed login attempt");

        SecurityEvent event2 = new SecurityEvent(
                2,
                "SQL_INJECTION",
                "guest",
                "10.0.0.5",
                "' OR 1=1 --");

        SecurityEvent event3 = new SecurityEvent(
                3,
                "PASSWORD_LEAK",
                "alice",
                "192.168.1.20",
                "password=123456 exposed");

        queue.enqueue(event1);
        queue.enqueue(event2);
        queue.enqueue(event3);

        System.out.println("Queue size after enqueue: " + queue.size());

        System.out.println("First event:");
        System.out.println(queue.peek().getShortSummary());

        System.out.println();
        System.out.println("Dequeue events:");

        while (!queue.isEmpty()) {
            SecurityEvent event = queue.dequeue();
            System.out.println(event.getShortSummary());
        }

        System.out.println();
        System.out.println("Queue size after dequeue: " + queue.size());
    }
}
