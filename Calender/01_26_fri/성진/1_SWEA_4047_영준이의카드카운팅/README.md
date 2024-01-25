# SWEA_4047.영준이의 카드 카운팅 D3

출처: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIsY84KEPMDFAWN

---

## 문제

시간 : 10개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초  
메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내

**※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.**


최근 영준이는 카드 게임에 꽂혀 있다.

영준이가 하는 카드 게임에는 한 덱의 카드가 필요한데 여기서 얘기하는 “한 덱”이란 스페이드, 다이아몬드, 하트, 클로버 무늬 별로 각각 A, 2~10, J, Q, K의 라벨 즉 4개의 무늬 별로

각각 13장씩 총 52장의 카드가 있는 모음을 의미한다.

편의상 A는 1, J, Q, K는 11, 12, 13으로 하여 1~13의 숫자가 카드에 적혀있다고 하자.

영준이는 몇 장의 카드를 이미 가지고 있는데 게임을 하기 위해서 몇 장의 카드가 더 필요한지 알고 싶어 한다.

그리고 이미 겹치는 카드를 가지고 있다면 오류를 출력하고자 한다.

지금 가지고 있는 카드의 정보가 주어지면 이 작업을 수행하는 프로그램을 작성하라.


**[입력]**

맨 위 줄에 테스트케이스의 개수가 주어진다.

각 테스트케이스 별로 순서대로 첫 번째 줄에 지금 영준이가 가지고 있는 카드에 대한 정보 S (1 ≤ |S| ≤ 1000)가 주어진다.

S는 각각 3자리로 표현되는 카드들의 정보를 붙여서 만든 하나의 문자열인데 각 카드는 TXY 꼴로 표현되며,

T는 카드의 무늬(S, D, H, C)이며 XY는 카드의 숫자 (01 ~ 13)이다.

**[출력]**

각 테스트케이스 별로 순서대로 한 줄씩 답을 출력하는데, 문자열 S를 보고 지금 무늬 별로(S, D, H, C 순서로) 몇 장의 카드가 부족한지 출력하여라.

이미 겹치는 카드가 있다면 문자열 “ERROR” (쌍따옴표는 출력하지 않는다)를 출력한다
 
---

## 입출력 예시

입력

3               // Test Case 수  
S01D02H03H04    // Test Case 1  
H02H10S11H02  
S10D10H10C01     	


 
출력

#1 12 12 11 13  	// Test Case 1의 정답   
#2 ERROR            // Test Case 2의 정답  
#3 12 12 12 12      // Test Case 3의 정답

---

## 느낀 점

- 배열이 아닌 문자열을 다루는 것이 어려웠다.
- 특히, 문자와 숫자가 섞인 문자열의 원소들을 각각 형변환하고 싶었으나 실패했고, 복잡한 제어문으로 문제를 풀게 됐다.
- 관련 문법, 메소드 공부가 필요하다.



---

## 생각해보면 좋을 코드


```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
    
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int times = Integer.parseInt(br.readLine());
        HashSet<Integer> s = new HashSet<>();
        HashSet<Integer> d = new HashSet<>();
        HashSet<Integer> h = new HashSet<>();
        HashSet<Integer> c = new HashSet<>();
            
        for(int i=0; i<times; i++) {
            String input = br.readLine();
             
            boolean flag = true;
            for(int j=0; j<input.length(); j+=3) {
                char card = input.charAt(j);
                int cardNum = Integer.parseInt(input.substring(j+1, j+3));
                 
                if(card == 'S') flag = s.add(cardNum);
                else if(card == 'D') flag = d.add(cardNum);
                else if(card == 'H') flag = h.add(cardNum);
                else flag = c.add(cardNum);
 
                if(!flag) break;
            }
             
            if(!flag) System.out.println("#"+(i+1)+" ERROR");
            else System.out.println("#"+(i+1)+" "+(13 - s.size())+" "
                                                 +(13 - d.size())+" "
                                                 +(13 - h.size())+" "
                                                 +(13 - c.size()));
            s.clear(); d.clear(); h.clear(); c.clear();
        }
    }
}
```

1. 자료구조
- 카드 숫자를 저장하고, 중복을 확인하기 위한 자료구조로 HashSet을 선택했다.

2. 문자열을 Integer로 변환
- `int cardNum = Integer.parseInt(input.substring(j+1, j+3));`로 substring으로 쪼갠 문자열을 integer로 바꿔 저장했다.

3. flag를 통한 break 조건
- HashSet.add() 메소드는 원소가 set에 성공적으로 추가됐을 때 'true'를 반환하고, 이미 원소가 존재할 경우 'false'를 반환한다.
- 이를 통해 중복된 카드가 입력되었는지 확인하고, false라면 ERROR를 출력하게끔 했다.