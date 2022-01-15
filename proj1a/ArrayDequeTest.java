import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void testArrayConstrut(){
        ArrayDeque<Integer> a1 = new ArrayDeque<Integer>(  );
        assertEquals(a1.size(), 0);
        for (int i = 1; i < 14; i++){
            a1.addFirst(i);
        }
        assertEquals(a1.size() , 13);
        System.out.print(a1.maxLoad);

        for (int i = 1; i < 11; i++)
        {
           System.out.println(a1.removeLast());
        }
        a1.printDeque();
        System.out.print(a1.maxLoad);

    }

}

