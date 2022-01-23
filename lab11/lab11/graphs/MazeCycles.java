package lab11.graphs;
import edu.princeton.cs.algs4.Stack;
import java.util.LinkedList;
/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public int[] parent;
    public int s;
    public boolean noCycle = true;

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            parent[i] = Integer.MAX_VALUE;
        }
        s = maze.xyTo1D(1,1);
        distTo[s] = 0;
        marked[s] = true;
    }

    @Override
    public void solve() {
        Stack<Integer> stack = new Stack<>();
        int currentPosition = s;
        int start_cycle = 0;
        for (int n : maze.adj(s)){
            parent[n] = s;
            distTo[n] = distTo[s] + 1;
            stack.push(n);
        }
        while (!stack.isEmpty() && noCycle){
            currentPosition = stack.pop();
            marked[currentPosition] = true;
            announce();
            for (int n: maze.adj(currentPosition)){
                if (parent[n] == Integer.MAX_VALUE){
                    parent[n] = currentPosition;
                    distTo[n] = distTo[currentPosition] + 1;
                    stack.push(n);
                }
                else {
                    if (marked[n] == true){
                        if (parent[currentPosition] != n){
                            noCycle = false;
                            start_cycle = n;
                            break;
                        }
                    }
                }
            }
        }
        int current = currentPosition ;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(current);
        while (current != s){
            list.add(parent[current]);
            current = parent[current];
        }
        edgeTo[start_cycle] = currentPosition;
        announce();
        while( !list.contains(start_cycle)){
            edgeTo[parent[start_cycle]] = start_cycle;
            announce();
            start_cycle = parent[start_cycle];
            System.out.println(start_cycle);
        }
        System.out.println("over"+start_cycle);
        while(currentPosition != start_cycle){
            edgeTo[currentPosition] = parent[currentPosition];
            announce();
            currentPosition = parent[currentPosition];
            System.out.println(currentPosition);
        }






    }

    // Helper methods go here
}

