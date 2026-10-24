package Q5;
public class ParallelMinSearch extends SensorSearchEngine {
    public ParallelMinSearch(double[] data, int start, int end) {
        super(data, start, end);
    }
    @Override
    public double processGrid() {
        if (endIdx - startIdx <= THRESHOLD) {
            double min = Double.MAX_VALUE;
            for (int i = startIdx; i < endIdx; i++) {
                min = Math.min(min, sensorData[i]);
            }
            return min;
        }
        int mid = startIdx + (endIdx - startIdx) / 2;
        ParallelMinSearch left =
            new ParallelMinSearch(sensorData, startIdx, mid);
        ParallelMinSearch right =
            new ParallelMinSearch(sensorData, mid, endIdx);
        left.fork();
        double rightMin = right.compute();
        double leftMin = left.join();
        return Math.min(leftMin, rightMin);
    }
}
