package SWEA._5356_의석이의세로로말해요;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution2 {
	public static void main(String[] args) { // 예시랑 실제랑 입력 다른 것 주의
	
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc =1; tc<=T;tc++) {
			char arr[][]=new char[5][];
			arr[0] = sc.next().toCharArray();
			arr[1] = sc.next().toCharArray();
			arr[2] = sc.next().toCharArray();
			arr[3] = sc.next().toCharArray();
			arr[4] = sc.next().toCharArray();
			
//			string을 바로 한글자씩 담는 배열로 만들어줌. 길이는 string의 길이와 같게 자동으로!
			
			for (int i =0;i<=15;i++) {
				for (int j = 0;j<5;j++) {
					if (arr[j].length <=i) continue;
					else System.out.printf("%s",arr[j][i]); // # 은 귀찮아서 안씀!
				}
			}


			
		}
		
	}
	

	
}
