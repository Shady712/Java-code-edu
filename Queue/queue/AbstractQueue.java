package queue;

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

public abstract class AbstractQueue implements Queue {

    /*
        Pred: AbstractQueue != null && e != null
        Post: n = n' + 1 && a[n] = e && forall i = 1..n' : a[i] == a'[i]
     */
    @Override
    public void enqueue(Object e) {
        Objects.requireNonNull(e);

        enqueueImpl(e);
    }

    protected abstract void enqueueImpl(Object e);

    /*
        Pred: AbstractQueue != null && n > 0
        Post: R == a[n] && Immutable
    */
    @Override
    public Object element() {
        assert size() > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    /*
        Pred: AbstractQueue != null && n > 0
        Post: R == a'[1] && n = n' - 1 && forall i = 1..n : a[i] = a'[i + 1]
    */
    @Override
    public Object dequeue() {
        assert size() > 0;

        return dequeueImpl();
    }

    protected abstract Object dequeueImpl();

    /*
        Pred: AbstractQueue != null
        Post: R == n && Immutable
     */
    @Override
    public abstract int size();

    /*
        Pred: AbstractQueue != null
        Post: n == 0
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    /*
        Pred: AbstractQueue != null
        Post: R == (n == 0) && Immutable
    */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /*
        Pred: AbstractQueue != null
        Post: R == getNth(n) && dropNth(n) on AbstractQueue
    */
    @Override
    public AbstractQueue removeNth(int n) {
        AbstractQueue queue = getNth(n);
        dropNth(n);
        return queue;
    }

    /*
        Pred: AbstractQueue != null && n > 0
        Post: Immutable && R == queue : type(R) == type(AbstractQueue) && forall i = 1..length : a[i] = a'[i * n]
    */
    @Override
    public AbstractQueue getNth(int n) {
        AbstractQueue queue = create();
        updateQueue(queue, n);
        return queue;
    }

    /*
        Pred: AbstractQueue != null && n > 0
        Post: forall i = 1..n && i % n == 0 a[i] is deleted
    */
    @Override
    public void dropNth(int n) {
        updateQueue(null, n);
    }

    private void updateQueue(AbstractQueue queue, int n) {
        int k = 1, size = size();
        for (int i = 0; i < size; i++) {
            Object e = dequeue();
            if (queue == null) {
                if (k++ % n != 0) {
                    enqueue(e);
                }
            } else {
                enqueue(e);
                if (k++ % n == 0) {
                    queue.enqueue(e);
                }
            }
        }
    }

    protected abstract AbstractQueue create();
}
