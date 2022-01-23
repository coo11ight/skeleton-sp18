package lab11.graphs;
import java.util.LinkedList;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;
    private boolean targetFound = false;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        marked[s] = true;
        announce();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(s);
        int currentPosition ;
        currentPosition = list.remove();
        while (currentPosition != t){
            for (int n : maze.adj(currentPosition)){
                if (!marked[n]){
                    {
                        if (edgeTo[n] == Integer.MAX_VALUE){
                            edgeTo[n] = currentPosition;
                            distTo[n] = distTo[currentPosition] + 1;
                        }
                        list.add(n);
                    }
                }
            }
            currentPosition = list.remove();
            marked[currentPosition] = true;
            announce();
        }
        targetFound = true;

        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
    }


    @Override
    public void solve() {
         bfs();
    }
}

