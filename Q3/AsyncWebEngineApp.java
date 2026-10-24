package Q3;
public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName() );
        TelemetryThread telemetry =
            new TelemetryThread("Telemetry-Worker");
        Thread logWorker = new Thread( new LogProcessorTask(),"Log-Worker" );
        System.out.println("Starting worker threads...");
        telemetry.start();
        logWorker.start();
        System.out.println("Main thread continues working.");
        try {
            telemetry.join();
            logWorker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted.");
        }
        System.out.println("All worker threads completed.");
    }
}
