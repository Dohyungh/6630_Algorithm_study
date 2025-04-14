package SWEA._1240_단순2진암호코드;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map.put(13, 0);
		map.put(25, 1);
		map.put(19, 2);
		map.put(61, 3);
		map.put(35, 4);
		map.put(49, 5);
		map.put(47, 6);
		map.put(59, 7);
		map.put(55, 8);
		map.put(11, 9);

		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			char[][] arr = new char[N][M];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			int R = -1;
			int C = -1;
			
			for (int i = 0; i < N; i++) {
				boolean flag = false;
				R = i;
				C = -1;
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == '1') {
						flag = true;
						C = j;
					}
					
				}
				
				if (flag) break;
			}
			
			C -= 55;
			
			int test = 0;
			int answer = 0;
			boolean flag = false;
			for (int i = 0; i < 8; i++) {
				
				int num = getNum(arr[R], (C + i*7));
				answer += num;
				
				if (!flag) {
					test += num *3;
					
				} else {
					test += num;
				}
				
				flag = !flag;
			}
			answer = test%10 == 0 ? answer : 0;
			
			System.out.printf("#%d %d\n", tc, answer);
			
		}

		br.close();
		
	}
	public static int getNum(char[] row, int start) {
		
		int num = 0;
		
		for (int i = start; i<=start+6; i++) {
			if (row[i] =='1') num++;
			num = num << 1;
		}
		
		return map.get(num >> 1);
	}
	
	

}
