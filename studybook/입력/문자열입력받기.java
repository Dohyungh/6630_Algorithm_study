package test.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문자열입력받기 {

	public static void main(String[] args) {
		
		
		// 문자열 -> 문자열(1글자)[]
		String str = "abc";
		String[] strarr = str.split(""); // [a,b,c]
		
		// 문자열 -> 문자[]
		char[] chrarr = str.toCharArray(); // [a,b,c]
		
		//문자열에서 char 하나(문자 혹은 숫자) 뽑아오기
		char b = str.charAt(1);
		
		String nu = "123";
		char c = nu.charAt(0);
		int intc = Character.getNumericValue(c); // 이거 모르면 아스키코드 이용해 변환

		
		//문자열에서 특정 인덱스 구간만 뽑아오기
		String ab = str.substring(1,3);
		
		//문자열에서 숫자로
		String numstr = "123";
		int num = Integer.parseInt(numstr);
		int num2 = Integer.valueOf(numstr);

		
		//문자열에서 숫자[]로 (split + for(i){Integer.parseInt})
		String numstr2 = "123 4 5 678";
		
		String[] numstrs2 = numstr2.split(" ");
		
		int[] inumstrs2 = new int[numstrs2.length];
		
		for (int i=0; i<inumstrs2.length;i++) {
		 inumstrs2[i]=Integer.parseInt(numstrs2[i]);
		}

		 //StringTokenizer : 문자열을 사용자 임의 기준으로 분리
		StringTokenizer st = new StringTokenizer("S01D02H03C04", "SDHC", true);
		System.out.println(st.nextToken());

		
		StringTokenizer st2 = new StringTokenizer("S01D02H03C04", "SDHC", false);
		System.out.println(st2.nextToken());

		//Arrays.asList 와 List.of
		List<String> strlist = Arrays.asList(strarr);
		
		List<String> strlist2 = List.of(strarr);
		
		List<String> strlist3 = Arrays.asList("a","b","c");
		
		Queue<String> strlist4 = new LinkedList<>(Arrays.asList(strarr));
		
	}
}
