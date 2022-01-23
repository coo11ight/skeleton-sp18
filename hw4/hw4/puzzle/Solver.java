package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.HashSet;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    searchNode node;
    HashSet<WorldState> hash;


    public Solver(WorldState initial){
        searchNode tempNode = new searchNode(initial);
        MinPQ<searchNode> pq = new MinPQ<>(tempNode);
        hash = new HashSet<WorldState>();
        while (! tempNode.w1.isGoal()){
            for (WorldState state: tempNode.w1.neighbors()){
                if(! hash.contains(state)){
                    pq.insert(new searchNode(state,tempNode,tempNode.move + 1));
                    hash.add(state);
                }
            }
            tempNode = pq.delMin();
        }
        node = tempNode;
    }

    public int moves(){
        return node.move;
    }

    public Iterable<WorldState> solution(){
        Stack<WorldState> stack = new Stack<>();
        stack.push(node.w1);
        searchNode tempNode = node;
        while (tempNode.preNode != null){
            tempNode = tempNode.preNode;
            stack.push(tempNode.w1);
        }
        return stack;
    }


    private class searchNode implements Comparator<searchNode>{
       public WorldState w1;
       public int move ;
       public searchNode preNode;


       public searchNode(WorldState w, searchNode pre, int m){
           w1 = w;
           preNode = pre;
           move = m;
       }

       /** inital state */
       public searchNode(WorldState w){
           w1 = w;
           preNode = null;
           move = 0;
       }

       public int totalEstimatedMove(){
           return move + w1.estimatedDistanceToGoal();
       }

       @Override
       public int compare(searchNode node1 , searchNode node2){
           if (node1.totalEstimatedMove() > node2.totalEstimatedMove()){
               return 1;
           }
           else if (node1.totalEstimatedMove() == node2.totalEstimatedMove()){
               return 0;
           }
           else {
               return -1;
           }
       }


    }
}
