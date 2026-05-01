import java.util.ArrayList;

public class EventQueue {
    private ArrayList<SecurityEvent> queue;

    public EventQueue() {
        queue = new ArrayList<>();
    }

    public void enqueue(SecurityEvent event) {
        queue.add(event);
    }

    public SecurityEvent dequeue() {
        if (isEmpty()) {
            return null;
        }

        return queue.remove(0);
    }

    public SecurityEvent peek() {
        if (isEmpty()) {
            return null;
        }

        return queue.get(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }

    public ArrayList<SecurityEvent> getAllEvents() {
        return new ArrayList<>(queue);
    }
}
