import java.util.ArrayList;

public class EventSorter {

    public ArrayList<SecurityEvent> sortByRiskScore(ArrayList<SecurityEvent> events) {
        ArrayList<SecurityEvent> copiedEvents = new ArrayList<>(events);
        return mergeSort(copiedEvents);
    }

    private ArrayList<SecurityEvent> mergeSort(ArrayList<SecurityEvent> events) {
        if (events.size() <= 1) {
            return events;
        }

        int mid = events.size() / 2;

        ArrayList<SecurityEvent> left = new ArrayList<>();
        ArrayList<SecurityEvent> right = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            left.add(events.get(i));
        }

        for (int i = mid; i < events.size(); i++) {
            right.add(events.get(i));
        }

        ArrayList<SecurityEvent> sortedLeft = mergeSort(left);
        ArrayList<SecurityEvent> sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    private ArrayList<SecurityEvent> merge(ArrayList<SecurityEvent> left, ArrayList<SecurityEvent> right) {
        ArrayList<SecurityEvent> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            SecurityEvent leftEvent = left.get(i);
            SecurityEvent rightEvent = right.get(j);

            if (leftEvent.getRiskScore() >= rightEvent.getRiskScore()) {
                result.add(leftEvent);
                i++;
            } else {
                result.add(rightEvent);
                j++;
            }
        }

        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }
}