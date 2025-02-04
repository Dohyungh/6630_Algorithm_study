[문제링크](https://softeer.ai/practice/6276)

<div class="ide-question__prompt" data-zoom="100" style="height: 100%; overflow-y: auto;"><div class="ide-prompt__title" style="font-size: 2.2rem; margin-bottom: 2rem;"><strong>[HSAT 2회 정기 코딩 인증평가 기출] Garage game</strong></div><div class="ide-example__input"><div class="ide-prompt"><div class="ide-prompt__desc"><table style="width: 100%;"><colgroup><col style="width: auto;"><col style="width: auto;"><col style="width: auto;"></colgroup><thead><tr style="border-bottom: 1px solid rgb(226, 232, 240);"><th style="text-align: left; padding: 8px;">언어</th><th style="text-align: right; padding: 8px;">시간 제한</th><th style="text-align: right; padding: 8px;">메모리 제한</th></tr></thead><tbody><tr><td style="text-align: left; padding: 8px;">JavaScript</td><td style="text-align: right; padding: 8px;">2초</td><td style="text-align: right; padding: 8px;">256MB</td></tr><tr><td style="text-align: left; padding: 8px;">C</td><td style="text-align: right; padding: 8px;">1초</td><td style="text-align: right; padding: 8px;">256MB</td></tr><tr><td style="text-align: left; padding: 8px;">C++</td><td style="text-align: right; padding: 8px;">1초</td><td style="text-align: right; padding: 8px;">256MB</td></tr><tr style="font-weight: bold; text-decoration: underline;"><td style="text-align: left; padding: 8px;">JAVA</td><td style="text-align: right; padding: 8px;">2초</td><td style="text-align: right; padding: 8px;">256MB</td></tr><tr><td style="text-align: left; padding: 8px;">Python</td><td style="text-align: right; padding: 8px;">2초</td><td style="text-align: right; padding: 8px;">256MB</td></tr></tbody></table></div></div></div><div class="ide-example__input"><div class="ide-prompt__title"><strong>문제</strong></div><div class="ide-prompt"><div class="ide-prompt__desc"><p class="qti-paragraph" dir="ltr"><span>자율주행 기술의 발전과 함께 차량 내 인포테인먼트 기술 또한 많은 주목을 받고 있다. 최근 자동차 실내에는 디스플레이의 대형화를 비롯해 새로운 제어 기술이 빠르게 적용되고 있는데, 완전 자율주행 시대가 다가올 수록 이런 변화가 가속화될 전망이다.</span></p><p class="qti-paragraph" dir="ltr"><span>개인 맞춤형 인포테인먼트 시스템 역시 핵심 기능 중 하나다. 자율주행 시대에 발맞춰 차량 내 디지털 시스템을 활용한 게임을 개발하고 있다.</span></p><p class="qti-paragraph" dir="ltr"><br></p><p class="qti-paragraph" dir="ltr"><img src="https://www.softeer.ai/upload/2021/08/20210826_141933551_97152.jpeg" alt="" width="inherit" height="inherit"><br></p><p class="qti-paragraph" dir="ltr"><br></p><p class="qti-paragraph" dir="ltr"><span>게임의 룰은 가로 세로 N칸의 차고(격자)가 있고, 각 차고에는 색깔이 있는 자동차가 하나씩 있다. 한 턴에 한 칸을 선택하며, 선택한 칸과 상하좌우 칸에 들어있는 자동차의 색이 같다면 모두 사라진다. 그리고 사라진 칸들과 연결된 칸들의 상하좌우 칸에 들어있는 자동차의 색이 같다면 함께 사라진다. (문제 하단 예시 참고.)</span></p><p class="qti-paragraph" dir="ltr"><br></p><p class="qti-paragraph" dir="ltr"><span>이때, 획득할 수 있는 점수는 사라진 자동차의 개수와 사라지는 차고 칸을 모두 포함하는 가장 작은 직사각형의 넓이의 합이다.</span></p><p class="qti-paragraph" dir="ltr"><span>자동차들이 사라지고 나면 위에 있는 자동차들이 아래로 떨어져 빈 칸을 채운다. 위쪽에는 충분한 자동차들이 더 있어서 위에서 추가적으로 떨어지며 모든 칸을 채운다.</span></p><p class="qti-paragraph" dir="ltr"><br></p><p class="qti-paragraph" dir="ltr"><span>위에서 어떤 자동차들이 떨어질지는 입력으로 주어진다. 위와 같은 게임을 3차례 반복 했을 때, 주어진 조건에서 얻을 수 있는 가장 큰 점수를 계산하라.</span></p></div></div></div><div class="ide-example__input"><div class="ide-prompt__title"><strong>제약조건</strong></div><div class="ide-prompt"><p class="ide-prompt__desc"><p class="qti-paragraph" dir="ltr"><span>1 ≤ N ≤ 15</span></p><p class="qti-paragraph" dir="ltr"><span>색상 번호는 1 이상 10</span><sup><span class="EditorTheme__textSuperscript">9</span></sup><span>이하의 자연수이다.</span></p></p></div></div><div class="ide-example__input"><div class="ide-prompt__title"><strong>입력형식</strong></div><div class="ide-prompt"><p class="ide-prompt__desc"><p class="qti-paragraph" dir="ltr"><span>입력으로는 차고 격자 칸의 가로, 세로 길이인 N이 첫 줄에 주어진다.</span></p><p class="qti-paragraph" dir="ltr"><span>다음 3N개의 줄에 N개의 자연수가 주어진다.</span></p><p class="qti-paragraph" dir="ltr"><span>각 자연수는 차고 칸에 있는 자동차의 색상 번호이다.</span></p></p></div></div><div class="ide-example__input"><div class="ide-prompt__title"><strong>출력형식</strong></div><div class="ide-prompt"><p class="ide-prompt__desc"><p class="qti-paragraph" dir="ltr"><span>주어진 조건에서 게임을 3차례 시뮬레이션 했을 때 얻을 수 있는 가장 큰 점수를 출력한다.</span></p></p></div></div><div><div class="ide-question__example"><div class="ide-example__input" style="margin-top: 16px;">

<div class="ide-prompt__title"><strong>입력 예시 1</strong></div>

```
2
1 1
2 2
1 1
3 3
4 4
1 2
```
<div class="ide-prompt__title"><strong>출력 예시 1</strong></div>

```
15
```
<div class="ide-prompt__title"><strong>입력 예시 2</strong></div>

```
3
8 5 1
9 6 1
10 7 1
11 1 3
12 1 3
13 1 3
1 2 2
1 2 2
1 2 2
```
<div class="ide-prompt__title"><strong>출력 예시 2</strong></div>

```
36
```

<div class="ide-example__input" style="margin-top: 16px;"><p class="ide-example__desc"><p class="qti-paragraph" style="text-align: center;"><br><img src="https://www.softeer.ai/upload/2021/08/20210826_155300543_92151.jpeg" alt="" width="inherit" height="inherit"><br></p><p class="qti-paragraph" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>첫번째 예제를 보면, 시뮬레이션 1회차에 4번 색상의 차량을 가지고 있는 차고(격자)가 2곳이 있다. 사라지는 자동차의 개수(2점)와 해당 차고를 포함하는 가장 작은 직사각형 면적(2점)을 합한 4점을 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>시뮬레이션 2회차에서 3번 색상의 차량을 가지고 있는 차고가 2곳이 있다. 사라지는 자동차의 개수(2점)와 해당 차고를 포함하는 가장 작은 직사각형 면적(2점)을 합한 4점을 획득하여 총 8점 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>마지막으로, 시뮬레이션 3회차에서 1번 색상의 차량을 가지고 있는 차고가 3곳이 있다. 사라지는 자동차의 개수(3점)와 해당 차고를 포함하는 가장 작은 직사각형 면적(4점)을 합한 7점을 획득하여, 총 15점을 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: center;"><img src="https://www.softeer.ai/upload/2021/08/20210826_155327031_93034.jpeg" alt="" width="inherit" height="inherit"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>두번째 예제를 보면, 시뮬레이션 1회차에 2번 색상의 차량을 가지고 있는 차고가 6곳이 있다. 사라지는 자동차의 개수(6점)와 해당 차고를 포함하는 가장 작은 직사각형 면적(6점)을 합한 12점을 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>시뮬레이션 2회차에서 3번 색상의 차량을 가지고 있는 차고가 3곳이 있다. 사라지는 자동차의 개수(3점)와 해당 차고를 포함하는 가장 작은 직사각형의 면적(3점)을 합한 6점을 획득하여 총 18점 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>마지막으로, 시뮬레이션 3회차에서 1번 색상의 차량을 가지고 있는 차고가 9곳이 있다. 사라지는 자동차의 개수(9점)와 해당 차고를 포함하는 가장 작은 직사각형의 면적(9점)를 합한 18점을 획득하여, 총 36점을 획득한다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><span>만약 아래와 같이 시뮬레이션 2회차에서 1번 색상의 차량을 선택하는 경우, 사라지는 자동차의 개수(6점)와 해당 차고를 포함하는 가장 작은 직사각형의 면적(6점)을 합한 12점을 획득할 수 있으나, 시뮬레이션 3회차에서 사라지는 자동차의 개수(3점)과 해당 차고를 포함하는 가장 작은 직사각형의 면적(3점)으로 6점을 획득하여 총 30점을 획득하여 가장 큰 점수가 될 수 없다.</span></p><p class="qti-paragraph" dir="ltr" style="text-align: justify;"><br></p><p class="qti-paragraph" dir="ltr" style="text-align: center;"><img src="https://www.softeer.ai/upload/2021/08/20210826_155710408_92071.jpeg" alt="" width="inherit" height="inherit"></p></p></div></div></div></div>