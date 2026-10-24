
package Q6;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryCatalogManager {

    private static final int TOTAL_ITEMS = 1000;
    private static final int TOTAL_WORKERS = 10;
    private static final int OPERATIONS_PER_WORKER = 5000;

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> synchronizedInventory =
                Collections.synchronizedMap(new HashMap<>());

        Map<Integer, Integer> concurrentInventory =
                new ConcurrentHashMap<>();

        initializeInventory(synchronizedInventory);
        initializeInventory(concurrentInventory);

        System.out.println("Inventory Catalog Benchmark");
        System.out.println("----------------------------");

        long synchronizedTime = runTest(synchronizedInventory);
        long concurrentTime = runTest(concurrentInventory);

        System.out.println("\nResults:");
        System.out.println("synchronizedMap time: " + synchronizedTime + " ms");
        System.out.println("ConcurrentHashMap time: " + concurrentTime + " ms");

        System.out.println("\nFinal inventory sizes:");
        System.out.println("synchronizedMap: " + synchronizedInventory.size());
        System.out.println("ConcurrentHashMap: " + concurrentInventory.size());

        System.out.println("\nBenchmark completed.");
    }

    private static void initializeInventory(Map<Integer, Integer> inventory) {
        for (int i = 1; i <= TOTAL_ITEMS; i++) {
            inventory.put(i, 100);
        }
    }

    private static long runTest(Map<Integer, Integer> inventory)
            throws InterruptedException {

        ExecutorService executor =
                Executors.newFixedThreadPool(TOTAL_WORKERS);

        long startTime = System.nanoTime();

        for (int worker = 0; worker < TOTAL_WORKERS; worker++) {
            executor.submit(() -> {
                Random random = new Random();

                for (int i = 0; i < OPERATIONS_PER_WORKER; i++) {
                    int itemId = random.nextInt(TOTAL_ITEMS) + 1;

                    inventory.get(itemId);

                    inventory.computeIfPresent(
                            itemId, (key, quantity) -> quantity + 1
                    );
                }
            });
        }

        executor.shutdown();

        if (!executor.awaitTermination(2, TimeUnit.MINUTES)) {
            executor.shutdownNow();
            System.out.println("Some workers did not finish in time.");
        }

        return TimeUnit.NANOSECONDS.toMillis(
                System.nanoTime() - startTime
        );
    }
}
