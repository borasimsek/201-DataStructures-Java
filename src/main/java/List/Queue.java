package List;

public class Queue {

    protected Node first;
    protected Node last;

    public Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(Node newNode) {
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    public Node dequeue(){
        Node result = first;
        if (!isEmpty()){
            first = first.getNext();
            if (isEmpty()){
                last = null;
            }
        }
        return result;
    }
    /*
    Write the method void removeOddIndexedElements() which removes only the odd indexed (1, 3, ...) elements.
    The first element at the front has index 1. You are not allowed to use any other data structure. Use external queue.
     */
    public void removeOddIndexedElements() {
        Queue external = new Queue();
        Node node = dequeue();
        external.enqueue(node);
        int index = 1;
        while (!isEmpty()) {
            index++;
            if (index % 2 == 0) {
                node = dequeue();
                external.enqueue(node);
            } else {
                dequeue();
            }
        }
        while (!external.isEmpty()) {
            node = external.dequeue();
            enqueue(node);
        }
    }
    /*
    Write the method void removeOddIndexedElements() which removes only the odd indexed (1, 3, ...) elements.
    The first element at the front has index 1. You are not allowed to use any other data structure. Use external queue.
     */
    public void removeOddIndexedElements_Mt_Question() {
        Queue external = new Queue();
        Node node = dequeue();
        external.enqueue(node);
        int index = 1;
        while (!isEmpty()) {
            index++;
            if (index % 2 == 0) {
                node = dequeue();
                external.enqueue(node);
            } else {
                dequeue();
            }
        }
        while (!external.isEmpty()) {
            node = external.dequeue();
            enqueue(node);
        }
    }
}
