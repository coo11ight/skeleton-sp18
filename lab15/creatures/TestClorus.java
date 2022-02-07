package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {


        /* Replace with the magic word given in lab.
         * If you are submitting early, just put in "early" */
        public static final String MAGIC_WORD = "";

        @Test
        public void testBasics() {
            Clorus c = new Clorus(0.7);
            assertEquals(0.7, c.energy(), 0.01);
            assertEquals(new Color(34, 0, 231), c.color());
            c.move();
            assertEquals(0.67, c.energy(), 0.01);
            c.move();
            assertEquals(0.64, c.energy(), 0.01);
            c.stay();
            assertEquals(0.63, c.energy(), 0.01);
            c.stay();
            assertEquals(0.62, c.energy(), 0.01);
        }

        @Test
        public void testReplicate() {
            Clorus c = new Clorus(2);
            Clorus babyC = c.replicate();
            assertNotSame(c,babyC);
            assertEquals(2.0, (c.energy() + babyC.energy()),0.01);
            assertEquals(c.energy(), (babyC.energy()),0.01);
        }

        @Test
        public void testChoose() {
            Clorus c = new Clorus(1.2);
            HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
            surrounded.put(Direction.TOP, new Impassible());
            surrounded.put(Direction.BOTTOM, new Impassible());
            surrounded.put(Direction.LEFT, new Impassible());
            surrounded.put(Direction.RIGHT, new Impassible());

            //You can create new empties with new Empty();
            //Despite what the spec says, you cannot test for Cloruses nearby yet.
            //Sorry!

            Action actual = c.chooseAction(surrounded);
            Action expected = new Action(Action.ActionType.STAY);

            assertEquals(expected, actual);


            HashMap<Direction, Occupant> surroundedEmpty = new HashMap<Direction, Occupant>();
            surroundedEmpty.put(Direction.TOP, new Empty());
            surroundedEmpty.put(Direction.BOTTOM, new Impassible());
            surroundedEmpty.put(Direction.LEFT, new Impassible());
            surroundedEmpty.put(Direction.RIGHT, new Impassible());

            Action actual1 = c.chooseAction(surroundedEmpty);
            Action expected1 = new Action(Action.ActionType.REPLICATE,Direction.TOP);

            assertEquals(expected1, actual1);

            HashMap<Direction, Occupant> surroundedPlip = new HashMap<Direction, Occupant>();
            surroundedEmpty.put(Direction.TOP, new Plip(1));
            surroundedEmpty.put(Direction.BOTTOM, new Impassible());
            surroundedEmpty.put(Direction.LEFT, new Impassible());
            surroundedEmpty.put(Direction.RIGHT, new Impassible());

            Action actual2 = c.chooseAction(surroundedPlip);
            Action expected2 = new Action(Action.ActionType.ATTACK,Direction.TOP);

            assertEquals(expected1, actual1);
            c.attack(new Plip(1));
            assertEquals(2.2, c.energy(),0.001);



        }

        public static void main(String[] args) {
            System.exit(jh61b.junit.textui.runClasses(creatures.TestClorus.class));
        }


}
