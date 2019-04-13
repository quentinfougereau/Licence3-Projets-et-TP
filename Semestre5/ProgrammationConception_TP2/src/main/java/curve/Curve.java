package curve;

import java.io.IOException;
import java.io.Writer;

public class Curve {

    private Function function;
    private double startValue;
    private double endValue;
    private double step;

    public Curve(Function function, double i, double i1, double v) {
        this.function = function;
        this.startValue = i;
        this.endValue = i1;
        this.step = v;
    }

    public void writePoints(Writer writer) throws IOException {
        for (double value = this.startValue; value <= this.endValue; value += this.step) {
            writer.write(value+" "+2*value+"\n");
        }
    }
}
