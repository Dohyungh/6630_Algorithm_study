package _1493;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_ref{
    
    static int[][] arr= new int[1001][1001];
     
    static void getNum() {
        int cnt = 1;
        for (int i = 1; cnt <= 100000; i++) {
            for (int j = 1, k = i; j <= i; j++, k--) {
                arr[k][j] = cnt++;
            }
        }
    }
     
    public static void main(String args[]) throws Exception {
//    	System.setIn(new FileInputStream("src/_1493/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        getNum();
 
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p= Integer.parseInt(st.nextToken());
            int q= Integer.parseInt(st.nextToken());
            int r1, r2,c1,c2;
            r1=r2=c1=c2=0;
             
            for (int i = 0; i < 150; i++) {
                for (int j = 0; j < 150; j++) {
                    if (arr[i][j] == p) {
                        r1 = i;
                        c1 = j;
                    }
                    if (arr[i][j] == q) {
                        r2 = i;
                        c2 = j;
                    }
                }
            }
            p=r1+r2;
            q=c1+c2;
     
 
            System.out.println("#" + test_case + " " + arr[p][q]);
 
        }
    }
}