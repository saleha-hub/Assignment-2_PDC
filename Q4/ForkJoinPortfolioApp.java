package Q4;
import java.util.concurrent.ForkJoinPool;
public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        int numberOfAssets = 10000;
        double[] assets = new double[numberOfAssets];
        for (int i = 0; i < numberOfAssets; i++) {
            assets[i] = 100 + Math.random() * 900; }
        long serialStart = System.nanoTime();
        double serialSum = 0;
        for (double risk : assets) {
            serialSum += risk; }
        long serialEnd = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool();
        long parallelStart = System.nanoTime();
        double parallelSum = pool.invoke( new ParallelRiskCalculator(assets, 0, assets.length));
        long parallelEnd = System.nanoTime();
        System.out.printf("Serial risk sum: %.2f%n", serialSum);
        System.out.printf("Parallel risk sum: %.2f%n", parallelSum);
        System.out.printf("Serial time: %.3f ms%n",
            (serialEnd - serialStart) / 1_000_000.0);
        System.out.printf("Parallel time: %.3f ms%n",
            (parallelEnd - parallelStart) / 1_000_000.0);
          double riskCap = 750.0;
        pool.invoke(new PortfolioThresholdTask( assets, 0, assets.length, riskCap));
        System.out.println("5% stress penalty applied to assets above " + riskCap);
        pool.shutdown();
    }
}
