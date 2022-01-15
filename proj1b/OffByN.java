public class OffByN implements CharacterComparator {
   private int gap;
    public OffByN(int n){
        super();
        gap = n;
    }

    @Override
    public boolean equalChars(char x,char y){
        int a;
        a = y - x;
        if ( a == gap || a == gap * (-1)){
            return true;
        }
        return false;
    }
}
