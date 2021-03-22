package Sources;

public class Mon4 {
    private int Flag = 0;

    public synchronized void waitIn() {
        while (Flag < 2) {
            try {
                wait();
            } catch (Exception ignored) {}
        }
    }

    public synchronized void signalIn() {
        Flag += 1;
        notifyAll();
    }
}
