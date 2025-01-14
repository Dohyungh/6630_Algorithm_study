# 사이클 게임 - 20040 

[문제 링크](https://www.acmicpc.net/problem/20040) 

### 성능 요약

메모리: 132504 KB, 시간: 612 ms

### 분류

자료 구조, 분리 집합

### 제출 일자

2025년 1월 8일 22:41:31

### 문제 설명

<p>사이클 게임은 두 명의 플레이어가 차례대로 돌아가며 진행하는 게임으로, 선 플레이어가 홀수 번째 차례를, 후 플레이어가 짝수 번째 차례를 진행한다. 게임 시작 시 0 부터 <em>n</em> − 1 까지 고유한 번호가 부여된 평면 상의 점 <em>n</em> 개가 주어지며, 이 중 어느 세 점도 일직선 위에 놓이지 않는다. 매 차례 마다 플레이어는 두 점을 선택해서 이를 연결하는 선분을 긋는데, 이전에 그린 선분을 다시 그을 수는 없지만 이미 그린 다른 선분과 교차하는 것은 가능하다. 게임을 진행하다가 처음으로 사이클을 완성하는 순간 게임이 종료된다. 사이클 <em>C</em>는 플레이어가 그린 선분들의 부분집합으로, 다음 조건을 만족한다.</p>

<blockquote>
<p><em>C</em>에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 출발점으로 되돌아올 수 있다.</p>
</blockquote>

<p>문제는 선분을 여러 개 그리다 보면 사이클이 완성 되었는지의 여부를 판단하기 어려워 이미 사이클이 완성되었음에도 불구하고 게임을 계속 진행하게 될 수 있다는 것이다. 이 문제를 해결하기 위해서 게임의 진행 상황이 주어지면 몇 번째 차례에서 사이클이 완성되었는지, 혹은 아직 게임이 진행 중인지를 판단하는 프로그램을 작성하려 한다.</p>

<p>입력으로 점의 개수 <em>n</em>과 <em>m</em> 번째 차례까지의 게임 진행 상황이 주어지면 사이클이 완성 되었는지를 판단하고, 완성되었다면 몇 번째 차례에서 처음으로 사이클이 완성된 것인지를 출력하는 프로그램을 작성하시오.</p>

### 입력 

 <p>입력은 표준입력을 사용한다. 입력의 첫 번째 줄에는 점의 개수를 나타내는 정수 3 ≤ <em>n</em> ≤ 500,000 과 진행된 차례의 수를 나타내는 정수 3 ≤ <em>m</em> ≤ 1,000,000 이 주어진다. 게임에서 사용하는 <em>n</em>개의 점에는 0 부터 <em>n</em> − 1 까지 고유한 번호가 부여되어 있으며, 이 중 어느 세 점도 일직선 위에 놓이지 않는다. 이어지는 <em>m</em> 개의 입력 줄에는 각각 <em>i</em>번째 차례에 해당 플레이어가 선택한 두 점의 번호가 주어진다 (1 ≤ <em>i</em> ≤ <em>m</em>).</p>

### 출력 

 <p>출력은 표준출력을 사용한다. 입력으로 주어진 케이스에 대해, <em>m</em> 번째 차례까지 게임을 진행한 상황에서 이미 게임이 종료되었다면 사이클이 처음으로 만들어진 차례의 번호를 양의 정수로 출력하고, <em>m</em> 번의 차례를 모두 처리한 이후에도 종료되지 않았다면 0을 출력한다.</p>

### 느낀점

- 다 풀고 보니 분리집합 자료구조 문제라고 써있었는데, 서로소 집합이랑 같은 말이라고 한다. 이렇게 푸는 방식이 맞는 것 같다.
- 문제 글이 장황해서 어려운 듯 했지만 비교적 간단한 문제였다.

### 설계 : 5분

- 선분을 하나 연결할때마다 각 집합의 루트를 찾아 다르면 연결하고, 같으면 answer에 몇번째 시도인지 저장하고 끝내도록 했다.
- m번만큼 반복해도 매번 다른 집합이었다면 아직 사이클이 완성되지 않음으로 초기화 수인 0이 출력된다.
- findSet 메서드와 unionSet 메서드를 작성하여 활용했다.