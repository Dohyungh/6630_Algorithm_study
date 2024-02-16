package BOJ._2810_컵홀더;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		// 정답 = min 사람수 , 컵홀더 개수
		// 컵홀더 개수 = 1+ 묶음 수(cnt)
		// 1묶음 = LL 혹은 S
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		char[] seats = sc.next().toCharArray();
		
		int cnt = 1;
		
		int idx = 0;
		while(idx <seats.length) { // 왜 for문으로 하려면 힘들까 // 포인터는 while이 편한듯
			if(seats[idx]== 'L') {
				cnt++;
				idx+=2;
				
			} else {
				cnt++;
				idx++;
			}
		}
		
		System.out.println(Math.min(N, cnt));
		
		
	}

}
