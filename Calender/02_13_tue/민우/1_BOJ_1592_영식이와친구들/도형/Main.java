package BOJ._1592_영식이와친구들;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int[] catched = new int[N+1];
		
		int pn = 1; // 일단 한 놈 받았음
		catched[pn]++;
		
		int answer = 0;
		
		while (true) {
			if (M == 1) { // 거참 귀찮게 하네
				break;
			}
			if (catched[pn] % 2 == 0) { //받은 놈이 짝수면 반시계
				pn = getCounterClock(pn,N,L);
				answer++;
				catched[pn]++;
				if (catched[pn] >= M) break;
			}
			else if (catched[pn] % 2 == 1) { //받은 놈이 홀수면 시계
				pn = getClock(pn,N,L);
				answer++;
				catched[pn]++;
				if (catched[pn] >= M) break;
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
	
	//복잡할 줄 알고 따로 뻈는데 별로 안 복잡하네
	public static int getClock(int pn, int N, int L) {
		int dest = 0;
		dest = pn+L;
		if (dest>N) dest = dest - N;
		return dest;
	}
	
	public static int getCounterClock(int pn, int N, int L) {
		int dest = 0;
		dest = (pn-L);
		if (dest<=0) dest = N + dest;
		return dest;
	}

}
