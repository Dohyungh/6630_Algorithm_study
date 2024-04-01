package SWEA._모의_보호필름;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
 
public class Solution {
     
    // 이게 어떻게 시간초과야
     
	// 풀이를 대충 설명하자면,
	
	// getCases 에서 조작을 가할 층을 1개 고를 떄, 2개 고를 때, ... K 개 고를 떄의 '층'을 선택하는 경우의 수를 모두 구하고
	// getDetailCases 에서 각 Cases에 대해 A 로 할지 B로 할지를 다시 모두 결정해 최종 경우의 수를 반환해줌.
	// 결국 시그마(i = 1부터 K 까지) (D Comb i) * (2^i) 의 경우의 수를 모두 탐색하는 것임.
	// 근데 이방식은 모든 경우의 수를 모두 탐색하기 때문에 시간초과가 날 수 밖에 없음.
	// 공식 해설이랑, 질문 게시판에서 하라는 백트래킹을 아무리 해도 결국 가장 큰 D 와 W에서 시간 초과가 난다.
	// 결국 근본 알고리즘이 틀려버리면 원래 벌목 백트래킹이던 것도 잔가지 백트래킹으로 전락할 수 밖에 없다는 뜻.
	
	
    static List<int[]> cases;
    static int answer;
    static int K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int tc = 1;  tc <= T; tc++) {
            int D = sc.nextInt();
            int W = sc.nextInt();
            K = sc.nextInt();
             
            int[][] map = new int[D][W];
             
            for (int i = 0; i<D; i++) {
                for (int j = 0; j<W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            if (check(map, K)) {
                System.out.printf("#%d %d%n", tc, 0);
                continue;
            }
            cases = new ArrayList<int[]>();
            boolean[] visit = new boolean[D];
            answer = K;
            for (int i = 1; i<=K; i++) {
            	
            	getCases(0, i, D, visit, 0,map);
            }
             
             
 
            System.out.printf("#%d %d%n", tc, answer);
        }
    }
     
    private static int[][] fill(int[] aCase, int[][] tempMap) {
         
         
        for (int i = 0; i < aCase.length; i++) {
            if (aCase[i] != -1) {
                for (int j = 0; j <tempMap[0].length; j++) {
                    tempMap[i][j] = aCase[i];
                }
                 
            }
        }
        return tempMap;
    }
    

    // map을 받으면 K 개로 연속 같은 층이 나오는 지 확인해 주는 메서드. 
    // 모든 열에서 체크에 통과하면 True 를 반환
    public static boolean check(int[][] map, int K) {
        int D = map.length;
        int W = map[0].length;
         
        boolean answer = true;
        for (int i = 0; i <W; i++) {
            boolean found = false;
            out:
            for (int j = 0; j <=D-K; j++) {
                int now = map[j][i];
                for (int k = 0; k <K; k++) {
                    if (map[j+k][i] != now) continue out;
                }
                found = true;
            }
            if (!found) {
                answer = false;
                break;
            }
        }
         
        return answer;
         
             
    }
    
    //
    public static void getCases(int depth,int r,int D, boolean[] visit, int start, int[][] map) {
        //기저 조건
        if (depth ==r && depth <answer) {
        	int[] indexes = new int[r];
        	int idx = 0;
        	for (int i = 0; i < visit.length; i++) {
        		if (visit[i]) {
        			indexes[idx] = i;
        			idx++;
        		}
        	}
        	
            int[] output = new int[visit.length];
            for (int i = 0; i < output.length; i++) {
                output[i] = -1;
            }

            getDetailCases(0,output,map,indexes);
            return;
                 

        }

        // 밑에는 그냥 Combination
        //visit은 D사이즈
        for (int i = start; i <D; i++) {
            if (!visit[i] && depth <answer) {
                visit[i] = true;
                getCases(depth+1,r,D,visit,start+1,map);
                visit[i] = false;
            }
        }
        
         
         
    }
 
    private static void getDetailCases(int idx, int[] output, int[][] map, int[] indexes) {
        //기저 조건
        if (idx >= indexes.length && indexes.length < answer) {
             
            int[][] tempMap = new int[map.length][];
            for (int i = 0;i<map.length; i++) {
                tempMap[i] = Arrays.copyOf(map[i], map[i].length);
            }
             
            tempMap = fill(output, tempMap);
//        	System.out.println(Arrays.toString(output));
        	
            if (check(tempMap,K)) {
                int cnt = 0;
                for (int x = 0; x < output.length; x++) {
                    if (output[x] != -1) cnt++;
                }
                answer = Math.min(answer, cnt);
                 
            }
            return;
        }
        // 그냥 0으로 채울지 1로 채울지를 내려 보냄
        
        if (indexes.length < answer) {
        	
        	output[indexes[idx]] = 1;
        	getDetailCases(idx+1,output,map,indexes);
        	output[indexes[idx]] = 0;
        	getDetailCases(idx+1,output,map,indexes);
        }

        

         
         
         
         
    }
 
}