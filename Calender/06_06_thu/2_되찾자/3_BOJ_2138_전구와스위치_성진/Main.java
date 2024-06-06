package boj_2138_전구와스위치;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        
        // 현재 상태와 목표 상태 입력 받기
        String init = sc.nextLine();
        String target = sc.nextLine();

        // 첫 번째 스위치를 누르는 경우와 누르지 않는 경우를 모두 고려해야 최솟값을 찾을 수 있음
        // 따라서 두 가지 경우를 모두 계산
        int result1 = getSwitchNum(N, init, target, false);
        int result2 = getSwitchNum(N, init, target, true);

        // 두 결과 중 유효한 최솟값 찾기
        if (result1 == -1 && result2 == -1) {
            System.out.println(-1);
        } else if (result1 == -1) {
            System.out.println(result2);
        } else if (result2 == -1) {
            System.out.println(result1);
        } else {
            System.out.println(Math.min(result1, result2));
        }
    }

    // 스위치 조작 횟수를 계산하는 메소드
    private static int getSwitchNum(int N, String init, String target, boolean switchOne) {
    	// char형으로 쪼개기
        char[] bulbs = init.toCharArray();
        // 스위치를 누른 횟수를 저장하는 변수 cnt
        int cnt = 0;

        // 첫 번째 스위치를 누르는 경우
        if (switchOne) {
            switchLight(bulbs, 0);
            cnt++;
        }

        // 두 번째 스위치부터 (중요)순차적으로 처리
        for (int i = 1; i < N; i++) {
            // i-1번 전구가 목표 상태와 다를 경우 i번 스위치를 누르고, 다음 스위치로 이동
            if (bulbs[i - 1] != target.charAt(i - 1)) {
                switchLight(bulbs, i);
                cnt++;
            }
        }

        // 최종 상태가 목표 상태와 같은지 확인
        for (int i = 0; i < N; i++) {
        	// 순차적으로 진행했을 때 목표 상태가 되지 않았다면...?
        	// 절대 목표 상태를 만들 수 없음을 의미
            if (bulbs[i] != target.charAt(i)) {
                return -1;
            }
        }

        return cnt;
    }

    // 스위치를 눌러 전구 상태를 변경하는 메소드
    private static void switchLight(char[] bulbs, int index) {
        // 현재 전구와 양 옆 전구의 상태 변경
    	// 1. 현재 전구와 오른쪽 전구의 상태 변경
        for (int i = index; i <= index + 1; i++) {
        	if (i < bulbs.length) { 		
        		if (bulbs[i] == '0') {
        			bulbs[i] = '1';
        		}
        		else {
        			bulbs[i] = '0';
        		}
        	}
        }
        // 2. 왼쪽 전구의 상태 변경
        if (index - 1 >= 0) {
            if (bulbs[index-1] == '0') {
            	bulbs[index-1] = '1';
            }
            else {
            	bulbs[index-1] = '0';
            }
        }
    }
}
