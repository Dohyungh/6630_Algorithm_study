package Study_for_2024_01_26.problem2;

import java.util.Scanner;

public class 패턴_마디의_길이 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();       // 테스트 케이스 입력 받음
        int T = Integer.parseInt(input);    // int형으로 변환해줌

        for(int tc=0 ; tc<T ; tc++){
            String str = sc.nextLine();     // 문자열 입력 받음

            for(int len=1 ; len<=10 ; len++){                    // len : 슬라이싱한 문자열의 길이
                String repeat = str.substring(0, len);           // 길이별로 잘라서 뒷부분과 일치하는지 비교
                if(repeat.equals(str.substring(len, len*2))){    // 일치하면 출력하고 반복 종료
                    System.out.printf("#%d %d\n", tc+1, repeat.length());
                    break;
                }
            }

        }
    }
}
