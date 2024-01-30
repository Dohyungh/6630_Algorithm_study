package SWEA._5432_쇠막대기자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
		//제한시간 초과
		//코드에서 에러나는 줄 알았는데 그냥 순수 시간 복잡도 문제 였음.
		//괄호문자의 최대 개수가 10만 개라서!
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split("");
			
			
			Stack<Integer> stack = new Stack<>(); // List<Integer> 써야되는 줄 알았는데 stack 자체가 구현클래스인듯
							
			List<List<Integer>> sticks = new ArrayList<>();
			List<Integer> rasers = new ArrayList<>();
			
			for (int i = 0; i<str.length; i++) {
				if (i<str.length-1&& str[i].equals("(") && !str[i+1].equals(")")) { // 맨 뒤에 딸린 조건들은 '비'레이저 조건
					stack.add(i);
				} else if (i>1 && str[i].equals(")") && !str[i-1].equals("(")) {
					Integer start = stack.pop();
					Integer end = i;
					sticks.add(Arrays.asList(start,end));
				} else if (i<str.length-1&& str[i].equals("(") && str[i+1].equals(")")) { // 레이저 조건.
					rasers.add(i);
				}	
			}
			
			//여기서 시간초과 나는듯?!
			int sum = 0;
			for (List<Integer >stick : sticks) { // 각 스틱마다
				int count = 1;
				for (Integer raser : rasers) { // 레이저랑 만나는 지 검사
					if (raser>stick.get(0) && raser<stick.get(1)) {
						count++;
					}
				}
				sum+=count;
				
			}

			System.out.printf("#%d %d%n",tc,sum);
			
			
		}
		
	}

}
