package BOJ._17413_단어뒤집기2;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		// 변수 이름 짓기는 참 어려워
		boolean inthegwalho = false;
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		
		int idx = 0;
		while (idx < str.length()) {
			//<를 만나면 inthegwalho를 참으로
			if (str.charAt(idx) == '<') {
				sb.append(str.charAt(idx));
				inthegwalho = true;
				idx++;
				continue;
			}
			
			//>를 만나면 inthegwalho를 거짓으로.
			if (str.charAt(idx) == '>') {
				sb.append(str.charAt(idx));
				inthegwalho = false;
				idx++;
				continue;
			}
			
			// 괄호 안일 때는 그냥 같다 붙여라.
			if (inthegwalho) {
				sb.append(str.charAt(idx));
				idx++;
				continue;
			}
			
			// 괄호 밖에서는 띄어쓰기는 제외하고 stack 에 넣은 다음, 공백을 만나면(혹은 여는 괄호를 만나면) pop 해줄 것임. 
			if (!inthegwalho) {
				if(str.charAt(idx) != ' ') {
					stack.add(str.charAt(idx));
				}
				
			}
			
			//이 밑은 사실 그냥 출력 보면서 디버깅한 결과물이라 할 말이 별로 없음.
			// 지금 공백이나 다음에 < 를 만나면 stack 에서 pop. index에러 안나게 주의
			if(!inthegwalho && (str.charAt(idx)==' ' || idx <str.length()-1 && str.charAt(idx+1)=='<')) {
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				// 괄호를 만났거나, 마지막 단어가 아니라면, 띄어쓰기를 해줘야함.
				// 마지막 단어 다음에도 띄어쓰기를 하면 안됨에 주의!!
				if(idx != str.length()-1 && str.charAt(idx+1)!='<') {
					sb.append(' ');
				}
			}
			//무조건 한칸씩
			idx++;
		}
		
		// 마지막에 남은 stack은 띄어쓰기를 만나지도, < 를 만나지도 않고 끝나기 때문에, 비어있는게 아니라면. 붙여줘야함.
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
		
		sc.close();
	}

}
