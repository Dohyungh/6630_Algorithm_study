package SWEA._2007_패턴마디의길이;

import java.util.Scanner;

//Hashmap을 이용할 수 없다! 왜??

//substring 이용해서 한번 해보세요

//전체 문자열의 길이의 약수(정수론)를 쓸수		가 없네 끝에 짜투리를 넣어놔서

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc<=T; tc++) {
			String str = sc.next();
			char[] arr = str.toCharArray(); // {K,O,R,E,A,K,O}
			int len = arr.length; // 길이 저장해놓고, 오버하면 break 시켜줄 거임
			
			int ans = 1;
			boolean flag= false; // 찾았다 싶으면 true 로 바꿔줄 거임
			while (!flag) {
				String temp_str = ""; // 후보 단어 선정
				for (int i = 0; i<ans;i++) {
//					temp_str += (String)arr[i]; 에러나네
//					temp_str += arr[i].toString(); 에러나네
//					temp_str += Char.toString() 없네
//					temp_str += Character.toString(arr[i]); //된다!
					temp_str += arr[i];	//이게 되네
				}
				
				int j=ans;			
				while(true) {
					if (j +ans>=len) {
						flag = !flag;
						break;
					}
					String target_str ="";
					for (int k=j;k<j+ans;k++) { // 여기서 j+ans >len 문제가 생김 ex. ...SAMSUNGSA 
												//근데 사실 이렇게 마지막에 있는 친구는 그냥 버려도 됨. 여기까지 왔다는 건 그전에는 안들어갔다는 거거덩
												//정답이 항상 있다고 문제에서 가정하니까.
												//만약 반복되는 문자열이 없을 경우 0을 반환하라 이런게 있었으면 좀 더 귀찮아 졌을 듯.
						target_str += arr[k];
					}
					if (target_str.equals(temp_str)) {
						j+=ans;
					} else {
						ans++;
						break;
					}
				}
			}
			System.out.printf("#%d %d%n",tc,ans);
		}
	}

}
