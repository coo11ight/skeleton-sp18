package lab14;
import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;


public class StrangeBitwiseGenerator implements Generator{
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int p){
        period = p;
        state = 0;
    }

    @Override
    public double next(){
        state += 1;
        int weirdState = state & (state >> 3) & (state >> 8) % period;;
        return (weirdState % period) / (double) period * 2 - 1;
    }


    public static void main(String[] args){
    Generator generator = new StrangeBitwiseGenerator(512);
    GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
    gav.drawAndPlay(4096, 1000000);

    }
}
