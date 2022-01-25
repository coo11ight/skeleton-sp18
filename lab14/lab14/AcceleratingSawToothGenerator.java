package lab14;
import lab14lib.Generator;
import java.util.ArrayList;
import lab14lib.GeneratorAudioVisualizer;
import lab14lib.MultiGenerator;

public class AcceleratingSawToothGenerator implements Generator{
    private int period;
    private int state;
    private double aSpeed;

    public AcceleratingSawToothGenerator(int p,double a){
        period = p;
        state = 0;
        aSpeed = a;
    }

    @Override
    public double next(){
        state += 1;
        if (state == period){
            state = 0;
            period = (int) (period * aSpeed);
        }
        return (state) / (double) period * 2 - 1;
    }


    public static void main(String[] args){
        Generator generator = new AcceleratingSawToothGenerator(200, 1.1);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);

    }
}
