package _4789;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

//		System.setIn(new FileInputStream("sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		// nextInt() 다음에 nextLine()을 바로 사용할 경우 남은 개행문자 때문에 문제가 발생
        // 이를 방지하기 위해 nextLine()을 한 번 더 호출!
        sc.nextLine();
        
		for (int test_case = 1; test_case <= T; test_case++) {

			

			// 문자열 입력 받기
            String temp = sc.nextLine();
            

            // 문자열을 숫자 배열로 변환하는 코드
            int[] arr = new int[temp.length()];
            for (int i = 0; i < temp.length(); i++) {
            	// Character.getNumericValue(char ch) 메소드: 숫자 형태의 char형을 int형으로 변환
            	// charAt(int idx): 문자열에서 특정 인덱스에 위치하는 char를 반환
                arr[i] = Character.getNumericValue(temp.charAt(i));
            }

            // sum: 시점별 기립 박수를 치고 있는 사람의 수
            int sum = 0;
            // answer: 동욱이가 고용해야 하는 사람의 수
            int answer = 0;
            
            
            for (int i=0; i<arr.length; i++) {
            	
            	// i 시점에 기립 박수를 위한 조건을 만족하지 못했다면
            	if (sum < i) {
            		// i-sum 만큼 사람을 고용한다.
            		answer += i - sum;
            		sum = i + arr[i];
            	}
            	else {
            		sum += arr[i];
            	}
            }
            
            System.out.println("#" + test_case + " " + answer);
            
            
        }
		
    }
}