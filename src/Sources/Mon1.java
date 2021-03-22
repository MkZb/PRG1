package Sources;

public class Mon1 {
    private int a = 0;

    public synchronized int copy_a(){
        return a;
    }

    public synchronized void max_a(int ai) {
        a = Math.max(a, ai);
    }
}
