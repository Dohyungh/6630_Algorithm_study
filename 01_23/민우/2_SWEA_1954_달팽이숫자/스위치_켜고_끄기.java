package Study_for_2024_01_23.problem2;

import java.util.Scanner;

public class 스위치_켜고_끄기 {    // 백준은 Main으로 이름 바꿔서 내야 함
    public static void main(String[] args) {

        /* 1시간 59분 걸림
        * 코드길이 : 2551 B
        * 시간 : 240 ms
        * 메모리 : 17980 KB
        * */

        Scanner sc = new Scanner(System.in);

        // 1. 스위치의 개수와 상태를 입력 받아보자
        int N = sc.nextInt();                  // N : 스위치 개수
        boolean[] swith = new boolean[N+1];    // swith : 스위치 상태 배열 / 인덱스와 스위치 숫자 맞추려구 크기를 1 크게 설정

        for(int i=1 ; i<=N ; i++){
            swith[i] = sc.nextInt() != 0;      // false(off)로 초기화 된 배열에 1이면 true(on)를 넣어주자 -> 인텔리제이 추천 기능 미쳤다
        }



        // 2. 학생 입력을 받으면 스위치의 상태를 바꿔보자
        int total = sc.nextInt();         // total : 학생 수
        for(int n=0 ; n<total ; n++){     // 학생 수만큼 츄라이
            int gender = sc.nextInt();    // gender : 성별
            int pos = sc.nextInt();       // pos : 학생 위치

            if(gender == 1){    // 남자일 경우 %를 통해 배수인지 확인
                for(int j=1 ; j<=N ; j++){
                    if(j%pos == 0)
                        swith[j] = !swith[j];    // !로 boolean 바꿔줌
                }
            }
            else{    // 여자일 경우
                swith[pos] = !swith[pos];
                int newLpos = pos - 1;    // 여자일 경우 왼쪽 확인
                int newRpos = pos + 1;    // 여자일 경우 오른쪽 확인
                while(newLpos>=1 && newRpos<=N){             // 븅신같이 ||로 해서 런타임에러 계속 뜸
                    if(swith[newLpos] == swith[newRpos]){    // 대칭 숫자가 같으면 계속 이어감
                        swith[newLpos] = !swith[newLpos];
                        swith[newRpos] = !swith[newRpos];
                        newLpos--;
                        newRpos++;
                    }
                    else
                        break;    // 대칭 숫자가 다르면 반복문 탈출
                }
            }
        }



        // 3. 정답을 출력해보자
        for(int i=1 ; i<=N ; i++){
            if(swith[i])
                System.out.print("1 ");    // boolean을 숫자로 바꿔주자
            else
                System.out.print("0 ");    // boolean을 숫자로 바꿔주자
            if(i % 20 == 0) {              // 20마다 다음 줄 출력이라서 %로 처리해주자
                System.out.println();      // 배열을 1부터 시작하게 하면 0일때 변수 차단해서 여기도 개꿀임
            }
        }

    }
}
