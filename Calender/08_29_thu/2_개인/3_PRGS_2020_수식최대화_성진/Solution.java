package prgs_수식최대화;

import java.util.*;

class Solution {

	// 3개의 연산자의 우선순위를 적용한 2차원 배열 생성
	static char[][] perm = { { '*', '+', '-' }, { '*', '-', '+' }, { '+', '*', '-' }, { '+', '-', '*' },
			{ '-', '*', '+' }, { '-', '+', '*' } };

	public long solution(String expression) {
		long answer = 0;

		String[] strs = expression.split("\\*|\\+|\\-");

		// 숫자를 저장할 리스트 numbers
		List<Long> numbers = new ArrayList<>();

		// 추출한 숫자들을 Long 타입으로 변환하여 리스트에 저장
		for (int i = 0; i < strs.length; i++) {
			long num = Long.parseLong(strs[i]);
			numbers.add(num);
		}

		// 연산자를 저장할 리스트 operators
		List<Character> operators = new ArrayList<>();

		// 수식에서 연산자만 추출하여 리스트에 저장
		for (int i = 0; i < expression.length(); i++) {
			char e = expression.charAt(i);
			if (e == '*' || e == '+' || e == '-') {
				operators.add(e);
			}
		}

		// 각 연산자 우선순위 경우의 수에 대해 계산
		for (int i = 0; i < perm.length; i++) {
			// 숫자와 연산자를 임시로 저장할 임시 리스트(tempNum, tempOper)를 생성
			List<Long> tempNum = new ArrayList<>();
			for (long num : numbers) {
				tempNum.add(num);
			}
			List<Character> tempOper = new ArrayList<>();
			for (char oper : operators) {
				tempOper.add(oper);
			}

			
			// 현재 우선순위(perm[i])에 따라 연산 수행
			for (int j = 0; j < 3; j++) {
				// 현재 우선순위의 연산자(perm[i][j])
				char oper = perm[i][j];

				int idx = 0;
				
				// 현재 우선순위 연산자가 존재하는 동안 반복
				// 다 쓸 때까지 반복이 진행되어야하므로
				while (tempOper.contains(oper)) {
					char o = tempOper.get(idx);
					if (o == oper) {
						// 연산자의 왼쪽에 해당하는 숫자
						long left = tempNum.get(idx);
						// 연산자의 오른쪽에 해당하는 숫자
						long right = tempNum.get(idx + 1);
						long result;
						
						// 연산 기호에 맞춰 연산을 수행
						if (oper == '*') {
							result = left * right;
						} else if (oper == '+') {
							result = left + right;
						} else {
							result = left - right;
						}
						
						// 결과를 현재 인덱스(idx)에 저장하고, 오른쪽 숫자 제거, 사용된 연산자 제거 
						tempNum.set(idx, result);
						tempNum.remove(idx + 1);
						tempOper.remove(idx);
						
					} else {
						idx++;
					}
				}
			}

			// 현재 우선순위에 따른 최종 연산 결과(절댓값) 저장
			long temp = Math.abs(tempNum.get(0));

			// 가장 큰 값으로 max값 갱신
			answer = Math.max(temp, answer);

		}

		return answer;
	}

}