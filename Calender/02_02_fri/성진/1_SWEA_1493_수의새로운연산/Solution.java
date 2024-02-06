package _1493;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

//		System.setIn(new FileInputStream("src/_1493/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			// p와 q를 입력받기.
			int p = sc.nextInt();
			int q = sc.nextInt();

			// 아래 정의한 returnLocation() 메소드에 점 p, q를 각각 인자값으로 주기
			// 각 점의 좌표 (x, y)를 길이가 2인 array에 x, y 순서대로 담아서 return (문제에서 정의한 &(p), &(q)를 수행)
			int[] pArr = returnLocation(p);
			int[] qArr = returnLocation(q);

			// 문제에서 정의한대로 점 p와 q를 좌표끼리의 덧셈 연산을 수행
			// p의 x좌표: pArr[0], q의 x좌표: qArr[0]이므로 덧셈연산에 의해 새로운 점의 x좌표는 pArr[0]+qArr[0]이다.(y좌표도 방법 동일)
			int[] sumArr = {pArr[0]+qArr[0], pArr[1]+qArr[1]};

			// 아래 정의한 returnPointNumber() 메소드에 새로운 점의 좌표 array를 인자값으로 주기
			// 해당 좌표에 찍힌 점의 수를 return (문제에서 정의한 #(x,y)연산을 수행)
			int answer = returnPointNumber(sumArr);

			System.out.printf("#%d %d\n", test_case, answer);

		}

	}

	
	// 문제 속 &(p)를 수행하는 메소드 returnLocation()
	public static int[] returnLocation(int p) {

		// (x, y) 좌표를 담을 arr 선언
		int[] arr = new int[2];

		// 인자값으로 받은 점 p가 몇번째 대각선 줄에 있는지를 나타낼 line_num 선언 및 초기화
		int line_num = 0;
		// 대각선 줄에서 몇번째 인덱스에 위치하는지 정확한 좌표를 나타내기 이동해야할 값을 저장하는 delta_idx 선언 및 초기화
		int delta_idx = 0;

		// 방법 1. for문(10000번째의 점이 141번째 줄에 등장한다는 것을 알고 있다면 n은 141까지 증가 가능)
//		for (int n=1; n<142; n++) {
//			if (p==1 || p < ((n+1)*(n+2))/2-n) {
//				line_num = n;
//				delta_idx = p - ((n*(n+1))/2-(n-1));
//				break;
//			}
//		}

		// 방법 2. 몇번째 줄까지 증가하는지 계산해보지 않더라도 while문으로 n을 증가하며 line_num을 찾을 수 있음
		// 다음 대각선으로 넘어갈 때마다 대각선에 들어오는 원소의 개수는 1씩 증가한다.
		// N번째 대각선이라면 1+2+3+...+N=(N*(N+1))/2개의 원소가 대각선에 존재할 것임.
		// 대각선을 증가시키면서(n++) 인자값으로 받은 p가 몇번째 대각선에 위치하는지 찾는다.
		int n = 1;
		while (p >= n*(n+1)/2 - (n-1)) {
			n++;
		}
		// 점 p는 n번째 바로 직전 대각선에 위치함으로 n-1번째 대각선으로 line_num 갱신
		line_num = n - 1;
		// 대각선 내에서 x,y 좌표를 몇 칸 이동해야하는지 알려주는 delta_idx를 계산
		delta_idx = p - ((line_num * (line_num + 1)) / 2 - (line_num - 1));

		// 같은 대각선 내에서 (x,y)의 x+y=(대각선 줄 수)와 같고, 왼쪽부터 x는 1증가, y는 1감소하는 형태임
		// delta_idx만큼 x를 증가, y를 감소시켜 p의 (x,y)좌표를 계산해 반환한다.
		arr[0] = 1 + delta_idx;
		arr[1] = line_num - delta_idx;

		return arr;
	}

	
	// 문제 속 #(x, y)를 수행하는 메소드 returnPointNumber()
	public static int returnPointNumber(int[] arr) {
		// 반환하고자 하는 pointNumber 선언 및 초기화
		int pointNumber = 0;

		// x, y좌표를 각각 저장
		int x = arr[0];
		int y = arr[1];

		// x, y와 관련된 음함수 식으로 도출
		pointNumber = x*(x+1)/2 + x*(y-1) + (y-1)*(y-2)/2;

		return pointNumber;
	}

}