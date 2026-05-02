import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGenerator {

    public void exportReport(
            ArrayList<SecurityEvent> events,
            ArrayList<SecurityEvent> sortedEvents,
            String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("HeapGuard Lite Report");
            writer.newLine();
            writer.write("=====================");
            writer.newLine();
            writer.newLine();

            writer.write("Total Events: " + events.size());
            writer.newLine();
            writer.newLine();

            writer.write("Full Risk Ranking:");
            writer.newLine();

            for (int i = 0; i < sortedEvents.size(); i++) {
                SecurityEvent event = sortedEvents.get(i);
                writer.write((i + 1) + ". " + event.getShortSummary());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Detailed Event Information:");
            writer.newLine();
            writer.write("===========================");
            writer.newLine();

            for (SecurityEvent event : sortedEvents) {
                writer.newLine();
                writer.write(event.getDetailedSummary());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Complexity Summary:");
            writer.newLine();
            writer.write("- EventParser file loading: O(n)");
            writer.newLine();
            writer.write("- EventAnalyzer risk scoring: O(n * m)");
            writer.newLine();
            writer.write("- MinHeap insertion: O(log n)");
            writer.newLine();
            writer.write("- MinHeap extraction: O(log n)");
            writer.newLine();
            writer.write("- Top-K extraction: O(k log n)");
            writer.newLine();
            writer.write("- Merge Sort: O(n log n)");
            writer.newLine();
            writer.newLine();
            writer.write("n = number of events");
            writer.newLine();
            writer.write("m = length of an event message");
            writer.newLine();
            writer.write("k = number of top alerts");
            writer.newLine();

            writer.close();

            System.out.println("Report exported to " + filePath);

        } catch (IOException e) {
            System.out.println("Error: Failed to export report.");
            System.out.println("Reason: " + e.getMessage());
        }
    }
}