package Q5;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
public class SensorGridMonitorApp {
    public static void main(String[] args) throws Exception {
        int totalReadings = 50000;
        double[] sensorData = new double[totalReadings];
        for (int i = 0; i < totalReadings; i++) {
            sensorData[i] = 10 + Math.random() * 90;
        }
        ForkJoinPool pool = new ForkJoinPool(4);
        long startTime = System.nanoTime();
        Future<Double> minFuture = pool.submit(
             new ParallelMinSearch(sensorData, 0, sensorData.length));
        Future<Double> maxFuture = pool.submit(
            new ParallelMaxSearch(sensorData, 0, sensorData.length));
        double minimum = minFuture.get();
        double maximum = maxFuture.get();
        long endTime = System.nanoTime();
        System.out.println("Total sensor readings: " + totalReadings);
        System.out.printf("Minimum reading: %.2f%n", minimum);
        System.out.printf("Maximum reading: %.2f%n", maximum);
        if (minimum < 20) {
            System.out.println("Alert: Sensor reading is below 20."); }
        if (maximum > 90) {
            System.out.println("Alert: Sensor reading is above 90.");}
        System.out.printf("Parallel search time: %.3f ms%n",(endTime - startTime) / 1_000_000.0);
        pool.shutdown();
    }
}
