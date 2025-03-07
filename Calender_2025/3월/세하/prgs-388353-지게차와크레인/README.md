## 📖 [지게차와 크레인](https://school.programmers.co.kr/learn/courses/30/lessons/388353)
#### 📍 문제
##### 문제 설명
A 회사의 물류창고에는 알파벳 대문자로 종류를 구분하는 컨테이너가 세로로 <code>n</code> 줄, 가로로 <code>m</code>줄 총 <code>n</code> x <code>m</code>개 놓여 있습니다. 특정 종류 컨테이너의 출고 요청이 들어올 때마다 지게차로 창고에서 접근이 가능한 해당 종류의 컨테이너를 모두 꺼냅니다. 접근이 가능한 컨테이너란 4면 중 적어도 1면이 창고 외부와 연결된 컨테이너를 말합니다.

최근 이 물류 창고에서 창고 외부와 연결되지 않은 컨테이너도 꺼낼 수 있도록 크레인을 도입했습니다. 크레인을 사용하면 요청된 종류의 모든 컨테이너를 꺼냅니다.

<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/0e90cfc3-ddd7-4841-9ed8-c420cb6be7a5/%E1%84%86%E1%85%AE%E1%86%AF%E1%84%85%E1%85%B2%E1%84%8E%E1%85%A1%E1%86%BC%E1%84%80%E1%85%A9-1-1.drawio.png" title="" alt="물류창고-1-1.drawio.png"></p>

위 그림처럼 세로로 4줄, 가로로 5줄이 놓인 창고를 예로 들어보겠습니다. 이때 "A", "BB", "A" 순서대로 해당 종류의 컨테이너 출고 요청이 들어왔다고 가정하겠습니다. “A”처럼 알파벳 하나로만 출고 요청이 들어올 경우 지게차를 사용해 출고 요청이 들어온 순간 접근 가능한 컨테이너를 꺼냅니다. "BB"처럼 같은 알파벳이 두 번 반복된 경우는 크레인을 사용해 요청된 종류의 모든 컨테이너를 꺼냅니다.

<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/e5fac969-705a-41cf-8609-ad41e30ea694/%E1%84%86%E1%85%AE%E1%86%AF%E1%84%85%E1%85%B2%E1%84%8E%E1%85%A1%E1%86%BC%E1%84%80%E1%85%A9-1-2.drawio.png" title="" alt="물류창고-1-2.drawio.png" width="400"></p>

위 그림처럼 컨테이너가 꺼내져 3번의 출고 요청 이후 남은 컨테이너는 11개입니다. 두 번째 요청은 크레인을 활용해 모든 <code>B</code> 컨테이너를 꺼냈음을 유의해 주세요. 세 번째 요청이 들어왔을 때 2행 2열의 <code>A</code> 컨테이너만 접근이 가능하고 2행 3열의 <code>A</code> 컨테이너는 접근이 불가능했음을 유의해 주세요.

처음 물류창고에 놓인 컨테이너의 정보를 담은 1차원 문자열 배열 <code>storage</code>와 출고할 컨테이너의 종류와 출고방법을 요청 순서대로 담은 1차원 문자열 배열 <code>requests</code>가 매개변수로 주어집니다. 이때 모든 요청을 순서대로 완료한 후 남은 컨테이너의 수를 return 하도록 solution 함수를 완성해 주세요.


##### 테스트 케이스 구성 안내

아래는 테스트 케이스 구성을 나타냅니다. 각 그룹 내의 테스트 케이스를 모두 통과하면 해당 그룹에 할당된 점수를 획득할 수 있습니다.

<table class="table">
        <thead><tr>
<th>그룹</th>
<th>총점</th>
<th>추가 제한 사항</th>
</tr>
</thead>
        <tbody><tr>
<td>#1</td>
<td>10%</td>
<td><code>requests</code>에 크레인을 사용한 출고 요청만 존재합니다.</td>
</tr>
<tr>
<td>#2</td>
<td>15%</td>
<td><code>requests</code>에 지게차를 사용한 출고 요청만 존재합니다.</td>
</tr>
<tr>
<td>#3</td>
<td>25%</td>
<td><code>requests</code>에 컨테이너의 종류가 최대 한 번씩 등장합니다. 즉, 이전에 꺼낸 컨테이너 종류를 다시 꺼내지 않습니다.</td>
</tr>
<tr>
<td>#4</td>
<td>50%</td>
<td>제한사항 외 추가조건이 없습니다.</td>
</tr>
</tbody>
      </table>

##### 제한사항
<ul>
<li>2 ≤ <code>storage</code>의 길이 = <code>n</code> ≤ 50

<ul>
<li>2 ≤ <code>storage[i]</code>의 길이 = <code>m</code> ≤ 50

<ul>
<li><code>storage[i][j]</code>는 위에서 부터 <code>i + 1</code>번째 행 <code>j + 1</code>번째 열에 놓인 컨테이너의 종류를 의미합니다.</li>
<li><code>storage[i][j]</code>는 알파벳 대문자입니다.</li>
</ul></li>
</ul></li>
<li>1 ≤ <code>requests</code>의 길이 ≤ 100

<ul>
<li>1 ≤ <code>requests[i]</code>의 길이 ≤ 2</li>
<li><code>requests[i]</code>는 한 종류의 알파벳 대문자로 구성된 문자열입니다.</li>
<li><code>requests[i]</code>의 길이가 1이면 지게차를 이용한 출고 요청을, 2이면 크레인을 이용한 출고 요청을 의미합니다.</li>
</ul>
</ul>

##### 입출력 예
<table class="table">
        <thead><tr>
<th>storage</th>
<th>requests</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>["AZWQY", "CAABX", "BBDDA", "ACACA"]</td>
<td>["A", "BB", "A"]</td>
<td>11</td>
</tr>
<tr>
<td>["HAH", "HBH", "HHH", "HAH", "HBH"]</td>
<td>["C", "B", "B", "B", "B", "H"]</td>
<td>4</td>
</tr>
</tbody>
      </table>

##### 입출력 예 설명
**예제 #1**

문제 설명의 예시와 같습니다.

**예제 #2**

<p><img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/95339b77-babc-4be8-96ee-60235ea50393/%E1%84%86%E1%85%AE%E1%86%AF%E1%84%85%E1%85%B2%E1%84%8E%E1%85%A1%E1%86%BC%E1%84%80%E1%85%A9-2.drawio.png" title="" alt="물류창고-2.drawio.png"></p>

창고의 초기 상태와 모든 요청을 수행한 뒤의 상태입니다. 남은 컨테이너의 수인 4를 return 해야 합니다.


---
#### 📍 풀이
- 조건에 따라 구현, 구현 과정에서 BFS 활용
- 지게차를 이용할 때
  - 해당 글자와 일치하는 컨테이너가 위치한 곳이 외부에서 접근이 가능한지 판별한다. (BFS)
    - 상하좌우로 탐색하며 빈 컨테이너가 있으면 Queue에 삽입
    - Queue에서 꺼낸 위치가 외부에서 접근 가능하면 (꺼낸 위치의 row 또는 column이 `0`이거나 `n - 1 || m - 1` 이면) `true` 반환
    - while문이 종료될 때까지 `true`를 반환하지 못했다면, 외부에서 접근이 불가능하므로, `false` 반환
  - 외부에서 접근이 가능하다면, 컨테이너의 위치를 List에 삽입한다.
  - `storageArr`를 모두 순회하며 컨테이너를 찾았으면, List에 저장된 위치에 해당하는 컨테이너를 모두 제거한다.
    - ***컨테이너를 찾았을 때 바로 제거하지 않는이유***
      - 지게차로 컨테이너를 동시에 제거하는 것이기 때문에 한 컨테이너를 제거하고, 다음 컨테이너를 제거하면 외부와 닿아있는 컨테이너가 새롭게 발생할 가능성이 있다.
- 크레인을 이용할 때
  - 해당 글자와 일치하는 컨테이너를 모두 제거한다.
---
#### 📍 느낀점
- 컨테이너를 동시에 제거하는 조건에서는 실수를 하지 않았는데, 예제를 제대로 읽지 않아서 '외부와 닿아있다'는 조건을 오해했다. 제발 주어진 예제만큼은 시뮬레이션을 정확히 해보자.😢