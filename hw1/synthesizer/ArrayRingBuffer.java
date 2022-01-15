package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class RingBufferIterator implements Iterator<T>{
        private int pos;
        private int curNum;

        private RingBufferIterator(){
            pos = first;
            curNum = 0;
        }

        @Override
        public boolean hasNext() {
            return curNum < fillCount();
        }

        @Override
        public T next(){
            T item = rb[first];
            pos = (pos + 1) % capacity;
            curNum++;
            return item;
        }


    }

    @Override
    public Iterator<T> iterator(){
        return new RingBufferIterator();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        this.capacity = capacity;
        first = 0;
        last = 0;
        this.fillCount = 0;
        rb =(T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last++;
        }
        fillCount++;
    }
    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */

    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        if (first == capacity - 1){
            first = 0;
        }
        else {
            first++;
        }
        fillCount--;
        return item;

    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];

    }



    // TODO: When you get to part 5, implement the needed code to support iteration.
}
