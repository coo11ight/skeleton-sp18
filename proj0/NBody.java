import java.io.File;

public class NBody{

    public static double readRadius(String path){
        In inFile = new In(new File(path));
        inFile.readLine();
        double radius = inFile.readDouble();
        inFile.close();
        return radius;

    }

    public static Planet[] readPlanets(String path){
        In inFile = new In(new File(path));
        int no = inFile.readInt();
        Planet[] planetArray = new Planet[no];
        inFile.readLine();
        inFile.readLine();
        for (int i = 0; i < no; i += 1){
            double xP = inFile.readDouble();
            double yP = inFile.readDouble();
            double xV = inFile.readDouble();
            double yV = inFile.readDouble();
            double m = inFile.readDouble();
            String img = inFile.readString();
            inFile.readLine();
            planetArray[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        inFile.close();
        return planetArray;

    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planetArray = readPlanets(filename);
        int len = planetArray.length;
        double[] xForceArray = new double[len];
        double[] yForceArray = new double[len];

        StdDraw.setScale(-200, 200);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        StdAudio.play("audio/2001.mid");

         for (double time = 0.0; time < T; time += dt){

            for (int i = 0; i < len; i += 1) {
                xForceArray[i] = planetArray[i].calcNetForceExertedByX(planetArray);
                yForceArray[i] = planetArray[i].calcNetForceExertedByY(planetArray);
            }

            for (int i = 0; i < len; i += 1) {
                planetArray[i].update(dt, xForceArray[i], yForceArray[i]);
            }

            StdDraw.picture(0.0, 0.0, "images/starfield.jpg", 400, 400);

            for (Planet p : planetArray) {
                p.draw(radius);
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planetArray.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetArray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planetArray[i].xxPos, planetArray[i].yyPos, planetArray[i].xxVel,
                    planetArray[i].yyVel, planetArray[i].mass, planetArray[i].imgFileName);
        }

    }





}