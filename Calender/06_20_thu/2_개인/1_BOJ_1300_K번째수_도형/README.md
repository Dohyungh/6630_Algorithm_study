# BOJ_1300.K번째수

출처: https://www.acmicpc.net/problem/1300

---
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	128 MB	44473	16465	12062	37.812%

## 문제

세준이는 크기가 N×N인 배열 A를 만들었다. 배열에 들어있는 수 A[i][j] = i×j 이다. 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다. B를 오름차순 정렬했을 때, B[k]를 구해보자.

배열 A와 B의 인덱스는 1부터 시작한다.

## 입력  
첫째 줄에 배열의 크기 N이 주어진다. N은 105보다 작거나 같은 자연수이다. 둘째 줄에 k가 주어진다. k는 min(109, N2)보다 작거나 같은 자연수이다.

## 출력

B[k]를 출력한다.


예제 입력 1   
```
3
7
```

예제 출력 1   
```
6
```
