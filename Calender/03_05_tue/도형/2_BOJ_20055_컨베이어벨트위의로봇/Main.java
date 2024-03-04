package BOJ._20055_컨베이어벨트위의로봇;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[] durable;
	static boolean[] isRobot;
	static int N;
	static int K;
	
	public static void main(String[] args) {
		//queue 쓸 힘도,,
		//index로 바꿔 쓸 힘도,,
		//없다
		//그저 구현
		//그저 시키는 대로..
		//근데 그게 맞는 듯?!
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		durable = new int[2*N];
		isRobot = new boolean[N];
		
		for (int i = 0; i < 2*N; i++) durable[i] = sc.nextInt();
		
		int answer = 0;
		while(true) {
			answer++;
			spin();
			isRobot[N-1] = false; //바로 내린다
			move();
			isRobot[N-1] = false;
			load();		
			if (countZero()>=K) break;
			
			
		}
		
		System.out.println(answer);
		
		
		
		
	}
	
	//1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
	private static void spin() {
		
		//isRobot
		for (int i = isRobot.length-1; i >=1; i--) {
			isRobot[i] = isRobot[i-1];
		}
		isRobot[0] = false;
		//durable
		int temp = durable[durable.length-1];
		for (int i = durable.length-1; i >=1; i--) {
			durable[i] = durable[i-1];
		}
		durable[0] = temp;
		
	}
	 
	//2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
	//2.1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
	private static void move() {
		for (int i = N-1; i >0; i--) {
			if (!isRobot[i] && isRobot[i-1] && durable[i]>=1) {
				isRobot[i-1] = false;
				isRobot[i] = true;
				durable[i]--;
			}
		}
	}
	
	//3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
	private static void load() {
		if (durable[0] > 0) {
			isRobot[0] = true;
			durable[0]--;
		}
		
	}
	
	//4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
	public static int countZero(){
		int cnt = 0; 
		for (int i = 0; i < durable.length; i++) {
			if (durable[i] <= 0) cnt++;
		}
		return cnt;
	}

}
