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

public class ArrayQueueModule {
    private static int head = 0, tail = 0;
    private static Object[] elements = new Object[2];

    /*
        Pred: e != null
        Post: n = n' + 1 && a[n] = e && forall i = 1..n' : a[i] == a'[i]
     */
    public static void enqueue(Object e) {
        Objects.requireNonNull(e);

        ensureCapacity(size() + 1);
        elements[tail++] = e;
        tail %= elements.length;
    }

    /*
        Pred: n > 0
        Post: R == a[n] && Immutable
     */
    public static Object element() {
        assert size() > 0;

        return elements[head];
    }

    /*
        Pred: n > 0
        Post: R == a'[1] && n = n' - 1 && forall i = 1..n : a[i] = a'[i + 1]
     */
    public static Object dequeue() {
        assert size() > 0;

        Object ans = elements[head];
        elements[head++] = null;
        head %= elements.length;
        return ans;
    }

    /*
        Pred: true
        Post: R == n && Immutable
     */
    public static int size() {
        if (head == tail) {
            return elements[head] == null ? 0 : elements.length;
        } else {
            return head < tail ? tail - head : elements.length - head + tail;
        }
    }

    /*
        Pred: true
        Post: R == (n == 0) && Immutable
     */
    public static boolean isEmpty() {
        return size() == 0;
    }

    /*
        Pred: true
        Post: n == 0
     */
    public static void clear() {
        Arrays.fill(elements, null);
        head = tail = 0;
    }

    /*
        Pred: e != null
        Post: n = n' + 1 && a[1] = e && forall i = 1..n' a'[i] = a[i + 1]
     */
    public static void push(Object e) {
        Objects.requireNonNull(e);

        ensureCapacity(size() + 1);
        head--;
        if (head == -1) {
            head = elements.length - 1;
        }
        elements[head] = e;
    }

    /*
        Pred: n > 0
        Post: R == a[n] && Immutable
     */
    public static Object peek() {
        assert size() > 0;

        return tail == 0 ? elements[elements.length - 1] : elements[tail - 1];
    }

    /*
        Pred: n > 0
        Post: R == a'[n] && n = n' - 1 && forall: i = 1..n : a[i] == a'[i]
     */
    public static Object remove() {
        assert size() > 0;

        tail--;
        if (tail == -1) {
            tail = elements.length - 1;
        }
        Object ans = elements[tail];
        elements[tail] = null;
        return ans;
    }

    /*
        Pred: true
        Post: R = a && Immutable
     */
    public static Object[] toArray() {
        return getArray(size());
    }

    /*
        Pred: true
        Post: R = a to String && Immutable
     */
    public static String toStr() {
        return Arrays.toString(toArray());
    }

    private static void ensureCapacity(final int capacity) {
        if (elements.length < capacity) {
            final int size = size();
            elements = getArray(capacity * 2);
            head = 0;
            tail = size;
        }
    }

    private static Object[] getArray(final int capacity) {
        Object[] ans = new Object[capacity];
        if (capacity == 0) {
            return ans;
        }

        if (head < tail) {
            System.arraycopy(elements, head, ans, 0, tail - head);
        } else {
            System.arraycopy(elements, head, ans, 0, elements.length - head);
            System.arraycopy(elements, 0, ans, elements.length - head, tail);
        }
        return ans;
    }
}
