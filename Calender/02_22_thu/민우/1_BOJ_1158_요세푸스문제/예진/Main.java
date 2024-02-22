package BOJ_1158_요세푸스문제;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> people = new ArrayList<>();			// 남은 원소들끼리 인덱스 순환을 해야해서 리스트 사용
		int idx = K-1;										// 0부터 시작이어서 K-1
		
		for (int i = 1; i <= N; i++) {						// 1부터 N까지 값 할당
			people.add(i);
		}
		
		System.out.print("<");								// 출력 시작
		while (people.size() > 1) {							// 리스트에 원소가 하나 남을 때까지(출력 형식 때문에)
			
			System.out.print(people.get(idx) + ", ");		// 대상 원소를 출력하고
			people.remove(idx);								// 삭제
			
			idx = (idx + K - 1) % people.size();			// 원소가 삭제되면 인덱스가 하나씩 당겨짐으로 K-1만큼씩만 이동
		}
		
		System.out.print(people.get(idx) +">");				// 마지막 원소 출력하고 괄호 닫기
		sc.close();											// 문단속
	}
}