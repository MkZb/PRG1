package Sources;

public class Mon5 {
    private int Flag = 0;

    public synchronized void waitCalc_a() {
        while (Flag < Data.P) {
            try {
                wait();
            } catch (Exception ignored) {}
        }
    }

    public synchronized void signalCalc_a() {
        Flag += 1;
        notifyAll();
    }
}
