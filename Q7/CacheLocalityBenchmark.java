package Q7;
public class CacheLocalityBenchmark {
    private static final int SIZE = 2000;
    private static final int REPETITIONS = 10;
    public static void main(String[] args) {
        double[][] matrix = new double[SIZE][SIZE];
        initializeMatrix(matrix);
        rowMajorSum(matrix);
        columnMajorSum(matrix);
        long rowMajorTime = benchmarkRowMajor(matrix);
        long columnMajorTime = benchmarkColumnMajor(matrix);
        System.out.println("Cache Locality Benchmark");
        System.out.println("------------------------");
        System.out.println("Matrix size: " + SIZE + " x " + SIZE);
        System.out.println("Repetitions: " + REPETITIONS);
        System.out.printf("Average row-major time: %.3f ms%n",rowMajorTime / 1_000_000.0);
        System.out.printf("Average column-major time: %.3f ms%n",columnMajorTime / 1_000_000.0);
        if (rowMajorTime > 0 && columnMajorTime > 0){
            double speedup =
                    (double) columnMajorTime / rowMajorTime;
            System.out.printf(
                    "Row-major speedup: %.2fx%n", speedup
            );
        }
        System.out.println("Benchmark completed.");
    }
    private static void initializeMatrix(double[][] matrix) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                matrix[row][col] = row + col * 0.5;
            }
        }
    }
    private static long benchmarkRowMajor(double[][] matrix) {
        long start = System.nanoTime();
        for (int i = 0; i < REPETITIONS; i++) {
            rowMajorSum(matrix);
        }
        return (System.nanoTime() - start) / REPETITIONS;
    }
    private static long benchmarkColumnMajor(double[][] matrix) {
        long start = System.nanoTime();
        for (int i = 0; i < REPETITIONS; i++) {
            columnMajorSum(matrix);
        }
        return (System.nanoTime() - start) / REPETITIONS;
    }
    private static double rowMajorSum(double[][] matrix) {
        double sum = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                sum += matrix[row][col];
            }
        }
        return sum;
    }
    private static double columnMajorSum(double[][] matrix) {
        double sum = 0;
        for (int col = 0; col < SIZE; col++) {
            for (int row = 0; row < SIZE; row++) {
                sum += matrix[row][col];
            }
        }
        return sum;
    }
}
