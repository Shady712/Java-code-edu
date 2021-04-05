package queue;

public class MainQueue {
    public static void main(String[] args) {
        Queue queue1 = new LinkedQueue();
        Queue queue2 = new ArrayQueue();

        fill(queue1);
        fill(queue2);

        dump(queue1);
        dump(queue2);
    }

    private static void fill(Queue queue) {
        queue.enqueue("hello");
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i * 10);
        }
        queue.enqueue("world");
    }

    private static void dump(Queue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
