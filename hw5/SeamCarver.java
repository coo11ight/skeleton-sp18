import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.util.List;


public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture){
        this.picture = picture;
    }
    public Picture picture() {
        return this.picture;
    }                      // current picture
    public     int width()  {
        return this.picture.width();
    }                       // width of current picture
    public     int height()     {
        return this.picture.width();
    }                   // height of current picture
    public  double energy(int x, int y)  {
        Color x1,x2;
        Color y1,y2;
        if (x == 0){
            x1 = picture.get(picture.width()-1,y);
            x2 = picture.get(x + 1,y);
        }
        else if (x == picture.width() -1){
            x1 = picture.get(0,y);
            x2 = picture.get(x-1, y );
        }
        else{
            x1 = picture.get(x-1,y);
            x2 = picture.get(x+1,y);
        }

        if (y == 0){
            y1 = picture.get(x,y + 1);
            y2 = picture.get(x ,picture.height() - 1);
        }
        else if (y == picture.height() -1){
            y1 = picture.get(x,0);
            y2 = picture.get(x, y - 1 );
        }
        else{
            y1 = picture.get(x,y -1);
            y2 = picture.get(x,y + 1);
        }
        return twoColorEnergy(x1,x2) + twoColorEnergy(y1,y2);

    }// energy of pixel at column x and row y

    /** return the energy bewteen color a and color b */
    public double twoColorEnergy(Color a,Color b){
        int rA = a.getRed();
        int rB = b.getRed();
        int gA = a.getGreen();
        int gB = b.getGreen();
        int bA = a.getBlue();
        int bB = b.getBlue();
        return Math.pow(rA - rB ,2 ) + Math.pow(Math.abs(gA - gB) ,2 ) +  Math.pow(bA - bB ,2 );
    }


    public int[] findHorizontalSeam() {
        int width = picture.width();
        int height = picture.height();
        double[][] energyArray = new double[width][height ]; // first row and last row are sentinel
        double[][] dpEnergyArray = new double[width][height + 2];
        int[][] dpEnergyDirectionArray =new int[width][height]; // 0 go to top left . 1 go to horizontal left, 2 goto bottom left, 3 means we are at the first col;
        for (int i = 0; i < width ; i ++){

            for(int j = 0; j < height  ; j++){
                energyArray[i][j] = energy(i, j );
            }
        }

        // initialize sentinel
        for (int i =0 ; i < width; i++){
            dpEnergyArray[i][0] = Double.MAX_VALUE;
            dpEnergyArray[i][height + 1] = Double.MAX_VALUE;
        }

        for (int i = 0; i < height ; i++){
            dpEnergyArray[0][i + 1] = energyArray[0][i];
            dpEnergyDirectionArray[0][i] = 3;
        }

        for (int i = 1; i < width; i++)
        {
            for (int j = 1; j < height + 1; j++){
                if (dpEnergyArray[i -1][j - 1] < dpEnergyArray[i -1][j ] && dpEnergyArray[i -1][j - 1] < dpEnergyArray[i -1][j + 1]){
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 0;
                }
                else if(dpEnergyArray[i - 1][ j ] < dpEnergyArray[i - 1][j + 1]){
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 1;
                }
                else{
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j + 1] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 2;
                }
            }
        }

        double maxEnergy =  Double.MAX_VALUE;
        int maxPosition = 0;
        for (int j = 1; j < height + 1; j++){
            if (dpEnergyArray[width - 1][j] < maxEnergy ){
                maxEnergy = dpEnergyArray[width - 1][j];
                maxPosition = j - 1;
            }
        }
        int[] seam = new int[width];
        seam[width - 1] = maxPosition;
        maxPosition += -1 + dpEnergyDirectionArray[width-1][maxPosition];


        for (int i = width - 2;i >= 0; i --){
            seam[i] = maxPosition;
            maxPosition += -1 + dpEnergyDirectionArray[i][maxPosition];
        }
        return seam;
    }           // sequence of indices for horizontal seam



    public   int[] findVerticalSeam()    {
        int width1 = picture.width();
        int height1 = picture.height();
        double[][] energyArray = new double[height1][width1 ]; // first row and last row are sentinel
        double[][] dpEnergyArray = new double[height1][width1 + 2];
        int[][] dpEnergyDirectionArray =new int[height1][width1]; // 0 go to top left . 1 go to horizontal left, 2 goto bottom left, 3 means we are at the first col;
        for (int i = 0; i < height1 ; i ++){

            for(int j = 0; j < width1  ; j++){
                energyArray[i][j] = energy(j, i );
            }
        }

        // initialize sentinel
        for (int i =0 ; i < height1; i++){
            dpEnergyArray[i][0] = Double.MAX_VALUE;
            dpEnergyArray[i][width1 + 1] = Double.MAX_VALUE;
        }

        for (int i = 0; i < width1 ; i++){
            dpEnergyArray[0][i + 1] = energyArray[0][i];
            dpEnergyDirectionArray[0][i] = 3;
        }

        for (int i = 1; i < height1; i++)
        {
            for (int j = 1; j < width1 + 1; j++){
                if (dpEnergyArray[i -1][j - 1] < dpEnergyArray[i -1][j ] && dpEnergyArray[i -1][j - 1] < dpEnergyArray[i -1][j + 1]){
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 0;
                }
                else if(dpEnergyArray[i - 1][ j ] < dpEnergyArray[i - 1][j + 1]){
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 1;
                }
                else{
                    dpEnergyArray[i][j] = dpEnergyArray[i - 1][j + 1] + energyArray[i][j - 1];
                    dpEnergyDirectionArray[i][j - 1] = 2;
                }
            }
        }

        double maxEnergy =  Double.MAX_VALUE;
        int maxPosition = 0;
        for (int j = 1; j < width1 + 1; j++){
            if (dpEnergyArray[height1 - 1][j] < maxEnergy ){
                maxEnergy = dpEnergyArray[height1 - 1][j];
                maxPosition = j - 1;
            }
        }
        int[] seam = new int[height1];
        seam[height1 - 1] = maxPosition;
        maxPosition += -1 + dpEnergyDirectionArray[height1-1][maxPosition];


        for (int i = height1 - 2;i >= 0; i --){
            seam[i] = maxPosition;
            maxPosition += -1 + dpEnergyDirectionArray[i][maxPosition];
        }
        return seam;


    }          // sequence of indices for vertical seam
    public    void removeHorizontalSeam(int[] seam) {
        this.picture = SeamRemover.removeHorizontalSeam(this.picture, seam);


    }  // remove horizontal seam from picture
    public    void removeVerticalSeam(int[] seam)  {
        this.picture = SeamRemover.removeVerticalSeam(this.picture, seam);
    }   // remove vertical seam from picture

}
