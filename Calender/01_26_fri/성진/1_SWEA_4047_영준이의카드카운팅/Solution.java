package _4047;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception {

//		System.setIn(new FileInputStream("sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		// nextInt() 다음에 nextLine()을 바로 사용할 경우 남은 개행문자 때문에 문제가 발생
		// 이를 방지하기 위해 nextLine()을 한 번 더 호출!
		sc.nextLine();
		
		
		
		for (int test_case = 1; test_case <= T; test_case++) {

			// 스페이드, 다이아, 하트, 클로버 각각 1번 ~ 13번 카드에 해당하는 카운트 배열 생성
			int[] s_count = new int[13];
			int[] d_count = new int[13];
			int[] h_count = new int[13];
			int[] c_count = new int[13];

			// 에러값 선언(디폴트는 "not error"라는 문자)
			// 겹치는 카드를 발견하면 "ERROR"로 바꾸고 이를 출력하고자 함.
			String error = "not error";

			// 한 줄의 input을 String으로 저장
			// 예시: test_case 1번의 경우, str은 "S01D02H03H04"이라는 문자열을 참조하고 있음.
			String str = sc.nextLine();

			
			
			// i는 0부터 (str의 길이-1)까지 3씩 증가(i = 0, 3, 6, ...)
			out:
			for (int i = 0; i < str.length(); i += 3) {

				// string을 부분으로 쪼개기 위한 substring() 메소드를 사용
				// substring(idx, idx): 부분문자열을 만들고자 하는 시작 인덱스, 끝 인덱스(포함 X)을 파라미터로 넣어줌.
				// 예시: test_case 1번의 경우, sub_str은 "S01", "D02", "H03", "H04"로 반복하며 3개씩 쪼갤
				String sub_str = str.substring(i, i + 3);

				// substring의 0번째 문자를 문자열로 바꿔주고, 이를 card라는 변수에 저장(card = 'S')
				// substring의 1번째 문자를 문자열로 바꿔주고, 이를 ten_num(십의 자릿수)라는 변수에 저장(ten_num = '0')
				// substring의 2번째 문자를 문자열로 바꿔주고, 이를 one_num(일의 자릿수)라는 변수에 저장(one_num = '1')
				char card = sub_str.charAt(i % 3);
				char ten_num = sub_str.charAt(i % 3 + 1);
				char one_num = sub_str.charAt(i % 3 + 2);

				// 문자가 'S'라면
				if (card == 'S') {

					// 십의 자릿수와 일의 자릿수를 모두 비교해서 01번 카드부터 13번 카드까지 비교
					// 일치한다면 s_count에 1을 더해준다.
					if (ten_num == '0') {

						if (one_num == '1') {
							s_count[1 - 1] += 1;
						} else if (one_num == '2') {
							s_count[2 - 1] += 1;
						} else if (one_num == '3') {
							s_count[3 - 1] += 1;
						} else if (one_num == '4') {
							s_count[4 - 1] += 1;
						} else if (one_num == '5') {
							s_count[5 - 1] += 1;
						} else if (one_num == '6') {
							s_count[6 - 1] += 1;
						} else if (one_num == '7') {
							s_count[7 - 1] += 1;
						} else if (one_num == '8') {
							s_count[8 - 1] += 1;
						} else if (one_num == '9') {
							s_count[9 - 1] += 1;
						}
					}

					else if (ten_num == '1') {

						if (one_num == '0') {
							s_count[10 - 1] += 1;
						} else if (one_num == '1') {
							s_count[11 - 1] += 1;
						} else if (one_num == '2') {
							s_count[12 - 1] += 1;
						} else if (one_num == '3') {
							s_count[13 - 1] += 1;
						}
					}

				}

				
				// s_count를 순회하며 1을 초과한다면 error 변수를 "ERROR"로 변경하고, for문 탈출(label: out)
				for (int k = 0; k < 13; k++) {
					if (s_count[k] > 1) {
						error = "ERROR";
						break out;
					}
				}

				
				// 이하 동문
				if (card == 'D') {

					if (ten_num == '0') {

						if (one_num == '1') {
							d_count[1 - 1] += 1;
						} else if (one_num == '2') {
							d_count[2 - 1] += 1;
						} else if (one_num == '3') {
							d_count[3 - 1] += 1;
						} else if (one_num == '4') {
							d_count[4 - 1] += 1;
						} else if (one_num == '5') {
							d_count[5 - 1] += 1;
						} else if (one_num == '6') {
							d_count[6 - 1] += 1;
						} else if (one_num == '7') {
							d_count[7 - 1] += 1;
						} else if (one_num == '8') {
							d_count[8 - 1] += 1;
						} else if (one_num == '9') {
							d_count[9 - 1] += 1;
						}
					}

					else if (ten_num == '1') {
						if (one_num == '0') {
							d_count[10 - 1] += 1;
						} else if (one_num == '1') {
							d_count[11 - 1] += 1;
						} else if (one_num == '2') {
							d_count[12 - 1] += 1;
						} else if (one_num == '3') {
							d_count[13 - 1] += 1;
						}
					}

				}

				for (int k = 0; k < 13; k++) {
					if (d_count[k] > 1) {
						error = "ERROR";
						break out;
					}
				}

				if (card == 'H') {

					if (ten_num == '0') {

						if (one_num == '1') {
							h_count[1 - 1] += 1;
						} else if (one_num == '2') {
							h_count[2 - 1] += 1;
						} else if (one_num == '3') {
							h_count[3 - 1] += 1;
						} else if (one_num == '4') {
							h_count[4 - 1] += 1;
						} else if (one_num == '5') {
							h_count[5 - 1] += 1;
						} else if (one_num == '6') {
							h_count[6 - 1] += 1;
						} else if (one_num == '7') {
							h_count[7 - 1] += 1;
						} else if (one_num == '8') {
							h_count[8 - 1] += 1;
						} else if (one_num == '9') {
							h_count[9 - 1] += 1;
						}

					}

					else if (ten_num == '1') {
						if (one_num == '0') {
							h_count[10 - 1] += 1;
						} else if (one_num == '1') {
							h_count[11 - 1] += 1;
						} else if (one_num == '2') {
							h_count[12 - 1] += 1;
						} else if (one_num == '3') {
							h_count[13 - 1] += 1;
						}
					}

				}

				for (int k = 0; k < 13; k++) {
					if (h_count[k] > 1) {
						error = "ERROR";
						break out;
					}
				}

				if (card == 'C') {

					if (ten_num == '0') {

						if (one_num == '1') {
							c_count[1 - 1] += 1;
						} else if (one_num == '2') {
							c_count[2 - 1] += 1;
						} else if (one_num == '3') {
							c_count[3 - 1] += 1;
						} else if (one_num == '4') {
							c_count[4 - 1] += 1;
						} else if (one_num == '5') {
							c_count[5 - 1] += 1;
						} else if (one_num == '6') {
							c_count[6 - 1] += 1;
						} else if (one_num == '7') {
							c_count[7 - 1] += 1;
						} else if (one_num == '8') {
							c_count[8 - 1] += 1;
						} else if (one_num == '9') {
							c_count[9 - 1] += 1;
						}
					}

					else if (ten_num == '1') {
						if (one_num == '0') {
							c_count[10 - 1] += 1;
						} else if (one_num == '1') {
							c_count[11 - 1] += 1;
						} else if (one_num == '2') {
							c_count[12 - 1] += 1;
						} else if (one_num == '3') {
							c_count[13 - 1] += 1;
						}
					}

				}

				for (int k = 0; k < 13; k++) {
					if (c_count[k] > 1) {
						error = "ERROR";
						break out;
					}
				}

			}

			
			
			// for문 탈출 후 error값 비교했을 때 "ERROR"라면 ERROR 출력
			if (error.equals("ERROR")) {
				System.out.println("#" + test_case + " " + error);
			}

			// ERROR가 아니라면 각 모양별 카드 장수를 계산해서 출력
			else {

				int s = 13;
				int d = 13;
				int h = 13;
				int c = 13;

				for (int idx = 0; idx < 13; idx++) {

					s -= s_count[idx];
					d -= d_count[idx];
					h -= h_count[idx];
					c -= c_count[idx];

				}
				System.out.println("#" + test_case + " " + s + " " + d + " " + h + " " + c);
			}

		}
	}

}