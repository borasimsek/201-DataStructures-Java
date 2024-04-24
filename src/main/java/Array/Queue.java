package Array;

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

    public boolean isEmpty(){
        return first == last;
    }

    public void enqueue(Element element){
        if (!isFull()){
            array[last] = element;
            last = (last + 1) % N;
        }
    }

    public Element dequeue(){
        if (!isEmpty()){
            Element tmp = array[first];
            first = (first + 1) % N;
            return tmp;
        }
        return null;
    }
     /*
    Write a function that inserts a new element after the largest element
     of the queue. Write the function for array implementation. You are
     not allowed to use any queue methods, just attributes, constructors,
     getters and setters.
     void insertAfterLargest (int data)
     */
     public void insertAfterLargest(int data) {
         // Find the largest element in the queue
         int largestIndex = 0;
         int largestData = array[0].getData();
         for (int i = 0; i < N; i++) {
             if (array[i] != null && array[i].getData() > largestData) {
                 largestIndex = i;
                 largestData = array[i].getData();
             }
         }
         // Shift elements to the right to make space for the new element
         for (int i = last; i > largestIndex; i = (i - 1 + N) % N) {
             array[i] = array[(i - 1 + N) % N];
         }
         // Insert the new element after the largest element
         array[(largestIndex + 1) % N] = new Element(data);
         // Update the last pointer
         last = (last + 1) % N;
     }
    /*
     Write a function that deletes the element in the K’th (K ≥ 0) position
     of the queue. Write the function for array implementation.
     void deleteKth(int K)
     */
    public void deleteKth(int K) {
        // Shift elements to the left to fill the gap
        for (int i = K; i < last; i = (i + 1) % N) {
            array[i] = array[(i + 1) % N];
        }
        // Update the last pointer
        last = (last - 1 + N) % N;
    }
    /*
     Write a method where the method returns the minimum number in a
     queue. Write the function for both array and linked list implementa
    tions. Do not use any class or external methods except isEmpty().
     int minimum()
     */
    public int minimum() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (array[i] != null && array[i].getData() < min) {
                min = array[i].getData();
            }
        }
        return min;
    }
}
