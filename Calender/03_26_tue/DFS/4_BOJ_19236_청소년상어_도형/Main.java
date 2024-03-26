package BOJ._19236_청소년상어;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,-1,-1,-1,0,1,1,1};
	
	static int answer = 0;
	

	
	public static void main(String[] args) {
		
		// 복잡시럽다
		
		Scanner sc = new Scanner (System.in);
		
		// 4by4 고정 사이즈
		// 16마리 물고기 고정
		
		int[][] fishes = new int[16][3]; // r, c, dir
		int[] shark = new int[3]; // r, c, dir
		
		int n = 0; // 이건 입력받는 순서이자, Map을 쭉 늘어뜨렸을때의 좌표(자리)가 됨
		int eaten = 0;
		while (n <16) { //16개를 입력받을 건데
			int i = sc.nextInt()-1; // 이게 물고기 번호 = fishes 배열에서의 index
			
			fishes[i][0] = n /4; // 행번호로 변환
			fishes[i][1] = n %4; // 열번호로 변환
			fishes[i][2] = sc.nextInt()-1;
			
			if(n == 0) { // 0 번째 자리에서 상어가 시작하니까 0번 물고기는 시작부터 먹힌다.
				
				fishes[i][0] = -1; // -1로 이 물고기 (i 번째) 가 먹혔음을 나타내주자
				fishes[i][1] = -1;
				
				shark[2] = fishes[i][2]; // 상어에 방향을 저장하고 나서!
				fishes[i][2] = -1; // 방향도 그냥 확실하게 -1로 바꿔주자 (죽은 물고기인지 판단할 때는 사실 한 값만 확인한다.)
				eaten += i+1; // 0번 자리에 있는게 꼭 0번 물고기가 아니고, 입력받은 물고기 번호를 더해 줘야 한다.
			}
			
			n++;
		}

		moveShark(fishes,shark,eaten);
		System.out.println(answer);
		sc.close();
	}
	

	
	
	public static int[][] moveFishes(int[][] fishes, int[] shark) {
		for (int i = 0; i<16; i++) { // 모든 물고기에 대해서
			
			if (fishes[i][0] == -1) continue; // 먹힌 물고기면 pass
			
			int cnt = 0; // 최대 8방향을 다 돌아야 갈 수 있는 자리가 나올 수 있음.
			out:
			while(cnt <=7) {
				int dir = fishes[i][2];
				int r = fishes[i][0];
				int c = fishes[i][1];
				
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !(nr == shark[0] && nc == shark[1])) { //범위 내에 있고, 상어 자리가 아니라면
					for (int j = 0; j < 16; j++) {
						// 내가 가고 싶은 자리에 물고기가 있을 경우
						if (fishes[j][0] == nr && fishes[j][1] == nc) {
							
							// 자리만 스왑 (방향은 스왑하면 안됨!!)
							int tempI = fishes[j][0];
							int tempJ = fishes[j][1];
							
							fishes[j][0] = fishes[i][0];
							fishes[j][1] = fishes[i][1];
							
							fishes[i][0] = tempI;
							fishes[i][1] = tempJ;
							
							break out; // 여기 안들어왔다면, 이 아래가 실행되게끔 브레이크
						}
					}
					// 내가 가고 싶은 자리에 물고기가 없고, 빈 자리일 경우에는 그냥 가면 된다.
					fishes[i][0] = nr;
					fishes[i][1] = nc;
					break out;
					
				} else {
					// 경계를 벗어나거나 상어자리라면 방향을 반시계로 45도 회전
					fishes[i][2] = (dir +1) % 8;
					cnt++;
				}
			}
		}
		return fishes;
	}
	
	
	public static void moveShark(int[][] fishes, int[] shark, int eaten) {
		
		// 맵을 여러방향으로 갖고 내려가야 해서 ""non-static + 복사 콤보를 쓴다!...""
		// 스태틱으로 선언해 버리면,
		// DFS로 바닥을 찍고, 이전 분기점으로 다시 돌아와서 맵을 보았을 때
		// 분기점의 맵이 아니라 방금찍은 바닥의 맵이 보이겠지?!

		fishes = moveFishes(fishes, shark);
		// 물고기를 모두 움직이고,


		// 이해하기 편하게 + 실수 안하게 변수명으로 바꾼다.
		int sharkR = shark[0];
		int sharkC = shark[1];
		int sharkDir = shark[2];
		
		// 내 방향에 있는 물고기 들을 후보에 넣고, 각 물고기
		List<Integer> candidates = new ArrayList<>();
		
		out:
		for (int k = 1; k <= 3; k++) { // 상어의 전진거리 (1칸, 2칸, 3칸) // 맵이 4by4라 전진 할 수 있는 칸은 최대 3칸!
			int nr = sharkR + k*dr[sharkDir];
			int nc = sharkC + k*dc[sharkDir];
			
			if(nr >= 0 && nr <4 && nc >= 0 && nc <4) {
				for (int i = 0; i <16; i++) {
					if (fishes[i][0] == nr && fishes[i][1] == nc) {
						// 그 자리에 물고기(i번째)가 있다면,
						// 후보에 추가
						candidates.add(i);
						continue out;
					}
				}
			}
		}
		
		// 기저 조건은 여기!
		if(candidates.size() == 0) { // 먹을 수 있는 물고기를 찾지 못했으면
			answer = Math.max(answer, eaten);
			return;
		}
		
		for (int i = 0; i < candidates.size(); i++) { // 여따 사이즈 넣는 거 원래 위험함! 지금은 추가를 안해주니까 그냥 썼다. 원래는 변수 하나 만들고 저장해 쓰는게 맞다.
			
			// 물고기 배열을 복사해서 넘겨주거나
			int[][] tempFishes = new int[16][3];
			for(int j = 0; j < 16; j++) {
				tempFishes[j] = Arrays.copyOf(fishes[j], fishes[j].length);
			}
			
			// 잡아 먹엇
			shark[0] = tempFishes[candidates.get(i)][0];
			shark[1] = tempFishes[candidates.get(i)][1];
			shark[2] = tempFishes[candidates.get(i)][2];

			// 잡아 먹혔
			tempFishes[candidates.get(i)] = new int[] {-1,-1,-1};

			// eaten 업데이트 해주고 계속 진행
			moveShark(tempFishes, shark, eaten+candidates.get(i)+1);
			
			// 이 밑에 다시 restore ( 안먹었지롱~) 해주거나.
			// 아직 해본 적이 없어서 엄두가 안난다..
			// 언젠가 엄격한 메모리 규칙을 만나면 해야할 날이 오겠지...
		}
	}

}
