import java.util.ArrayList;

public class Minheap {
    private ArrayList<SecurityEvent> heap;

    public Minheap() {
        heap = new ArrayList<>();
    }

    public void insert(SecurityEvent event) {
        heap.add(event);
        heapifyUp(heap.size() - 1);
    }

    public SecurityEvent extractMin() {
        if (isEmpty()) {
            return null;
        }

        SecurityEvent min = heap.get(0);
        SecurityEvent last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public SecurityEvent peek() {
        if (isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap.get(index).getPriority() < heap.get(parentIndex).getPriority()) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < heap.size()
                    && heap.get(leftChild).getPriority() < heap.get(smallest).getPriority()) {
                smallest = leftChild;
            }

            if (rightChild < heap.size()
                    && heap.get(rightChild).getPriority() < heap.get(smallest).getPriority()) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        SecurityEvent temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}