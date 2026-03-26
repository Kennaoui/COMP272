import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * PriorityQueue in Java
 *
 * Important:
 * - A PriorityQueue is a heap-based data structure.
 * - By default, Java's PriorityQueue is a min-heap.
 * - Iterating over a PriorityQueue does NOT show elements in sorted order.
 * - To retrieve elements in priority order, repeatedly use poll().
 */

class StableElement implements Comparable<StableElement> {
    int value;
    int priority;
    long order;   // used to break ties in insertion order

    public StableElement(int value, int priority, long order) {
        this.value = value;
        this.priority = priority;
        this.order = order;
    }

    @Override
    public int compareTo(StableElement other) {
        // First compare by priority
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }

        // If priorities are equal, compare by insertion order
        // This makes equal-priority elements come out in the order they were inserted
        return Long.compare(this.order, other.order);
    }

    @Override
    public String toString() {
        return "(value=" + value + ", priority=" + priority + ", order=" + order + ")";
    }
}

class Student {
    String name;
    int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Different ways to create a PriorityQueue ===\n");

        // 1) Default min-heap
        // Smallest element has the highest priority
        PriorityQueue<Integer> defaultPQ = new PriorityQueue<>();

        // 2) Specify initial capacity
        // This does NOT limit the size; it only gives an initial internal capacity
        PriorityQueue<Integer> capacityPQ = new PriorityQueue<>(50);

        // 3) Max-heap using a Comparator
        // Largest element has the highest priority
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

        // 4) Create from a collection
        // The queue is built from the given collection
        PriorityQueue<String> fromCollectionPQ = new PriorityQueue<>(List.of("b", "c", "a"));

        // 5) Custom object ordering with a Comparator
        // Here, higher grade comes first; if grades are equal, compare by name
        PriorityQueue<Student> studentPQ = new PriorityQueue<>(
            (a, b) -> {
                if (a.getGrade() != b.getGrade()) {
                    return Integer.compare(b.getGrade(), a.getGrade()); // descending by grade
                }
                return a.getName().compareTo(b.getName()); // ascending by name
            }
        );

        // 6) Custom object ordering using Comparable
        // The class StableElement defines its own ordering in compareTo()
        PriorityQueue<StableElement> stablePQ = new PriorityQueue<>();


        System.out.println("=== Adding elements ===\n");

        defaultPQ.add(40);    // add() inserts an element
        defaultPQ.offer(10);  // offer() also inserts an element
        defaultPQ.add(30);
        defaultPQ.add(20);

        System.out.println("defaultPQ after adding elements: " + defaultPQ);
        System.out.println();


        System.out.println("=== Basic operations ===\n");

        // peek() returns the head without removing it
        System.out.println("peek(): " + defaultPQ.peek());

        // poll() removes and returns the head
        System.out.println("poll(): " + defaultPQ.poll());

        System.out.println("After poll(), queue is now: " + defaultPQ);

        // remove(x) removes a specific element if it exists
        boolean removed = defaultPQ.remove(30);
        System.out.println("remove(30): " + removed);
        System.out.println("After remove(30), queue is now: " + defaultPQ);
        System.out.println();


        System.out.println("=== Iteration is NOT sorted ===\n");

        // Important:
        // Iterating through a PriorityQueue does not guarantee sorted order
        System.out.println("Iterating through defaultPQ:");
        for (int val : defaultPQ) {
            System.out.println(val);
        }
        System.out.println();

        System.out.println("To retrieve elements in priority order, repeatedly poll():");
        while (!defaultPQ.isEmpty()) {
            System.out.println(defaultPQ.poll());
        }
        System.out.println();


        
    }
}
