import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s1 = "abccba";
        assertTrue("s1 is palindrome correct", palindrome.isPalindrome(s1));

        String s2 = "abcabc";
        assertFalse("is2 is not palindrome ,correct", palindrome.isPalindrome(s2));
    }

    @Test
    public void testOffByOneEqual(){
        CharacterComparator cc = new OffByOne();
        char s1,s2,s3,s4;
        s1 = 'a';
        s2 = 'b';
        s3 = 'c';
        s4 = 'd';
        assertTrue(cc.equalChars(s1, s2));
        assertTrue(cc.equalChars(s2,s3));
        assertFalse(cc.equalChars(s1,s1));
        assertFalse(cc.equalChars(s1,s3));

    }

    @Test
    public void testOffByNEqual(){
        CharacterComparator cc = new OffByN(3);
        char s1,s2,s3,s4;
        s1 = 'a';
        s2 = 'b';
        s3 = 'd';
        s4 = 'e';
        assertTrue(cc.equalChars(s1,s3));
        assertTrue(cc.equalChars(s2,s4));
        assertFalse(cc.equalChars(s3,s1));
        assertFalse(cc.equalChars(s1,s1));
        assertFalse(cc.equalChars(s1,s4));
    }


}
