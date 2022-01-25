import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
       int length = items.size();
       Queue<Queue<Item>> queues = new Queue<>();
       Queue<Item> queue ;
       for (int i = 0; i < length; i++){
           queue = new Queue<>();
           queue.enqueue(items.dequeue());
           queues.enqueue(queue);
       }
       return queues;


    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> sortedQueue = new Queue<>();
        Item item1,item2;
        while (!q1.isEmpty() && !q2.isEmpty()){
            item1 = q1.peek();
            item2 = q2.peek();
            if (item1.compareTo(item2) < 0){
                q1.dequeue();
                sortedQueue.enqueue(item1);
            }
            else{
                q2.dequeue();
                sortedQueue.enqueue(item2);
            }
        }
        int size;
        if (q1.isEmpty()){
            size = q2.size();
            for (int i = 0 ; i < size ; i++){
                sortedQueue.enqueue(q2.dequeue());
            }
        }
        else{
            size = q1.size();
            for (int i = 0 ; i < size ; i++){
                sortedQueue.enqueue(q1.dequeue());
            }
        }
        return sortedQueue;

    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items == null){
            return null;
        }
        if (items.size() == 1){
            return items;
        }
       int size = items.size();
        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();

        for (int i = 0; i < size / 2; i++){
           q1.enqueue(items.dequeue());
        }
        for (int i = size / 2; i <size; i ++){
            q2.enqueue(items.dequeue());
        }
        return mergeSortedQueues(mergeSort(q1),mergeSort(q2));
    }

    public static void main(String[] args){
        Queue<String> queue0 = new Queue<>();
        queue0.enqueue("coollight");
        queue0.enqueue("xiannv");
        queue0.enqueue("zhou");
        queue0.enqueue("leng");
        queue0.enqueue("doge");
        queue0.enqueue("cat");
        queue0.enqueue("tiger");
        queue0.enqueue("pig");

        Queue<String> answer = new Queue<>();
        answer.enqueue("cat");
        answer.enqueue("coollight");
        answer.enqueue("doge");
        answer.enqueue("leng");
        answer.enqueue("pig");
        answer.enqueue("tiger");
        answer.enqueue("xiannv");
        answer.enqueue("zhou");
        Queue<String> queue1 = MergeSort.mergeSort(queue0);
        int length = answer.size();
        for (int i= 0; i < length; i++){
            System.out.print(i+"th should be ");
            System.out.print(answer.dequeue());
            System.out.print(" and the sorted is " + queue1.dequeue());
            System.out.println("");
        }





    }
}
