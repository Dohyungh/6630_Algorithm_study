# 가운데를 말해요 - 1655 

[문제 링크](https://www.acmicpc.net/problem/1655) 

### 성능 요약

메모리: 35192 KB, 시간: 420 ms

### 분류

자료 구조, 우선순위 큐

### 제출 일자

2025년 2월 25일 10:49:26

### 문제 설명

<p>백준이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다. 백준이가 정수를 하나씩 외칠때마다 동생은 지금까지 백준이가 말한 수 중에서 중간값을 말해야 한다. 만약, 그동안 백준이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.</p>

<p>예를 들어 백준이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면, 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다. 백준이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에는 백준이가 외치는 정수의 개수 N이 주어진다. N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다. 그 다음 N줄에 걸쳐서 백준이가 외치는 정수가 차례대로 주어진다. 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.</p>

### 출력 

 <p>한 줄에 하나씩 N줄에 걸쳐 백준이의 동생이 말해야 하는 수를 순서대로 출력한다.</p>

### 느낀점

- 알고리즘 분류를 먼저 봐버려서 비교적 쉽게 풀렸다. 이전에 풀이를 들은 적이 있는 것 같았다.
- 요즘 코테를 보다보면 dp또는 구현문제가 많은데, 쉬워보이는 문제도 IDE없이 갑자기 구현하려고 하면 어떤 자료구조를 사용하는게 가장 적절할지 헷갈린다. 간단하다고 생각했던 자료구조를 얼마나 적절하게 응용할 수 있느냐가 중요하게 느껴졌다.

### 설계 : 20분 (알고리즘 분류 참고)

- 두개의 우선순위 큐를 준비한다.
    - 하나에는 지금까지 입력된 값중 중간 값보다 작은 수들(small), 나머지 하나에는 큰 수들(big)을 넣을 것이다.
    - 작은 수를 넣을 큐는 내림차순으로 정렬하도록 한다.
- 두 큐의 크기 차이를 0 또는 1로 유지하면서, 작은 수들이 내림차순으로 정렬된 큐에서 peek() 한 수를 중간값으로 둔다.
- small.peek()은 NullPoint이면 안된다. 문제에서 백준이는 최소 1개의 숫자를 외치기 때문에 가장 처음 small 큐에 수를 하나 넣어 초기화 한다.
- 그 다음부터 입력되는 수가 있다면 small.peek()과 비교해서 작거나 같다면 small에, 크다면 big에 추가한다.
- small과 big의 사이즈를 비교해서 두 큐의 사이즈 차이를 유지시킨 후 small.peek() 값을 출력한다.