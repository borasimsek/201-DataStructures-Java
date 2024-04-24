package List;

public class Hash {

    private LinkedList[] table;

    private int N;

    public Hash(int N){
        table = new LinkedList[N];
        for (int i = 0; i < N; i++){
            table[i] = new LinkedList();
        }
        this.N = N;
    }

    public Node search(int value){
        int address;
        address = hashFunction(value);
        return table[address].search(value);
    }

    public void insert(int value){
        int address;
        address = hashFunction(value);
        table[address].insertLast(new Node(value));
    }

    public void insert(Node node){
        int address;
        address = hashFunction(node.data);
        table[address].insertLast(node);
    }

    public void deleteValue(int value){
        int address;
        if (search(value) != null){
            address = hashFunction(value);
            table[address].deleteValue(value);
        }
    }

    private int hashFunction(int value){
        return value % N;
    }

    /*
         Write a static method in Hash class
     int numberOfExtras(int[] array)
     that takes an array of integers as a parameter and counts the number
     of extra elements in the array. Your method should run in O(N)
     time, where N is the size of the array. Use hashing.
     1 4 2 5 2 4 3 4 →3 extras (two 4, one 2)
     2 1 2 1 2 3 1 2 1 2 →7extras (four 2, three 1)
     1 1 1 1 1 1 →5extras (five 1)
     */
    public static int numberOfExtras(int[] array) {
        Hash hash = new Hash(array.length);
        int extras = 0;
        for (int i = 0; i < array.length; i++) {
            if (hash.search(array[i]) == null) {
                hash.insert(array[i]);
            } else {
                extras++;
            }
        }
        return extras;
    }
    /*
         Write the method
     boolean perfectMap()
     that returns true if the hash table contains one node at maximum per
     linked list in separate chaining, otherwise it returns false
     */
    public boolean perfectMap() {
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null && table[i].getHead().getNext() != null) {
                return false;
            }
        }
        return true;
    }
    /*
    Write the method
     int numberOfClusters()
     that finds the number of clusters in hash table. A cluster is a contiguous
     group of non-null elements in the array
     */
    public int numberOfClusters() {
        int clusters = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null) {
                clusters++;
                Node tmp = table[i].getHead();
                while (tmp.getNext() != null) {
                    tmp = tmp.getNext();
                }
                while (tmp != null && tmp.getNext() == null) {
                    clusters++;
                    tmp = tmp.getNext();
                }
            }
        }
        return clusters;
    }
    /*
    Write a method that simplifies a hash table by creating a new hash
     table containing elements from the original hash table, where
     •
     •
     For single occurrence of a value, copy that value to the new table
     For multiple occurrences of that value, copy that value only once
     to the new table
      Write the function both array and linked list implementations. You are
     allowed to use linked list and hashing methods.
     Hash simplify()
     */
    public Hash simplify() {
        Hash newHash = new Hash(N);
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null) {
                Node tmp = table[i].getHead();
                while (tmp != null) {
                    if (newHash.search(tmp.getData()) == null) {
                        newHash.insert(tmp.getData());
                    }
                    tmp = tmp.getNext();
                }
            }
        }
        return newHash;
    }
    /*
    Write a static method that takes an array of integers as a parameter
     and checks if the array contains any duplicate elements. Your method
     should run in O(N) time, where N is the size of the array. You are
     allowed to use any methods and external data structures we learned in
     the class.
     boolean anyDuplicate(int[] array)
     */
    public static boolean anyDuplicate(int[] array) {
        Hash hash = new Hash(array.length);
        for (int i = 0; i < array.length; i++) {
            if (hash.search(array[i]) == null) {
                hash.insert(array[i]);
            } else {
                return true;
            }
        }
        return false;
    }
    /*
    Write an hash function that maps the key values in an hash table into
     an hash value. Assume that the hash value of an hash table can be
     obtained first by summing up the key values of the elements in the
     hash table and then hashing the sum. Write the function for array im
    plementation. Assume also that linear probing is used as the collision
     strategy. Do not use any class or external methods except hashFunc
    tion.
     int hashFunctionItSelf ()
     */
    public int hashFunctionItSelf() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null) {
                Node tmp = table[i].getHead();
                while (tmp != null) {
                    sum += tmp.getData();
                    tmp = tmp.getNext();
                }
            }
        }
        return sum % N;
    }
    /*
    Write a method that deletes all elements having value X. Assume also
     that X can exist more than once in the hash table. Write the function for
     both array and linked list implementations. For array implementation assume
      that linear probing is used as the collision strategy. Do not use any class
      or external methods except hashFunction.
     void deleteAll (int X)
     */
    //array implementation:
    public void deleteAll(int X) {
        int address = hashFunction(X);
        if (search(X) != null) {
            table[address].deleteValue(X);
        }
    }
    //linked list implementation:
    public void deleteAll_Linked(int X) {
        for (int i = 0; i < N; i++) {
            if (table[i].search(X) != null) {
                table[i].deleteValue(X);
            }
        }
    }
    /*
    Write function that finds the number of empty slots in an hash table
     (For both array and linked list implementations).
     int numberOfEmptySlots()
     */
    //array implementation:
    public int numberOfEmptySlots() {
        int empty = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() == null) {
                empty++;
            }
        }
        return empty;
    }
    //linked list implementation:
    public int numberOfEmptySlots_Linked() {
        int empty = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() == null) {
                empty++;
            }
        }
        return empty;
    }
    /*
    Write a function that finds the minimum element in a hash table with
     linear probing.
     Element<T> minimum()
     */
    public Node minimum() {
        Node min = null;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null) {
                Node tmp = table[i].getHead();
                while (tmp != null) {
                    if (min == null || tmp.getData() < min.getData()) {
                        min = tmp;
                    }
                    tmp = tmp.getNext();
                }
            }
        }
        return min;
    }
    /*
     Write a function that computes the load factor of an hash table im
    plemented with an array of linked lists (Separate chaining). The load
     factor of a hash table is the ratio of the number of elements in the hash
     table to the table size.
     double loadFactor()
     */
    public double loadFactor() {
        int elements = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].getHead() != null) {
                Node tmp = table[i].getHead();
                while (tmp != null) {
                    elements++;
                    tmp = tmp.getNext();
                }
            }
        }
        return (double) elements / N;
    }
    public static void main(String[] args) {
        int[] array = {1, 4, 2, 5, 2, 4, 3, 4};
        System.out.println(numberOfExtras(array));
    }
}
