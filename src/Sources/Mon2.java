package Sources;

public class Mon2 {
    private int d;

    public synchronized void put_d(int d_value){
        d = d_value;
    }

    public synchronized int copy_d() {
        return d;
    }
}
