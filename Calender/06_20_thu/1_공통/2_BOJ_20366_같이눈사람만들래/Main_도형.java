package BOJ._20366_같이눈사람만들래;

/*
1쌍 배열로 만든 다음에(O(N^2)) 정렬 후(Nlog(N)) 인접 원소 탐색하는 방법으로 품

알고리즘 (정렬 + 투포인터)을 미리 알고 푸니까 좀 더 투포인터스러운 풀이가 있을 거라 기대해서 계속 고민해봤는데,
찾지 못했고
(로직을 갖고 포인터를 왔다갔다 하면 시간복잡도 O(N)으로 끊을 수 있는 그런..)

구글링 해본 풀이 중에 가장 좋았던 거는
4개의 포인터 중에 바깥 2개는 그냥 완탐이고 (O(N^2))
나머지 2개만 O(N)인 풀이가 있었다.
https://ansohxxn.github.io/boj/20366/
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 길이가 정해져있지만 (N Comb 2) 귀찮아서 List
		List<int[]> coupleArr = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 중복되게 들어가지 않도록 조건 걸어주기
				if (i < j) {
					// 작은 인덱스, 큰 인덱스, 눈사람 크기로 배열 만들어서 넣어주기
					coupleArr.add(new int[] {i, j, arr[i]+arr[j]});
				}
			}
		}

		// 정렬
		Collections.sort(coupleArr, (o1, o2) -> {return o1[2] - o2[2];});
		
		int answer = Integer.MAX_VALUE;

		// 눈사람(완성) 리스트에서 하나뽑고, 그 바로 다음꺼 뽑았을 때,
		// 인덱스가 겹치는게 없으면
		// 그 차이를 answer 변수에 업데이트 하고 바로 break
		for (int i = 0 ; i <coupleArr.size(); i++) {
			int[] a = coupleArr.get(i);
			
			for (int j = i+1; j < coupleArr.size(); j++) {
				int[] b = coupleArr.get(j);
				
				if (a[0] != b[0] && a[0] != b[1] && a[1] != b[0] && a[1] != b[1]) {
					answer = Math.min(answer, Math.abs(a[2] - b[2]));
					break;
				}
			}
		}
		System.out.println(answer);
		
		sc.close();
		
	}

}
