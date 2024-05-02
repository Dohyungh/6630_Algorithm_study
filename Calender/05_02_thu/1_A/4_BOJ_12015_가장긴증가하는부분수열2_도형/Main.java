package BOJ._12015_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		// 여기까지 입력
		
		// 임시배열 생성
		int[] tempArr = new int[N];
		
		
		// 최초 시작 때 arr[0]를 넣어주자
		int len = 1;
		tempArr[0] = arr[0];
		
		// arr 를 하나씩 읽으면서
		// 마지막 원소보다 크면 그냥 맨 뒤에 붙여주고,
		// 아니라면, 자기보다 큰 놈 중에 제일 작은 놈을 찾아서 바꿔준다.
		
		// 이렇게 하면, temparr 는 계속 오름차순 정렬되고,
		// 최대 길이는 계속 저장되면서 
		// 과거는 계속 가장 유망한 원소로 갈아 끼워진다.
		
		for (int i = 1; i < N; i++) {
			if (arr[i] > tempArr[len-1]) {
				
				tempArr[len++] = arr[i];
				
			} else {
				
				int idx = findIndex(arr[i], tempArr, len);
				tempArr[idx] = arr[i];
			}
		}
		
		System.out.println(len);
		
		
		
		
	}

	private static int findIndex(int num, int[] tempArr, int len) {
		
		int left = 0;
		int right = len-1;
		int mid = (left+right) / 2;
		
		while (left < right) {
			if (tempArr[mid] >= num) right = mid;
			if (tempArr[mid] < num) left = mid+1;
			mid = (left + right) / 2;
			
		}
		
		return right;
	}

}
