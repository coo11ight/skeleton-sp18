import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars(){
        char x = 'a';
        char y = 'b';
        assertTrue(offByOne.equalChars(x, x));
        assertFalse(offByOne.equalChars(x, y));
    }

    // Your tests go here.
}
