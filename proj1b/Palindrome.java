public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (char w : word.toCharArray()){
            wordDeque.addLast(w);
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d1 = wordToDeque(word);
        while (d1.size() > 1){
            char first = d1.removeFirst();
            char last  = d1.removeLast();
            if (first == last){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public  boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d1 = wordToDeque(word);
        while (d1.size() > 1){
            char first = d1.removeFirst();
            char last  = d1.removeLast();
            if (cc.equalChars(first, last)){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }


}
