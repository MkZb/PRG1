package Sources;

public class Mon6 {
    private int Flag = 0;

    public synchronized void waitCalcMU() {
        while (Flag < (Data.P - 1)) {
            try {
                wait();
            } catch (Exception ignored) {}
        }
    }

    public synchronized void signalCalcMU() {
        Flag += 1;
        notifyAll();
    }
}
