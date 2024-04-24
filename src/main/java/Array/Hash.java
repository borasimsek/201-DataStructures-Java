package Array;

public class Hash {

    private Element[] table;

    private boolean[] deleted;

    private int N;

    public Hash(int N){
        table = new Element[N];
        deleted = new boolean[N];
        this.N = N;
    }

    private int hashFunction(int value){
        return value % N;
    }

    public Element search(int value){
        int address;
        address = hashFunction(value);
        while (table[address] != null){
            if (!deleted[address] && table[address].getData() == value){
                break;
            }
            address = (address + 1) % N;
        }
        return table[address];
    }

    public void insert(int value){
        int address;
        address = hashFunction(value);
        while (table[address] != null && !deleted[address]){
            address = (address + 1) % N;
        }
        if (table[address] != null){
            deleted[address] = false;
        }
        table[address] = new Element(value);
    }

    public void deleteValue(int value){
        int address;
        address = hashFunction(value);
        while (table[address] != null){
            if (!deleted[address] && table[address].getData() == value){
                break;
            }
            address = (address + 1) % N;
        }
        deleted[address] = true;
    }
    /*
    Write the method
     int numberOfClusters()
     that finds the number of clusters in hash table. A cluster is a contiguous
     group of non-null elements in the array.
     */
    public int numberOfClusters() {
        int clusters = 0;
        boolean inCluster = false;
        for (int i = 0; i < N; i++) {
            if (table[i] != null && !deleted[i]) {
                if (!inCluster) {
                    clusters++;
                    inCluster = true;
                }
            } else {
                inCluster = false;
            }
            if(table[0] != null && !deleted[0] && table[N-1] != null && !deleted[N-1]){
                clusters++;
            }

        }
        return clusters;
    }
}
