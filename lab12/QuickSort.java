import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        Item item;
        int cmp;
        while(!unsorted.isEmpty()){
                item = unsorted.dequeue();
                cmp = item.compareTo(pivot);
               if (cmp < 0){
                   less.enqueue(item);
               }
               else if (cmp == 0){
                   equal.enqueue(item);
               }
               else {
                   greater.enqueue(item);
               }
            }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        if (items.size() < 2) return items;
        Item pivot = getRandomItem(items);
        Queue<Item>less = new Queue<>();
        Queue<Item>equal = new Queue<>();
        Queue<Item>greater = new Queue<>();
        partition(items,pivot,less,equal,greater);
        return catenate(catenate(quickSort(less),quickSort(equal)),quickSort(greater));

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
        Queue<String> queue1 = QuickSort.quickSort(queue0);
        int length = answer.size();
        for (int i= 0; i < length; i++){
            System.out.print(i+"th should be ");
            System.out.print(answer.dequeue());
            System.out.print(" and the sorted is " + queue1.dequeue());
            System.out.println("");
        }





    }
}
