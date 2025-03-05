## 📖 [카펫](https://school.programmers.co.kr/learn/courses/30/lessons/42842)
#### 📍 문제
##### 문제 설명

Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

<img src="https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/b1ebb809-f333-4df2-bc81-02682900dc2d/carpet.png" title="" alt="carpet.png">

Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

##### 제한사항
```
- 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
- 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
- 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
```

##### 입출력 예
<table class="table">
        <thead><tr>
<th>brown</th>
<th>yellow</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>10</td>
<td>2</td>
<td>[4, 3]</td>
</tr>
<tr>
<td>8</td>
<td>1</td>
<td>[3, 3]</td>
</tr>
<tr>
<td>24</td>
<td>24</td>
<td>[8, 6]</td>
</tr>
</tbody>
      </table>

---
#### 📍 풀이
- 주어진 갈색 칸의 개수에서 4를 빼고 2로 나눈 수를 `sum` 이라고 하면 `a + b = sum`을 만족하는 `a`와 `b`가 노란색 칸의 가로와 세로 길이이다.
- 따라서 `a = sum - 1`, `b = sum - a`로 놓고 `a`는 1씩 감소, `b`는 1씩 증가시키며 `a * b = yellow`를 만족하는 `a`와 `b`를 찾는다.
---
#### 📍 느낀점
- 수식만 찾으면 쉬운 문제였다. 😎