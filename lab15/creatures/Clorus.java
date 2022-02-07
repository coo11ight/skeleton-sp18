package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;


public class Clorus extends Creature{

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    private final double moveEnergyLose = 0.03;
    private final double stayEnergyLose = 0.01;

    private final double repEnergyGiven = 0.5;
    private final double repEnergyRetain = 0.5;

    public Clorus(double e){
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus(){
         this(1);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }

    /** attack plip and gain its energy */
    public void attack(Creature c) {
        energy += c.energy();
    }

    public void stay() {
        energy -= stayEnergyLose;
    }

    public void move(){
        energy -= moveEnergyLose;
    }

    public Clorus replicate(){
        double babyEnergy = energy * repEnergyGiven;
        energy *= repEnergyRetain;
        return new Clorus(babyEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (plips.size() > 0){
            return new Action(Action.ActionType.ATTACK,HugLifeUtils.randomEntry(plips));
        }

        if (energy >= 1.0){
            return new Action(Action.ActionType.REPLICATE,HugLifeUtils.randomEntry(empties));
        }

        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(empties));



    }







}
