package Sources;

public class Mon3 {
    private int[][] MD = new int[Data.N][Data.N];

    public synchronized void putMD(int[][] src) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, MD[i], 0, src[i].length);
        }
    }

    public synchronized void copyMD(int[][] dest) {
        for (int i = 0; i < MD.length; i++) {
            System.arraycopy(MD[i], 0, dest[i], 0, MD[i].length);
        }
    }
}
