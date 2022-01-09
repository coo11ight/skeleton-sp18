public class Planet {
    public double xxPos,yyPos;
    public double xxVel,yyVel;
    public double mass;
    public String imgFileName;
    public static double constG = 6.67E-11D;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double xDis, yDis, Dis;
        xDis = this.xxPos - p.xxPos;
        yDis = this.yyPos - p.yyPos;
        Dis  = Math.sqrt(xDis * xDis + yDis * yDis);
        return Dis;
    }

    public double calcForceExertedBy(Planet p){
        double Dis =  this.calcDistance(p);
        double force = constG * this.mass * p.mass / Math.pow(Dis,2);
        return force;

    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double Dis = calcDistance(p);
        double xDis = p.xxPos - this.xxPos;
        return xDis / Dis * force;
    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double Dis = calcDistance(p);
        double yDis = p.yyPos - this.yyPos;
        return yDis / Dis * force;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double xForce = 0.0;
        for (Planet p : allPlanets){
            if (this != p){
                xForce += this.calcForceExertedByX(p);
            }
        }
        return xForce;

    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double yForce = 0.0;
        for (Planet p : allPlanets){
            if (this != p){
                yForce += this.calcForceExertedByY(p);
            }
        }
        return yForce;

    }

    public void update(double dt, double xForce, double yForce){
        double Ax = xForce / mass;
        double Ay = yForce / mass;
        xxVel += dt * Ax;
        yyVel += dt * Ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;

    }

    public void draw(double radius){
        StdDraw.picture(xxPos / radius * 200, yyPos / radius * 200, "images/" + imgFileName, 10, 10 );
    }

}
