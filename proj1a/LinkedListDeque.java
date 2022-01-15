public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size;

    public class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(T x,IntNode ppre, IntNode nnex){
            item = x;
            prev = ppre;
            next = nnex;
        }

        /** used to construct sentinel */
        public  IntNode(IntNode ppre,IntNode nnex){
            prev = ppre;
            next = nnex;
        }

        public IntNode(T x){
            item = x;
            prev = null;
            next = null;

        }
        public IntNode(IntNode x){
            item = x.item;
            prev = x.prev;
            next = x.next;
        }

        /** helper function for getRcursive() in Deque */
       public T getIthNext(int index) {


           if (this.next == sentinel) {
               return null;

           }
           else {
               if (index == 0){
                   return this.next.item;
               }
               return this.next.getIthNext(index - 1);
           }
           }
       }


    public LinkedListDeque(){
        sentinel = new IntNode(null, null );  //need instantialize sentinel
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T x){
        sentinel = new IntNode(null, null);   //need instantialize sentinel
        IntNode node = new IntNode(x);
        sentinel.next = node;
        sentinel.prev = node;
        node.next = sentinel;
        node.prev = sentinel;
        size = 1;
    }

    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }



    public void addFirst(T x){
        IntNode node = new IntNode(x);
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        node.prev = sentinel;
        size += 1;
    }

    public void addLast(T item){
        IntNode node = new IntNode(item,sentinel.prev,sentinel);
        sentinel.prev = node;
        sentinel.prev.prev.next = node;
        size += 1;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode tempNode = new IntNode(sentinel);
        while (tempNode.next != sentinel){
            tempNode = tempNode.next;
            System.out.print(tempNode.item);
        }
    }

    public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;

    }

    public T removeLast(){
        if (sentinel.prev == sentinel){
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return item;

    }

    public T get(int index){

        IntNode tempNode = new IntNode(sentinel);
        while (index > 0 && tempNode.next != sentinel){
            tempNode = tempNode.next;
            index--;
        }

        if (tempNode.next == sentinel){
            return null;
        }
        return tempNode.next.item;

    }

    public T getRecursive(int index){
        return sentinel.getIthNext(index);
    }

}

