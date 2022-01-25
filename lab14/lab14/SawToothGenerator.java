package lab14;
import lab14lib.Generator;
import java.util.ArrayList;
import lab14lib.GeneratorAudioVisualizer;
import lab14lib.MultiGenerator;


public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int p){
        period = p;
        state = 0;
    }

    @Override
    public double next(){
        state += 1;
        return (state % period) / (double) period * 2 - 1;
    }


    public static void main(String[] args){
    Generator generator = new SawToothGenerator(512);
    GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
    gav.drawAndPlay(4096, 1000000);

    }
}
