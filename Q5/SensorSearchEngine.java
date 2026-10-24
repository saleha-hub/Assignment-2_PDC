package Q5;
import java.util.concurrent.RecursiveTask;

public abstract class SensorSearchEngine extends RecursiveTask<Double> {
    protected double[] sensorData;
    protected int startIdx;
    protected int endIdx;

    protected static final int THRESHOLD = 1000;
    public SensorSearchEngine(double[] sensorData, int startIdx, int endIdx) {
        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }
    public abstract double processGrid();
    @Override
    protected Double compute() {
        return processGrid();
    }
}
