package boj_1043_거짓말;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int[] p;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 사람의 수
		int M = sc.nextInt(); // 파티의 수

		int trueNum = sc.nextInt();

		// 진실을 아는 사람 수가 0일 때 처리해줘야 런타임에러 발생 X(내 코드 기준)
		if (trueNum == 0) {
			System.out.println(M);
			return;
		}

		// 진실을 아는 사람들의 번호를 담는 리스트
		List<Integer> truePeople = new ArrayList<Integer>();
		for (int i = 0; i < trueNum; i++) {
			int truePerson = sc.nextInt();
			truePeople.add(truePerson);
		}

		// union-find의 p배열
		p = new int[N + 1];
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}

		// 각 파티의 인원을 담는 int[] 배열을 묶는 list 생성
		List<int[]> peopleList = new ArrayList<int[]>();
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			int[] people = new int[num];
			for (int j = 0; j < people.length; j++) {
				people[j] = sc.nextInt();
			}
			peopleList.add(people);
		}

		// 진실을 아는 사람들끼리 union을 실행 => 진실을 아는 모든 사람들이 첫 번째 원소를 부모를 가지게 될 것
		for (int i = 1; i < truePeople.size(); i++) {
			union(truePeople.get(0), truePeople.get(i));
		}

		// 각 파티의 인원끼리 union을 실행
		for (int i = 0; i < peopleList.size(); i++) {
			int[] temp = peopleList.get(i);
			for (int j = 1; j < temp.length; j++) {
				union(temp[0], temp[j]);
			}
//            for (int j=0; j<p.length; j++) {
//            	System.out.print(p[j] + " ");
//            }
//            System.out.println();
		}

		int answer = 0;

		// 다시 각 파티를 순회하며 말할 수 있는지 여부를 검사
		for (int i = 0; i < peopleList.size(); i++) {
			int[] temp = peopleList.get(i);
			boolean canTell = true;
			// 해당 파티 인원(한 명이라도)의 부모가 진실을 아는 사람들의 부모와 동일하다면 말을 못함
			for (int j = 0; j < temp.length; j++) {
				if (find(temp[j]) == find(truePeople.get(0))) {
					canTell = false;
					break;
				}
			}

			if (canTell) {
				answer++;
			}

		}

		System.out.println(answer);

	}

	static void union(int x, int y) {

		int px = find(x);
		int py = find(y);

		if (px != py) {
			p[py] = px;
		}

	}

	static int find(int x) {
		if (p[x] != x) {
			p[x] = find(p[x]);
		}
		return p[x];
	}
}