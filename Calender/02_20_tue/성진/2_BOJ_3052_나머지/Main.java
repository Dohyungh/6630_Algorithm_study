package boj_3052_나머지;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// HashSet으로 구현된 set인 remainSet 생성
		Set<Integer> remainSet = new HashSet<Integer>();

		// 10개의 수를 입력받으며 아래 구문을 반복
		for (int i = 0; i < 10; i++) {
			// 입력받은 수를 num에 저장
			int num = sc.nextInt();
			// 만약 num을 42로 나눈 나머지가 remainSet에 없다면 add.
			if (!remainSet.contains(num % 42)) {
				remainSet.add(num % 42);
			}

		}

		// size 메소드를 활용해 집합의 크기 즉, 정답을 출력
		System.out.println(remainSet.size());

		sc.close();

	}

}
