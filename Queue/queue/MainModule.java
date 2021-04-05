package queue;

public class MainModule {
    public static void main(String[] args) {
        ArrayQueueModule.enqueue("hello");
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        ArrayQueueModule.enqueue("world");
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue());
        }
    }
}
