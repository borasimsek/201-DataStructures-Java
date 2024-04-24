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
    public Node dequeue(int k) {
        Node result = first;
        if (k == 1) {
            if (!isEmpty()) {
                first = first.getNext();
                if (isEmpty()) {
                    last = null;
                }
            }
            return result;
        }
        Node previous = first;
        Node current = first.getNext();
        int index = 2;
        while (current != null) {
            if (index == k) {
                previous.setNext(current.getNext());
                if (current == last) {
                    last = previous;
                }
                return current;
            }
            previous = current;
            current = current.getNext();
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
        Queue newQueue = new Queue();
        Node previous = null;
        Node current = first;
        int index = 1;
        while (current != null) {
            if (index % 2 == 0) {
                if (previous == null) {
                    first = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                if (current == last) {
                    last = previous;
                }
                newQueue.enqueue(current);
            } else {
                previous = current;
            }
            current = current.getNext();
            index++;
        }
        return newQueue;
    }
    /*
    Write a function that inserts a new element after the largest element
     of the queue. Write the function for array implementation. You are
     not allowed to use any queue methods, just attributes, constructors,
     getters and setters.
     void insertAfterLargest (int data)
     */
    public void insertAfterLargest(int data) {
        Node newNode = new Node(data);
        Node previous = null;
        Node current = first;
        Node largest = first;
        while (current != null) {
            if (current.getData() > largest.getData()) {
                largest = current;
            }
            previous = current;
            current = current.getNext();
        }
        if (largest == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(largest.getNext());
            largest.setNext(newNode);
            if (largest == last) {
                last = newNode;
            }
        }
    }
    /*
     Write a method where the method returns the minimum number in a
     queue. Write the function for both array and linked list implementa
    tions. Do not use any class or external methods except isEmpty().
     int minimum()
     */
    public int minimum() {
        if (isEmpty()) {
            // If the queue is empty, return some default value,
            // such as Integer.MAX_VALUE to represent no minimum value found
            return Integer.MAX_VALUE;
        }

        int min = first.getData(); // Initialize min to the data of the first node
        Node current = first.getNext(); // Start from the second node

        // Iterate through the queue to find the minimum value
        while (current != null) {
            if (current.getData() < min) {
                min = current.getData(); // Update min if the current node's data is smaller
            }
            current = current.getNext(); // Move to the next node
        }

        return min;
    }
    /*
    For linked list implementation, write a function that moves the element
     currently at the rear of the queue to the front of the queue.
     void moveToFront()
     */
    public void moveToFront() {
        if (isEmpty() || first == last) {
            return;
        }
        Node previous = null;
        Node current = first;
        while (current != last) {
            previous = current;
            current = current.getNext();
        }
        last.setNext(first);
        first = last;
        last = previous;
        last.setNext(null);
    }
        /*
     Write a function that returns the maximum element in a queue. You are
     only allowed to use enqueue, dequeue, isEmpty functions. The queue
     must contain the same elements in the same order after the execution
     of this function.
     */
    public int maximum() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        Queue external = new Queue();
        int max = Integer.MIN_VALUE;
        while (!isEmpty()) {
            Node node = dequeue();
            external.enqueue(node);
            if (node.getData() > max) {
                max = node.getData();
            }
        }
        while (!external.isEmpty()) {
            Node node = external.dequeue();
            enqueue(node);
        }
        return max;
    }



}
