import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EventParser {

    public ArrayList<SecurityEvent> loadEventsFromFile(String filePath) {
        ArrayList<SecurityEvent> events = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int id = 1;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    SecurityEvent event = parseLine(line, id);
                    if (event != null) {
                        events.add(event);
                        id++;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error: Failed to read file: " + filePath);
            System.out.println("Reason: " + e.getMessage());
        }

        return events;
    }

    private SecurityEvent parseLine(String line, int id) {
        String[] parts = line.split(",");

        if (parts.length < 4) {
            System.out.println("Warning: Invalid event format skipped: " + line);
            return null;
        }

        String type = parts[0].trim();
        String user = "unknown";
        String ip = "unknown";
        String message = "";

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();

            if (part.startsWith("user=")) {
                user = part.substring("user=".length()).trim();
            } else if (part.startsWith("ip=")) {
                ip = part.substring("ip=".length()).trim();
            } else if (part.startsWith("message=")) {
                message = part.substring("message=".length()).trim();
            } else {
                message += " " + part;
            }
        }

        return new SecurityEvent(id, type, user, ip, message);
    }
}