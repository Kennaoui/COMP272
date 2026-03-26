import java.util.PriorityQueue;

class StableElement implements Comparable<StableElement> {
    int value;
    int priority;
    long order; // insertion order

    public StableElement(int value, int priority, long order) {
        this.value = value;
        this.priority = priority;
        this.order = order;
    }

    @Override
    public int compareTo(StableElement other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        return Long.compare(this.order, other.order); // tie-breaker
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue<StableElement> pq = new PriorityQueue<>();
        long counter = 0;

        pq.add(new StableElement(10, 1, counter++));
        pq.add(new StableElement(20, 1, counter++));
        pq.add(new StableElement(30, 1, counter++));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll().value);
        }
    }
}
