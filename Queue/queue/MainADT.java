package queue;

public class MainADT {
    public static void main(String[] args) {
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        ArrayQueueADT queue2 = ArrayQueueADT.create();

        fill(queue1);
        fill(queue2);

        dump(queue1);
        dump(queue2);
    }

    private static void fill(ArrayQueueADT queue) {
        ArrayQueueADT.enqueue(queue, "hello");
        for (int i = 0; i < 5; i++) {
            ArrayQueueADT.enqueue(queue, i * 10);
        }
        ArrayQueueADT.enqueue(queue, "world");
    }

    private static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(ArrayQueueADT.dequeue(queue));
        }
    }
}
