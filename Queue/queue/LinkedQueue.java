package queue;

public class LinkedQueue extends AbstractQueue {
    private int size;
    private Node head;
    private Node tail;

    private static class Node {
        private Node next;
        private final Object value;

        public Node(final Object value) {
            next = null;
            this.value = value;
        }
    }

    @Override
    protected void enqueueImpl(Object e) {
        size++;
        if (head == null) {
            head = tail = new Node(e);
        } else {
            this.tail = this.tail.next = new Node(e);
        }
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    protected Object dequeueImpl() {
        size--;
        Object ans = head.value;
        head = head.next;
        return ans;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected LinkedQueue create() {
        return new LinkedQueue();
    }
}
