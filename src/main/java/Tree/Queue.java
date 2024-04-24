package Tree;

public class Queue {

    private Element array[];

    private int first;

    private int last;

    private int N;

    public Queue(int N){
        this.N = N;
        array = new Element[N];
        first = 0;
        last = 0;
    }

    boolean isFull(){
        return (last + 1) % N == first;
    }

    boolean isEmpty(){
        return first == last;
    }

    void enqueue(Element element){
        if (!isFull()){
            array[last] = element;
            last = (last + 1) % N;
        }
    }

    Element dequeue(){
        if (!isEmpty()){
            Element tmp = array[first];
            first = (first + 1) % N;
            return tmp;
        }
        return null;
    }
    /*
     Write the methods
     Element dequeue(int k), Node dequeue(int k)
     which dequeues data as the k’th element from the first. dequeue(1) is
     equal to the original dequeue, that is, the first element has index 1.
     You are not allowed to use any queue methods and any external struc
    tures (arrays, queues, trees, etc). You are allowed to use attributes,
     constructors, getters and setters. Write the method for both array and
     linked list implementations.
     */
    public Element dequeue(int k) {
        Element result = array[first];
        if (k == 1) {
            if (!isEmpty()) {
                first = (first + 1) % N;
                return result;
            }
            return null;
        }
        int index = 2;
        int current = (first + 1) % N;
        while (current != last) {
            if (index == k) {
                Element tmp = array[current];
                for (int i = current; i != last; i = (i + 1) % N) {
                    array[i] = array[(i + 1) % N];
                }
                last = (last - 1 + N) % N;
                return tmp;
            }
            current = (current + 1) % N;
            index++;
        }
        return null;
    }
    /*
    Write a function that creates and returns a new queue by removing
     even indexed elements from the original queue and inserting into the
     newly created queue. Write the function for both array and linked list
     implementations. The first node has index 1. You are not allowed
     to use any queue or linked list methods, just attributes, constructors,
     getters and setters.
     Queue divideQueue()
     */
    public Queue divideQueue() {
        Queue newQueue = new Queue(N);
        int index = 1;
        while (!isEmpty()) {
            if (index % 2 == 0) {
                newQueue.enqueue(dequeue());
            } else {
                dequeue();
            }
            index++;
        }
        return newQueue;
    }

}
