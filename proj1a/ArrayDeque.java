public class ArrayDeque<T> {
    private int head;  // first element index;
    private int size;  //  how many elments in the array
    private final double minLoadFactor = 0.25; // when maxLoad > 16 ,minimum usage of memory
    private final double shrinkFactor = 0.5 ; // when space usage ratio < minLoadFactor, shrink the array;
    private final int multiplyFactor = 2; // when add and the array is full, the facor to exaggerate
    public int maxLoad; // the max size that array can hold
    public T[] array; // array the class hold



    public ArrayDeque(  ){
        array = (T[]) new Object[16];
        maxLoad = 16;
        size = 0;
        head = 7;
    }

    public ArrayDeque(T a){
        array =(T[]) new Object[16];
        maxLoad = 16;
        size = 1;
        array[8] = a;
        head = 8;
    }



    public void addLast(T item) {
        if (head + size  == maxLoad) {
            maxLoad *= multiplyFactor;
            T[] a = (T[]) new Object[maxLoad];
            System.arraycopy(array, 0, a, 0, size);
            a[size] = item;
            size ++;
            array = a;
        }
        else{
            array[head + size ] = item;
            size ++;
        }
    }

    public void addFirst(T item){
        if (head == 0){
            maxLoad *= multiplyFactor;
            T[] a = (T[]) new Object[maxLoad];
            head = maxLoad / 4;
            System.arraycopy(array, 0, a, head, size);
            head --;
            a[head] = item;
            size ++;
            this.array = a ;
        }
        else {
            head --;
            array[head] = item;
            size ++;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public  int size(){
        return  size;
    }

    public void printDeque(){
        if (isEmpty()){
            return;
        }
        else{
            for (int i = 0; i < size - 1; i++){
                System.out.print(array[head + i]);
                System.out.print(" ");
            }
            System.out.print(array[head + size - 1]);
        }
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T item = array[head];
        if (((size - 1) / (double) maxLoad ) < minLoadFactor &&  maxLoad > 16){
            maxLoad *= shrinkFactor;
            int last_head = head;
            head = maxLoad / 4;
            T[] a = (T[]) new Object[maxLoad];
            System.arraycopy(array, last_head + 1, a,  head, size - 1);
            size -= 1;
            array = a;
        }
        else{
            head++;
            size--;
        }
        return item;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T item = array[head + size - 1];
        if (((size - 1) / (double) maxLoad ) < minLoadFactor && maxLoad > 16 ){
            maxLoad *= shrinkFactor;
            int last_head = head;
            head = maxLoad / 4;
            T[] a = (T[]) new Object[maxLoad];
            System.arraycopy(array, last_head, a, head, size - 1);
            size -= 1;
            array = a;
        }
        else{
            size--;
        }
        return item;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        else{
            return array[head + index];
        }
    }

}