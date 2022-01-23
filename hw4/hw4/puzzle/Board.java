package hw4.puzzle;

import java.util.Set;
import java.util.HashSet;

public class Board implements WorldState{

    int[][] goal;
    int[][] state;
    int boardSize;
    int[] empty; // row,col for 0;

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public Board(int[][] tiles){
        boardSize = tiles.length;
        goal =new int[boardSize][boardSize];
        for (int i = 0;i< boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                goal[i][j] = i * boardSize + j;
            }
        }
        state = new int[boardSize][boardSize];
        for (int i = 0;i< boardSize; i++){
            System.arraycopy(tiles[i],0,state[i],0,boardSize);
        }

        int temp = 1;
        for (int i = 0;i< boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                temp = state[i][j];
               if (temp == 0){
                   empty = new int[2];
                   empty[0] = i;
                   empty[1] = j;
                   break;
               }
            }
            if (temp == 0){
                break;
            }
        }
    }

    public int tileAt(int i, int j){
        return state[i][j];
    }

    public int size(){
        return boardSize;
    }

    public Iterable<WorldState> neighbors(){
        Set<WorldState> set = new HashSet<>();
        int row = empty[0];
        int col = empty[1];
        if (row > 0){
            set.add(new Board(move(row-1, col, row, col)));
        }
        if (row < boardSize - 1){
            set.add(new Board(move(row + 1, col, row, col)));
        }
        if (col > 0){
            set.add(new Board(move(row, col - 1, row, col)));
        }
        if (col < boardSize - 1){
            set.add(new Board(move(row , col + 1, row, col)));
        }
        return set;
    }

    /** row2,col2 is 0  move (row1,col1) to (row2,col2)*/
    public int[][] move(int row1,int col1,int row2,int col2){
        int[][] destination = new int[boardSize][boardSize];
        for (int i =0; i <boardSize;i++){
            System.arraycopy(state[i],0,destination[i],0,boardSize);
        }
        destination[row2][col2] = destination[row1][col1];
        destination[row1][col1] = 0;
        return destination;
    }

    public int hamming(){
        int ham = 0;
        int right = 1;
        for (int i = 0;i< boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (state[i][j] != right){
                    ham += 1;
                }
                right ++;
            }
        }
        ham --;
        return ham;
    }
    public int manhattan(){
        int man = 0;
        int[] right_xy = new int[2];
        for (int i = 0;i< boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (state[i][j] == 0){
                    continue;
                }
                right_xy = right_XY(state[i][j]);
                man += Math.abs(right_xy[0] - i) + Math.abs(right_xy[1] - j);
            }
        }
        return man;
    }

    public int[] right_XY(int value){
        int[] right= new int[2];
        right[0] = (value - 1 )/boardSize;
        right[1] = (value - 1 )%boardSize;
        return right;
    }


    public int estimatedDistanceToGoal(){
        return  manhattan();
    }

    @Override
    public boolean equals(Object y){
        if (this == y){
            return true;
        }
        if (y == null || y.getClass() != this.getClass()){
            return false;
        }
        Board y1 = (Board) y;

        if (y1.boardSize != this.boardSize) return false;
        for (int i = 0;i< boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.state[i][j] != y1.state[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode(){
        return hamming();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
