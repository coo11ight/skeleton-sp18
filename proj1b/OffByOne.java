public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y){
        int a,b;
        a = x;
        b = y;
        if (a == b - 1 || a - 1 == b){
            return true;
        }
        return false;
    }


}
