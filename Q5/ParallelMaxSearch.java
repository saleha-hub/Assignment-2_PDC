package Q5;
public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[] data, int start, int end) {
        super(data, start, end);
    }
    @Override
    public double processGrid() {
        if (endIdx - startIdx <= THRESHOLD) {
            double max = -Double.MAX_VALUE;
       for (int i = startIdx; i < endIdx; i++) {
                max = Math.max(max, sensorData[i]);
            }
            return max;
        }
        int mid = startIdx + (endIdx - startIdx) / 2;
        ParallelMaxSearch left =
            new ParallelMaxSearch(sensorData, startIdx, mid);
        ParallelMaxSearch right =
            new ParallelMaxSearch(sensorData, mid, endIdx);
        left.fork();
        double rightMax = right.compute();
        double leftMax = left.join();
        return Math.max(leftMax, rightMax);
    }
}
