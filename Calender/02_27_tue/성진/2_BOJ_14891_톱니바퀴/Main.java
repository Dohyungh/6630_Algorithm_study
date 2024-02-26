package boj_14891_톱니바퀴;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		
		// 0번, 1번, 2번, 3번 톱니바퀴
		// 각 톱니바퀴별 톱니 8개의 자성(N극, S극)의 정보
		int[][] gearArr = new int[4][8];
		
		
		// 톱니의 정보를 입력받아 저장
		for (int i=0; i<4; i++) {
			String[] strLine = sc.nextLine().split("");
			for (int j=0; j<8; j++) {
				gearArr[i][j] = Integer.parseInt(strLine[j]);
			}
		}
		
		
		// 회전횟수
		int K = sc.nextInt();
		
		// 회전횟수만큼 반복
		for (int k=0; k<K; k++) {
			
			// 톱니바퀴의 번호는 인덱스를 맞추기 위해 (입력값-1)로 num에 저장
			int num = sc.nextInt()-1;
			int dir = sc.nextInt();
			
			// decideDir 사용자 정의 메소드로 옆 톱니와 자성을 고려했을 때 각 번호별 회전방향을 저장한 dirArr 계산
			// dirArr이 0이라면 회전 X, 1이라면 해당 인덱스의 톱니가 시계 방향 회전, -1이라면 반시계 방향 회전
			int[] dirArr = decideDir(num, dir, gearArr);
			
			
			// 새롭게 회전된 톱니의 정보가 담길 새 arr 초기화
			int[][] newGearArr = new int[4][8];
			
			
			// 각 톱니마다 회전을 시행
			for (int i=0; i<4; i++) {
				// 회전을 해야 한다면(dirArr[i]가 0이 아닌 1 또는 -1이라면)
				if (dirArr[i] != 0) {
					// 각 톱니의 인덱스를 방향(시계 or 반시계)에 맞게 1칸씩 조정
					for (int j=0; j<8; j++) {
						newGearArr[i][j] = gearArr[i][(j-dirArr[i]+8)%8];
					}
				}
				// 회전하지 않는다면 그대로 값을 이어받는다.
				else {
					for (int j=0; j<8; j++) {
						newGearArr[i][j] = gearArr[i][j];
					}
				}
			}
			// 회전 1회가 끝나면 다시 gearArr에 회전된 상태를 저장
			gearArr = newGearArr;
		}
		
		
		
		
		
		
		// 출력할 정답
		int answer = 0;
		
		// S극이면 점수를 획득
		for (int i=0; i<4; i++) {
			if (gearArr[i][0] == 1) {
				answer += Math.pow(2, i);
			}
		}
		
		System.out.println(answer);
		
		
		sc.close();
	}
	
	
	
	// 정보가 주어졌을 때 각 톱니별 방향을 지정하는 메소드 decideDir
	static int[] decideDir(int num, int dir, int[][] gearArr) {
		
		// 톱니의 번호를 저장한다.
		int gearNum = num;
		
		int[] dirArr = new int[4];
		dirArr[gearNum] = dir; 
		
		// 기준 톱니에서 오른쪽 비교
		// 회전하지 않을 때까지(인덱스 2와 6이 같아질 때까지) 반복.
		while(gearNum <= 2) {
			if (gearArr[gearNum][2] != gearArr[++gearNum][6]) {
				dir = dir * (-1);
				dirArr[gearNum] = dir;
			}
			else {
				break;
			}
		}
		
		// 다시 톱니 번호, 방향 초기화
		gearNum = num;
		dir = dirArr[gearNum];
		
		// 기준 톱니에서 왼쪽으로 비교
		// 회전하지 않을 때까지(인덱스 2와 6이 같아질 때까지) 반복.
		while (gearNum - 1 >= 0) {
			if (gearArr[gearNum][6] != gearArr[--gearNum][2]) {
				dir = dir * (-1);
				dirArr[gearNum] = dir;
			}
			else {
				break;
			}
		}
		
		return dirArr;
		
		
	}
	
	
//	static void printArr(int[][] arr) {
//		for (int i=0; i<arr.length; i++) {
//			for (int j=0; j<arr[i].length; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	
}
