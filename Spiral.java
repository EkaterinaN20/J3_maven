import java.util.Scanner;

public class Spiral {
        public static void main(String[] args) {
            int n = 5;
            int[][] arr = new int[n][n];
            int row = 0;
            int col = 0;
            int x = 1;
            int y = 0;
            int z = 0;
            int w = n;
            int swap;
            for (int i = 0; i < n * n; i++) {
                arr[row][col] = i + 1;
                if (--w == 0) {
                    w = n * (z % 2) + n * ((z + 1) % 2) - (z / 2 - 1) - 2;
                    swap = x;
                    x = -y;
                    y = swap;
                    z++;
                }
                col += x;
                row += y;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    System.out.printf("%3d", arr[i][j]);
                System.out.println();
            }
        }
    }
