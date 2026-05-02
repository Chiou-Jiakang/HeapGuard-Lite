import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private EventQueue eventQueue;
    private ArrayList<SecurityEvent> loadedEvents;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        eventQueue = new EventQueue();
        loadedEvents = new ArrayList<>();
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readChoice();

            switch (choice) {
                case 1:
                    loadSampleEvents();
                    break;

                case 2:
                    addCustomEvent();
                    break;

                case 3:
                    analyzeEvents();
                    break;

                case 4:
                    showTopKAlerts();
                    break;

                case 5:
                    showFullRanking();
                    break;

                case 6:
                    exportReport();
                    break;

                case 7:
                    showComplexitySummary();
                    break;

                case 0:
                    System.out.println("Exiting HeapGuard Lite.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 0-7.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println(" HeapGuard Lite");
        System.out.println("====================================");
        System.out.println("1. Load sample events");
        System.out.println("2. Add custom event");
        System.out.println("3. Analyze events");
        System.out.println("4. Show Top-K alerts");
        System.out.println("5. Show full ranking");
        System.out.println("6. Export report");
        System.out.println("7. Show complexity summary");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private int readChoice() {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Choose an option: ");
            }
        }
    }

    private void loadSampleEvents() {
        EventParser parser = new EventParser();

        loadedEvents = parser.loadEventsFromFile("data/sample_events.txt");

        eventQueue.clear();

        for (SecurityEvent event : loadedEvents) {
            eventQueue.enqueue(event);
        }

        System.out.println("Loaded " + loadedEvents.size() + " events into the event queue.");
    }

    private void addCustomEvent() {
        System.out.println("Add custom event selected.");
    }

    private void analyzeEvents() {
        if (loadedEvents.isEmpty()) {
            System.out.println("No events loaded. Please load sample events first.");
            return;
        }

        EventAnalyzer analyzer = new EventAnalyzer();

        for (SecurityEvent event : loadedEvents) {
            analyzer.analyze(event);
        }

        System.out.println("Analyzed " + loadedEvents.size() + " events.");
    }

    private void showTopKAlerts() {
        if (loadedEvents.isEmpty()) {
            System.out.println("No events loaded. Please load sample events first.");
            return;
        }

        boolean hasAnalyzedEvents = false;

        for (SecurityEvent event : loadedEvents) {
            if (!event.getRiskLevel().equals("UNKNOWN")) {
                hasAnalyzedEvents = true;
                break;
            }
        }

        if (!hasAnalyzedEvents) {
            System.out.println("Events have not been analyzed yet. Please analyze events first.");
            return;
        }

        Minheap heap = new Minheap();

        for (SecurityEvent event : loadedEvents) {
            heap.insert(event);
        }

        int k = 5;
        int count = Math.min(k, heap.size());

        System.out.println();
        System.out.println("Top " + count + " High-Risk Alerts:");

        for (int i = 1; i <= count; i++) {
            SecurityEvent event = heap.extractMin();
            System.out.println(i + ". " + event.getShortSummary());
        }
    }

    private void showFullRanking() {
        System.out.println("Show full ranking selected.");
    }

    private void exportReport() {
        System.out.println("Export report selected.");
    }

    private void showComplexitySummary() {
        System.out.println();
        System.out.println("Complexity Summary:");
        System.out.println("- Queue enqueue/dequeue: O(1) or O(n), depending on implementation");
        System.out.println("- Current EventQueue dequeue with ArrayList.remove(0): O(n)");
        System.out.println("- MinHeap insertion: O(log n)");
        System.out.println("- MinHeap extraction: O(log n)");
        System.out.println("- Top-K extraction: O(k log n)");
        System.out.println("- Merge Sort: O(n log n)");
        System.out.println("- Stack bracket checking: O(m)");
        System.out.println();
        System.out.println("n = number of events");
        System.out.println("k = number of top alerts");
        System.out.println("m = length of an event message");
    }
}