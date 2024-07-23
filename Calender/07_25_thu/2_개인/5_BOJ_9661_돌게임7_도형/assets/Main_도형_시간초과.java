package BOJ._9661_돌게임7;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	// 하위 모델 중 어느 한 개라도 후공이 나오면 상위 모델에서 선공이 이기고
	// 하위 모델이 모두 선공이면 상위 모델에서 후공이 이김
	// 후공이 0 , 선공이 1이면 되겠군
	// 하위 는 곱셈으로 계산하고
	// 하위 -> 상위 는 1 - x 꼴로 하자.
	static Map<Long, Integer> dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		dp = new HashMap<Long, Integer>();
		System.out.println(getAnswer(N) == 0 ? "CY" : "SK");
		sc.close();
		
	}

	private static int getAnswer(long n) {
		if (dp.containsKey(n)) return dp.get(n);
		if (n == 1) return 1;
		if (n == 0) return 0;
		
		int floorOfLog_4 = (int) (Math.log(n) / Math.log(4));
		int answer = 1;
		for (long i = floorOfLog_4; i>=0; i--) {
			answer *= getAnswer(n - (long) Math.pow(4, i));
			if (answer == 0) break;
			
		}
		dp.put(n, 1 - answer);
		return 1 - answer;
	}

}
