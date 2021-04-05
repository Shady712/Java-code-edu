package queue;

public class Main {
    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();

        fill(queue1);
        fill(queue2);

        dump(queue1);
        dump(queue2);
    }

    private static void fill(ArrayQueue queue) {
        queue.enqueue("hello");
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i * 10);
        }
        queue.enqueue("world");
    }

    private static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
