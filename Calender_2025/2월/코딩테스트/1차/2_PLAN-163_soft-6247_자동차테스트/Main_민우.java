package day_05.pro2;

import java.io.*;
import java.util.*;

// 25분 Sol

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());

            if (binarySearch(arr, q) == -1) {
                sb.append(0).append("\n");
                continue;
            }

            int low = binarySearchLowerBound(arr, q) - 1;
            int high = binarySearchUpperBound(arr, q);

            sb.append((low + 1) * (N - high)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) left = mid + 1;
            else if (arr[mid] > target) right = mid - 1;
            else return mid;
        }

        return -1;
    }

    private static int binarySearchLowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < target) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    private static int binarySearchUpperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= target) left = mid + 1;
            else right = mid;
        }

        return right;
    }

}
