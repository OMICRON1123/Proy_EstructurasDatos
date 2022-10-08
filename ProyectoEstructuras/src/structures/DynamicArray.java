package structures;

public class DynamicArray<T extends Comparable> {

    private T[] array = (T[])(new Object[2]);
    private int capacity = 2;
    private int size = 0;

    public DynamicArray() {

    }

    // este método mete en el array el valor que se le pase de manera que los valores quedan organizados de menor a mayor
    public void PushBack(T val) {
        if (size < capacity) {
            array[size] = val;            
        } else {
            T[] newArray = (T[])(new Object[2 * capacity]);
            System.arraycopy(array,0,newArray,0,capacity);
            capacity *= 2;
            array = newArray;
            array[size] = val;
        }
        size++;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
            //System.out.println("ERROR: index out of range");
        } 
        return array[pos];
    }

    public void set(int pos, T value) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[pos] = value;
    }

    public void Remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        } 
        array[pos] = array[size--];       
    }

    public int Size() {
        return size;
    }

    public void showArray() {
        for (T x : array) {
            System.out.print(x+" ");
        }
        System.out.println();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T x : array) {
            sb.append(x.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public void sort() {
        sort(0,size);
    }
    
    private void sort(int beg, int las){ 
        if (beg != las) {
            sort(beg,((beg+las)>>1));
            sort(((beg+las)>>1)+1,las);
            int mid = (beg+las)>>1;
            merge(beg,mid,las);
        }
    }
    
    private void merge(int beg,int mid,int las) {
        T[] auxArray = (T[])(new Object[array.length]);
        System.arraycopy(array,0,auxArray,0,array.length);
        int idx = beg;
        int idx1 = beg;
        int idx2 = mid;
        while (idx < las) {
            if (idx1 >= mid) {
                array[idx++] = auxArray[idx2];
            }
            else if (idx2 >= las) {
                array[idx++] = auxArray[idx1];
            }
            else {
                array[idx++] = auxArray[idx1].compareTo(auxArray[idx2]) <= 0 ? auxArray[idx1++] : auxArray[idx2++]; 
            }
        }
    }
}
