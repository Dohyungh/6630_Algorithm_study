package BOJ._2580_스도쿠;

import java.util.Scanner;

public class Main {
    static boolean answered = false;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solve(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }


    }
    static void solve(int idx) {
        if (idx == 81) {
            answered = true;
            return;
        }
        int row = idx / 9;
        int col = idx % 9;
        int boxRow = row /3; // 0, 1, 2
        int boxCol = col /3; // 0, 1, 2

        if (arr[row][col] != 0) {
            solve(idx+1);
            return;
        }

        // 0 이면

        for (int i = 1; i <= 9; i++) {
            if (horCheck(row, i) && verCheck(col, i) && boxCheck(boxRow, boxCol, i)) {
                arr[row][col] = i;
                solve(idx+1);
                if (!answered) arr[row][col] = 0;
            }
        }

    }

    static boolean horCheck(int row, int num) {
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == num) return false;
        }
        return true;
    }

    static boolean verCheck(int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][col] == num) return false;
        }
        return true;
    }

    static boolean boxCheck(int boxRow, int boxCol, int num) {
        int startRow = 3 * boxRow;
        int startCol = 3 * boxCol;

        for (int i = startRow; i<startRow+3; i++) {
            for (int j = startCol; j <startCol +3; j++) {
                if (arr[i][j] == num) return false;
            }
        }
        return true;
    }
}
