package queue;

/*
    Model:
        [a1, a2, ... an]
        n -- size of queue

    Inv:
        n >= 0
        forall i = 1..n: a[i] != null

    Let's define Immutable: n == n' && forall i = 1..n : a[i] = a'[i]
 */

public interface Queue {

    /*
        Pred: Queue != null && e != null
        Post: n = n' + 1 && a[n] = e && forall i = 1..n' : a[i] == a'[i]
     */
    void enqueue(Object e);

    /*
        Pred: Queue != null && n > 0
        Post: R == a[n] && Immutable
    */
    Object element();

    /*
        Pred: Queue != null && n > 0
        Post: R == a'[1] && n = n' - 1 && forall i = 1..n : a[i] = a'[i + 1]
    */
    Object dequeue();

    /*
        Pred: Queue != null
        Post: R == n && Immutable
    */
    int size();

    /*
        Pred: Queue != null
        Post: R == (n == 0) && Immutable
    */
    boolean isEmpty();

    /*
        Pred: Queue != null
        Post: n == 0
    */
    void clear();

    /*
        Pred: Queue != null && n > 0
        Post: Immutable && R == queue : type(R) == type(Queue) && forall i = 1..length : a[i] = a'[i * n]
     */
    Queue getNth(int n);

    /*
        Pred: Queue != null && n > 0
        Post: R == getNth(n) && dropNth(n) on Queue
     */
    Queue removeNth(int n);

    /*
        Pred: Queue != null && n > 0
        Post: forall i = 1..n && i % n == 0 a[i] is deleted
    */
    void dropNth(int n);
}
