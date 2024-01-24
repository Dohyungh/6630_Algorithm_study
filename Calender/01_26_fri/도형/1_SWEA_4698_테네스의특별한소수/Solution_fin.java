package SWEA._4698_테네스의특별한소수;

import java.util.Scanner;

public class Solution_fin {
public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);
	int T = sc.nextInt();
	boolean[] not_prime = new boolean[1000000+1]; // 주어진 수 범위 안의 모든 수의 소수 여부를 미리 체크 해놓기, 이걸 tc for loop 안에 넣으면 시간 초과 // 초기값이 false 라서 not_prime으로 지음
	not_prime[0] = true; 
	not_prime[1] = true; //1은 소수도 합성수도 아니다
	
	for(int i = 2; i<=1000; i++) { // 1000000의 제곱근 1000까지만
		if (!not_prime[i]) { // '소수'이면
			for(int j = i+1; j<=1000000;j++) { //i 다음수부터 탐색해서 
				if ((j % i) == 0 && !not_prime[j]) { //나누어 떨어지고, 그 수가 아직 소수라고 체크돼어 있으면, // 한번 껐으면 다시 키면 안됨
					not_prime[j] = !not_prime[j];
				}
			}				
		}	
	}
	
	for(int tc=1; tc<=T;tc++) {
		int D = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		
		boolean[] hasD = new boolean[B+1]; //D를 가졌는지 boolean 으로 체크

		for(int i = 2; i<=B; i++) {
			int num = i;
			while (num != 0) {
				if ((num%10) == D) {//1의자리수를 체크
					hasD[i] = !hasD[i];
					break;
				} else {
					num /= 10; //다음 자리수(<-)을 체크
				} 
			}		
		}
		

		int ans = 0;
		for (int i = A;i<=B;i++) {
			if (!not_prime[i] && hasD[i]) { //"소수"이고, "D"가 있으면, 변수명을 잘 지으면 헷갈릴 일도 없다.
				ans++;
			}
		}
		
		System.out.printf("#%d %d%n",tc, ans);
	}

}
	
	
}