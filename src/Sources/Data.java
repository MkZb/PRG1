package Sources;

import java.util.Arrays;

public class Data {
    public static int P = 4;
    public static int N = 2400;
    public static int H = N / P;

    public static void fillMatrix(int[][] MX) {
        for (int[] arr : MX) {
            Arrays.fill(arr, 1);
        }
    }

    public static void fillArray(int[] A) {
        Arrays.fill(A, 1);
    }

    public static int[][] createMatrix() {
        int[][] result = new int[N][N];
        for (int[] arr : result) {
            Arrays.fill(arr, 1);
        }
        return result;
    }

    public static int maxArrayPart(int[] A, int start) {
        int max = 0;
        for (int i = start; i < start + H; i++) {
            if (A[i] > max) max = A[i];
        }
        return max;
    }

    public static void calcMU(int tNum, int[][] MU, int[][] MD, int[][] MC, int[][] MR, int d, int a) {
        int[][] result;
        result = matrixSum(tNum, multMatrixByVal(tNum, d, multMatrixPart(tNum, MD, MC)), multMatrixByVal(tNum, a, MR));
        for (int i = 0; i < N; i++) {
            for (int j = H * (tNum - 1); j < H * tNum; j++) {
                MU[i][j] = result[i][j];
            }
        }
    }

    private static int[][] multMatrixPart(int i, int[][] MA, int[][] MB) {
        int[][] result = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = H * (i - 1); k < H * i; k++) {
                result[j][k] = 0;
                for (int l = 0; l < N ; l++) {
                    result[j][k] += MA[j][l] * MB[l][k];
                }
            }
        }
        return result;
    }

    private static int[][] multMatrixByVal(int i, int a, int[][] MA) {
        int[][] result = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = H * (i - 1); k < H * i; k++) {
                result[j][k] = MA[j][k] * a;
            }
        }
        return result;
    }

    private static int[][] matrixSum(int i, int[][] MA, int[][] MB) {
        int[][] result = new int[N][N];

        for (int j = 0; j < N; j++) {
            for (int k = H * (i - 1); k < H * i; k++) {
                result[j][k] = MA[j][k] + MB[j][k];
            }
        }
        return result;
    }
}
