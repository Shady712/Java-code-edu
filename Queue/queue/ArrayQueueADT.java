package queue;

import java.util.Arrays;
import java.util.Objects;

/*
    Model:
        [a1, a2, ... an]
        n -- size of queue

    Inv:
        n >= 0
        forall i = 1..n: a[i] != null

    Let's define Immutable: n == n' && forall i = 1..n : a[i] = a'[i]
 */

public class ArrayQueueADT {
    private int head = 0, tail = 0;
    private Object[] elements = new Object[2];

    /*
        Pred: true
        Post: R.n == 0 && R is a new structure
     */
    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    /*
        Pred: queue != null && e != null
        Post: n = n' + 1 && a[n] = e && forall i = 1..n' : a[i] == a'[i]
     */
    public static void enqueue(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(queue);
        Objects.requireNonNull(e);

        ensureCapacity(queue, size(queue) + 1);
        queue.elements[queue.tail++] = e;
        queue.tail %= queue.elements.length;
    }

    /*
        Pred: queue != null && n > 0
        Post: R == a[n] && Immutable
     */
    public static Object element(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;

        return queue.elements[queue.head];
    }

    /*
        Pred: queue != null && n > 0
        Post: R == a'[1] && n = n' - 1 && forall i = 1..n : a[i] = a'[i + 1]
     */
    public static Object dequeue(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;

        Object ans = queue.elements[queue.head];
        queue.elements[queue.head++] = null;
        queue.head %= queue.elements.length;
        return ans;
    }

    /*
        Pred: queue != null
        Post: R == n && Immutable
     */
    public static int size(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        if (queue.head == queue.tail) {
            return queue.elements[queue.head] == null ? 0 : queue.elements.length;
        } else {
            return queue.head < queue.tail ? queue.tail - queue.head : queue.elements.length - queue.head + queue.tail;
        }
    }

    /*
        Pred: queue != null
        Post: R == (n == 0) && Immutable
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        return size(queue) == 0;
    }

    /*
        Pred: queue != null
        Post: n == 0
     */
    public static void clear(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        Arrays.fill(queue.elements, null);
        queue.head = queue.tail = 0;
    }

    /*
        Pred: queue != null && e != null
        Post: n = n' + 1 && a[1] = e && forall i = 1..n' a'[i] = a[i + 1]
     */
    public static void push(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(queue);
        Objects.requireNonNull(e);

        ensureCapacity(queue, size(queue) + 1);
        queue.head--;
        if (queue.head == -1) {
            queue.head = queue.elements.length - 1;
        }
        queue.elements[queue.head] = e;
    }

    /*
        Pred: queue != null && n > 0
        Post: R == a[n] && Immutable
     */
    public static Object peek(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;

        return queue.tail == 0 ? queue.elements[queue.elements.length - 1] : queue.elements[queue.tail - 1];
    }

    /*
        Pred: queue != null && n > 0
        Post: R == a'[n] && n = n' - 1 && forall: i = 1..n : a[i] == a'[i]
     */
    public static Object remove(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert size(queue) > 0;

        queue.tail--;
        if (queue.tail == -1) {
            queue.tail = queue.elements.length - 1;
        }
        Object ans = queue.elements[queue.tail];
        queue.elements[queue.tail] = null;
        return ans;
    }

    /*
        Pred: queue != null
        Post: R = a && Immutable
     */
    public static Object[] toArray(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        return getArray(queue, size(queue));
    }

    /*
        Pred: queue != null
        Post: R = a to String && Immutable
     */
    public static String toStr(ArrayQueueADT queue) {
        return Arrays.toString(toArray(queue));
    }

    private static void ensureCapacity(ArrayQueueADT queue, final int capacity) {
        if (queue.elements.length < capacity) {
            final int size = size(queue);
            queue.elements = getArray(queue, capacity * 2);
            queue.head = 0;
            queue.tail = size;
        }
    }

    private static Object[] getArray(ArrayQueueADT queue, final int capacity) {
        Object[] ans = new Object[capacity];
        if (capacity == 0) {
            return ans;
        }

        if (queue.head < queue.tail) {
            System.arraycopy(queue.elements, queue.head, ans, 0, queue.tail - queue.head);
        } else {
            System.arraycopy(queue.elements, queue.head, ans, 0, queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, ans, queue.elements.length - queue.head, queue.tail);
        }
        return ans;
    }
}
