package SWEA._11315_오목판정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			char[] line = new char[N];
			List<List<Integer>> list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				line = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					if(line[j]=='o') {
						list.add(Arrays.asList(i,j));
					}
					
				}
			}
			
			boolean five = false;
			
			// 가로
			Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {
				if (o1.get(0)==o2.get(0)) {
					return o1.get(1)-o2.get(1);
				}
				return o1.get(0) - o2.get(0);
			});
			
			
			Iterator<List<Integer>> iter = list.iterator();
			
			int r = -1;
			int c = -1;
			int count = 1;
			while (iter.hasNext()&&!five) {
				List<Integer> token = iter.next();
				int nr = token.get(0);
				int nc = token.get(1);
				if (r == nr && c+1==nc) {
					count++;
					if (count == 5) {
						five = true;
						break;
					}
					r = nr;
					c = nc;
					
				} else {
					r = nr;
					c = nc;
					count = 1;
				}
			}
			
			// 세로
			Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {
				if (o1.get(1)==o2.get(1)) {
					return o1.get(0)-o2.get(0);
				}
				return o1.get(1) - o2.get(1);
			});
			
			
			iter = list.iterator();

			r = -1;
			c = -1;
			count = 1;
			while (iter.hasNext()&&!five) {
				List<Integer> token = iter.next();
				int nr = token.get(0);
				int nc = token.get(1);
				if (c == nc && r+1==nr) {
					count++;
					if (count == 5) {
						five = true;
						break;
					}
					r = nr;
					c = nc;
					
				} else {
					r = nr;
					c = nc;
					count = 1;
				}
			}
			

			//대각 변환
			
			for (int i = 0; i<list.size(); i++) {
				r = list.get(i).get(0);
				c = list.get(i).get(1);
				list.set(i, Arrays.asList(r+c,-r+c));
				
			}
			
			// 가로 대각
			Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {
				if (o1.get(0)==o2.get(0)) {
					return o1.get(1)-o2.get(1);
				}
				return o1.get(0) - o2.get(0);
			});
			
			
			iter = list.iterator();
			
			r = Integer.MIN_VALUE; // 변환했을 때 어떤 값이 나올지 몰라서. 근데 아마 -200 정도면 넉넉할 듯
			c = Integer.MIN_VALUE;
			count = 1;

			while (iter.hasNext()&&!five) {
				List<Integer> token = iter.next();
				int nr = token.get(0);
				int nc = token.get(1);
				if (r == nr && c+2==nc) {
					count++;
					if (count == 5) {
						five = true;
						break;
					}
					r = nr;
					c = nc;
					
				} else {
					r = nr;
					c = nc;
					count = 1;
				}
			}
			// 세로 대각
			Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {
				if (o1.get(1)==o2.get(1)) {
					return o1.get(0)-o2.get(0);
				}
				return o1.get(1) - o2.get(1);
			});
			
			
			iter = list.iterator();
			
			r = Integer.MIN_VALUE;
			c = Integer.MIN_VALUE;
			count = 1;
			while (iter.hasNext()&&!five) {
				List<Integer> token = iter.next();
				int nr = token.get(0);
				int nc = token.get(1);
				if (c == nc && r+2==nr) {
					count++;
					if (count == 5) {
						five = true;
						break;
					}
					r = nr;
					c = nc;
					
				} else {
					r = nr;
					c = nc;
					count = 1;
				}
			}
			
			if (five) {
				System.out.printf("#%d %s%n",tc,"YES");
			} else {
				System.out.printf("#%d %s%n",tc,"NO");
			}
			
		}
		
		
		
		
	}

}
