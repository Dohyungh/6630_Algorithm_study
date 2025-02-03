package boj_2473_세용액;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		long min = Long.MAX_VALUE;

		int lft = 0;
		int rgt = N - 1;

		int lft_ans = arr[lft];
		int mid_ans = 0;
		int rgt_ans = arr[rgt];

		while (lft < rgt) {
			long sum = arr[lft] + arr[rgt];

			for (int i = lft + 1; i < rgt; i++) {
				long tmp = Math.abs(sum + arr[i]);

				if (tmp < min) {
					min = tmp;
					lft_ans = arr[lft];
					mid_ans = arr[i];
					rgt_ans = arr[rgt];
				}
			}

			if (min == 0) {
				break;
			}

			if (sum > 0) {
				rgt--;
			} else {
				lft++;
			}
		}

		System.out.println(lft_ans + " " + mid_ans + " " + rgt_ans);

//        
//        long closestSum = Long.MAX_VALUE; // 가장 가까운 합을 저장할 변수
//        int[] result = new int[3]; // 가장 가까운 합을 이루는 세 용액의 값을 저장할 배열
//        
//        // 첫 번째 용액 선택
//        for (int i = 0; i < N - 2; i++) {
//            int left = i + 1; // 두 번째 용액을 가리키는 포인터
//            int right = N - 1; // 세 번째 용액을 가리키는 포인터
//            
//            // 두 번째 용액과 세 번째 용액을 투 포인터로 탐색
//            while (left < right) {
//                long sum = (long)solutions[i] + solutions[left] + solutions[right]; // 세 용액의 합 계산
//                
//                // 현재 합이 가장 0에 가까운지 확인
//                if (Math.abs(sum) < Math.abs(closestSum)) {
//                    closestSum = sum;
//                    result[0] = solutions[i];
//                    result[1] = solutions[left];
//                    result[2] = solutions[right];
//                }
//                
//                // 합이 0보다 작으면 왼쪽 포인터를 오른쪽으로 이동
//                if (sum < 0) {
//                    left++;
//                } 
//                // 합이 0보다 크면 오른쪽 포인터를 왼쪽으로 이동
//                else {
//                    right--;
//                }
//            }
//        }
//        
//        // 결과 배열을 오름차순으로 정렬하여 출력
//        Arrays.sort(result);
//        System.out.println(result[0] + " " + result[1] + " " + result[2]);
//        
//        sc.close();
	}
}
