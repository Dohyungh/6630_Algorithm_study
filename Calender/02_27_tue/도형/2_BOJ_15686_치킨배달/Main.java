package BOJ._15686_치킨배달;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<boolean[]> cases = new ArrayList<>();
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N][N];
		
		// 리스트를 안쓰려면 입력 받을 때 개수를 저장해놔야 함.
		int cntChicken = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = sc.nextInt();
				map[i][j] = num;
				if (num == 2) {
					cntChicken++;
				}
			}
		}
		
		int[][] chicken = new int[cntChicken][2];
		
		//치킨집 좌표 다받아서 chicken 에 저장
		int tmp =0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j]==2) {
					chicken[tmp][0] = i;
					chicken[tmp][1] = j;
					tmp++;
				}
			}
		}
		
		// cctv 마다 방향이 있는게 아니고
		// 해당 치킨집을 살릴지 죽일지니까 visited 배열이 훨씬 간단함.
		boolean[] visited = new boolean[cntChicken];
		
		getCases(0,M,0,visited);
		
		int answer = Integer.MAX_VALUE;
		
		for (boolean[] aCase : cases) {
			answer = Math.min(answer, cityChickenDist(map, chicken, aCase));
		}
		
		System.out.println(answer);
		

		
		sc.close();
		
		
		
	}
	
	// 가정집 좌표 i, j , 치킨집 좌표가 들어있는 배열 chicken, 남길 치킨집 인덱스가 들어있는 aCase
	// 한 가정집에 대해 살아남은, 가장 가까운 치킨집 거리를 주는 함수 
	public static int getDistance(int i, int j, int[][] chicken, boolean[] aCase) {
		
		int answer = Integer.MAX_VALUE;
		for (int k = 0; k < chicken.length; k++) {
			if (aCase[k]) {
				int dist = Math.abs(chicken[k][0] - i) + Math.abs(chicken[k][1] - j);
				if (dist < answer) {
					answer = dist; 
				}				
			}
		}
		return answer;
	}
	
	// 도시의 치킨 거리 구하는 함수
	public static int cityChickenDist(int[][] map, int[][] chicken, boolean[] aCase) {
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0 ; j < map[0].length; j++) {
				if (map[i][j] == 1) sum+=getDistance(i,j,chicken, aCase);
			}
		}
		return sum;
	}
	
	// start 를 빼먹었었네
	// 다 이해했다고 생각했는데.. 어렵다잉..
	public static void getCases(int depth, int M, int start, boolean[] visited) {
		if (depth == M) {
			cases.add(Arrays.copyOf(visited, visited.length));
			return;
		}
		
		for (int i = start; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				getCases(depth+1, M, i+1, visited);
				visited[i] = false;
				
			}
		}
	}

}
