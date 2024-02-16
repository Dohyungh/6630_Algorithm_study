package BOJ._2941_크로아티아알파벳;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] list = {"c=","c-","dz=","d-","lj","nj","s=","z="}; // dz= 랑 z= 가 문제
		
		char[] str = sc.next().toCharArray();
		
		
		int answer = str.length; //거꾸로 2개 짜리나 3개짜리 나오면 빼주는 식으로.
		for (int i = 0 ; i < str.length-1; i++) {
			if (i<str.length-2 && str[i] == 'd') { // 이걸 먼저 조사해야함 // indexoutofbounds 주의
				if ((""+str[i]+str[i+1]+str[i+2]).equals("dz=")) { // 여긴 그냥 익숙한 붙이기
					answer-=2;
					i++;
					continue;
				}
			}
			
			StringBuilder strBd = new StringBuilder(); //나도 한번 써보자
			strBd.append(str[i]);
			strBd.append(str[i+1]);
			for (int j = 0; j<list.length; j++) {
				if (strBd.toString().equals(list[j])) answer--; // toString()을 써줘야 String이 됨
			}
		}
		
		System.out.println(answer);
		
		sc.close();
	}

}
