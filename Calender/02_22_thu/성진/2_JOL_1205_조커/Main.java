package jol_1205_조커;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 카드의 수 입력
		int N = sc.nextInt();

		// 0이 아닌 카드를 담을 list 생성
		List<Integer> numList = new ArrayList<Integer>();

		// 조커(숫자 0 카드)의 개수
		int numJoker = 0;

		// N개의 카드의 정보를 입력받음
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			// 만약 해당 카드가 조커가 아니고, 리스트에 없는 원소일 때만 add.
			if (num != 0 && !numList.contains(num)) {
				numList.add(num);
			}
			// 만약 해당 카드가 조커라면, 리스트에 add하지 않고, 조커의 개수만 1 늘려줌
			else if (num == 0) {
				numJoker++;
			}
		}

		// 만약 전체 카드가 조커였다면 조커의 수가 그대로 최대 스트레이트의 길이이므로 바로 출력
		if (numJoker == N) {
			System.out.println(N);
		}

		else {
			// 카드를 오름차순으로 정렬
			Collections.sort(numList);

			// makeStraight 사용자 정의 메소드 사용해서 answer를 구함
			int answer = makeStraight(numList, numJoker);

			System.out.println(answer);

		}

		sc.close();

	}

	// 최대 길이의 스트레이트를 구하는 메소드 makeStraight
	// 파라미터로 일반 카드가 담긴 List, 조커 카드의 수 numJoker가 필요함
	public static int makeStraight(List<Integer> list, int numJoker) {

		// 최대 길이의 스트레이트를 구하기 위한 최댓값 설정
		int max = Integer.MIN_VALUE;

		// 일반 카드를 순회하며(마지막 원소 제외)
		for (int i = 0; i < list.size() - 1; i++) {

			// 아래 while문 반복을 하며 증가할 인덱스를 초기화
			// idx는 스트레이트인지 확인하며 넘어갈 카드의 인덱스 번호
			int idx = i;
			int straight = 1;
			int joker = numJoker;

			while (true) {
				// 만약 idx가 마지막 원소의 인덱스 번호인 경우
				if (idx >= list.size() - 1) {
					// 남은 조커 카드를 전부 스트레이트 길이에 더해주고, max값을 갱신하고 while문을 탈출
					straight += joker;
					joker = 0;
					if (straight > max) {
						max = straight;
					}
					break;
				}

				// 만약 다음 원소가 1만큼 크다면(즉, 스트레이트라면)
				if (list.get(idx) + 1 == list.get(idx + 1)) {
					// 스트레이트 길이를 1 증가하고, idx를 증가하며 while문을 반복
					straight++;
					idx++;
				}
				// 만약 다음 원소가 2 이상 크다면(스트레이트가 아니라면)
				else {
					// 조커가 남아있는 경우
					if (joker > 0) {
						// 조커가 다음 원소 사잇값들을 전부 채울 수 있다면(조커의 수가 다음 원소 값의 차이보다 크거나 같다면)
						if (joker >= list.get(idx + 1) - list.get(idx) - 1) {
							// 스트레이트의 길이를 그만큼 증가하고, 조커를 사용한 만큼 조커의 수에서 빼준다.
							straight += list.get(idx + 1) - list.get(idx);
							joker -= list.get(idx + 1) - list.get(idx) - 1;
							// idx를 증가하며 while문을 반복
							idx++;
						}
						// 조커가 다음 원소 사잇값들을 온전히 채울 수 없다면
						else {
							// 남은 조커 카드를 전부 스트레이트 길이에 더해주고, max값을 갱신하고 while문을 탈출
							straight += joker;
							joker = 0;
							if (straight > max) {
								max = straight;
							}
							break;
						}
					}
					// 조커가 남아있지 않은 경우
					else {
						// max값을 갱신하고, while문을 탈출
						if (straight > max) {
							max = straight;
						}
						break;
					}
				}
			}

		}

		return max;
	}

}
