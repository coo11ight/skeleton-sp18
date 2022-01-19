package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF uf ;
    int openSiteNumber ;
    int[] openArray; // 0 closed 1 open
    int dimension;

    public Percolation(int n){
        if (n <= 0) throw new RuntimeException() ;
        dimension = n;
        uf = new WeightedQuickUnionUF( n * n + 2);
        for (int i = 0; i < n; i ++){
            uf.union(i, n*n );
            uf.union(n*n - 1 - i, n*n + 1);
        }
        openSiteNumber = 0;
        openArray = new int[n*n];
        for (int i = 0; i < n * n ; i++){
            openArray[i] = 0;
        }
    }

    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        validate(row);
        validate(col);
        int index = xyTo1Dim(row, col);
        if (openArray[index] == 0){
            openSiteNumber += 1;
            openArray[index] = 1;
            openUnion(row, col);
        }

    }

    private void openUnion(int row, int col){
        if ( row - 1 > 0 && isOpen(row - 1,col)) uf.union(xyTo1Dim(row, col),xyTo1Dim(row-1,col));
        if ( col > 1 && isOpen(row  ,col - 1)) uf.union(xyTo1Dim(row, col),xyTo1Dim(row ,col - 1));
        if ( col < dimension - 1 && isOpen(row ,col + 1)) uf.union(xyTo1Dim(row, col),xyTo1Dim(row,col + 1));
        if ( row + 1 < dimension && isOpen(row + 1,col)) uf.union(xyTo1Dim(row, col),xyTo1Dim(row+1, col));
    }

    public boolean isOpen(int row, int col){

        return openArray[xyTo1Dim(row, col)] == 1;
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        return uf.connected(xyTo1Dim(row,col), dimension*dimension);
    }  // is the site (row, col) full?

    public int numberOfOpenSites()    {
        return openSiteNumber;
    }       // number of open sites

    public boolean percolates() {
        return uf.connected(dimension*dimension, dimension*dimension +1);
    }             // does the system percolate?

    public void validate(int i){
        if (i < 0 || i + 1 > dimension) throw  new IndexOutOfBoundsException();
    }

    public int xyTo1Dim(int row, int col){
       return  row * dimension + col;
    }

    public static void main(String[] args) {
        Percolation myPercolation = new Percolation(5);
        System.out.println("after construction now number of open " + myPercolation.numberOfOpenSites());
        myPercolation.open(1,1);

        System.out.println(" now number of open " + myPercolation.numberOfOpenSites());
        System.out.println(myPercolation.isOpen(1,1));
        System.out.println(myPercolation.isFull(1,1));

        System.out.println(myPercolation.percolates());

        myPercolation.open(2,2);
        myPercolation.open(3,3);
        myPercolation.open(2,3);
        myPercolation.open(1,2);
        myPercolation.open(0,2);
        System.out.println(myPercolation.isFull(1,1));
        System.out.println(myPercolation.isFull(3,3));
        System.out.println(myPercolation.percolates());
        myPercolation.open(4,4);

        System.out.println(myPercolation.percolates());
        myPercolation.open(3,4);

        System.out.println(myPercolation.percolates());


    }  // use for unit testing (not required)


}
