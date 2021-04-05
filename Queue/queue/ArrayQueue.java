package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue extends AbstractQueue {
    private int head = 0, tail = 0;
    private Object[] elements = new Object[2];

    @Override
    protected void enqueueImpl(Object e) {
        ensureCapacity(size() + 1);
        elements[tail++] = e;
        tail %= elements.length;
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }

    @Override
    protected Object dequeueImpl() {
        Object ans = elements[head];
        elements[head++] = null;
        head %= elements.length;
        return ans;
    }

    @Override
    public int size(ArrayQueue this) {
        if (head == tail) {
            return elements[head] == null ? 0 : elements.length;
        } else {
            return head < tail ? tail - head : elements.length - head + tail;
        }
    }

    /*
        Pred: ArrayQueue != null && e != null
        Post: n = n' + 1 && a[1] = e && forall i = 1..n' a'[i] = a[i + 1]
     */
    public void push(ArrayQueue this, Object e) {
        Objects.requireNonNull(e);

        ensureCapacity(size() + 1);
        head--;
        if (head == -1) {
            head = elements.length - 1;
        }
        elements[head] = e;
    }

    /*
        Pred: ArrayQueue != null && n > 0
        Post: R == a[n] && Immutable
     */
    public Object peek(ArrayQueue this) {
        assert size() > 0;

        return tail == 0 ? elements[elements.length - 1] : elements[tail - 1];
    }

    /*
        Pred: ArrayQueue != null && n > 0
        Post: R == a'[n] && n = n' - 1 && forall: i = 1..n : a[i] == a'[i]
     */
    public Object remove(ArrayQueue this) {
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
        Pred: ArrayQueue != null
        Post: R = a && Immutable
     */
    public Object[] toArray(ArrayQueue this) {
        return getArray(size());
    }

    /*
        Pred: ArrayQueue != null
        Post: R = a to String && Immutable
     */
    public String toStr(ArrayQueue this) {
        return Arrays.toString(toArray());
    }

    private void ensureCapacity(ArrayQueue this, final int capacity) {
        if (elements.length < capacity) {
            final int size = size();
            elements = getArray(capacity * 2);
            head = 0;
            tail = size;
        }
    }

    private Object[] getArray(ArrayQueue this, final int capacity) {
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

    @Override
    protected ArrayQueue create() {
        return new ArrayQueue();
    }
}
