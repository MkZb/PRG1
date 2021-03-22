import Sources.*;

public class PRG1 {
    public static void main(String[] args) {
        System.out.println("Main Thread started");
        int[] Z = new int[Data.N];
        int[][] MC = new int[Data.N][Data.N];
        int[][] MR = new int[Data.N][Data.N];
        int[][] MU = new int[Data.N][Data.N];

        Thread[] threads = new Thread[Data.P];

        final long[] startTime = new long[1];
        final long[] endTime = new long[1];
        final long[] execTime = new long[1];

        Mon1 mon1 = new Mon1();
        Mon2 mon2 = new Mon2();
        Mon3 mon3 = new Mon3();
        Mon4 mon4 = new Mon4();
        Mon5 mon5 = new Mon5();
        Mon6 mon6 = new Mon6();

        class T extends Thread {
            int a, d;
            int[][] MD = new int[Data.N][Data.N];

            int tNum;

            public T(int i) {
                tNum = i;
            }

            @Override
            public void run() {
                System.out.println("Thread T" + tNum + " started");
                if (tNum == 1) {
                    mon3.putMD(Data.createMatrix());
                    Data.fillMatrix(MC);
                    //Signal input in T1
                    mon4.signalIn();
                }
                if (tNum == Data.P) {
                    mon2.put_d(1);
                    Data.fillMatrix(MR);
                    Data.fillArray(Z);
                    //Signal input in TP
                    mon4.signalIn();
                }
                //Wait for input
                mon4.waitIn();

                a = Data.maxArrayPart(Z, (tNum - 1) * Data.H);
                //Set max a
                mon1.max_a(a);

                //Signal about finishing a calculation
                mon5.signalCalc_a();

                //Copy MD, d
                mon3.copyMD(MD);
                d = mon2.copy_d();

                //Wait for a calculations
                mon5.waitCalc_a();

                //Copy a
                a = mon1.copy_a();

                Data.calcMU(tNum, MU, MD, MC, MR, d, a);
                if (tNum != Data.P) {
                    //Signal about finishing calculation MU
                    mon6.signalCalcMU();
                } else {
                    //Wait for MU calculations in TP
                    mon6.waitCalcMU();
                    endTime[0] = System.currentTimeMillis();
                    execTime[0] = endTime[0] - startTime[0];
                    System.out.println("Execution time in mills: " + execTime[0]);
                    //Print MU
                    /*for (int i = 0; i < Data.N; i++) {
                        for (int j = 0; j < Data.N; j++) {
                            System.out.print(MU[i][j]);
                            System.out.print(" ");
                        }
                        System.out.println();
                    }*/
                }
                System.out.println("Thread T" + tNum + " finished");
            }
        }

        startTime[0] = System.currentTimeMillis();
        for (int i = 1; i < Data.P + 1; i++) {
            threads[i - 1] = new T(i);
            threads[i - 1].start();
        }

    }
}

