# 1의 개수 세기 - 9527 

[문제 링크](https://www.acmicpc.net/problem/9527) 

### 성능 요약

메모리: 14260 KB, 시간: 100 ms

### 분류

비트마스킹, 수학, 누적 합

### 제출 일자

2025년 1월 22일 17:40:11

### 문제 설명

<p>두 자연수 A, B가 주어졌을 때, A ≤ x ≤ B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때 1의 개수의 합을 구하는 프로그램을 작성하시오.</p>

<p>즉, f(x) = x를 이진수로 표현 했을 때 1의 개수라고 정의하고, 아래 식의 결과를 구하자.</p>

<p><mjx-container class="MathJax" jax="CHTML" display="true" style="font-size: 109%; position: relative;"> <mjx-math display="true" class="MJX-TEX" aria-hidden="true" style="margin-left: 0px; margin-right: 0px;"><mjx-munderover><mjx-over style="padding-bottom: 0.2em; padding-left: 0.474em;"><mjx-texatom size="s" texclass="ORD"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D435 TEX-I"></mjx-c></mjx-mi></mjx-texatom></mjx-over><mjx-box><mjx-munder><mjx-row><mjx-base style="padding-left: 0.02em;"><mjx-mo class="mjx-lop"><mjx-c class="mjx-c2211 TEX-S2"></mjx-c></mjx-mo></mjx-base></mjx-row><mjx-row><mjx-under style="padding-top: 0.167em;"><mjx-texatom size="s" texclass="ORD"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c3D"></mjx-c></mjx-mo><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D434 TEX-I"></mjx-c></mjx-mi></mjx-texatom></mjx-under></mjx-row></mjx-munder></mjx-box></mjx-munderover><mjx-texatom space="2" texclass="ORD"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D453 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-texatom></mjx-math><mjx-assistive-mml unselectable="on" display="block"><math xmlns="http://www.w3.org/1998/Math/MathML" display="block"><munderover><mo data-mjx-texclass="OP">∑</mo><mrow data-mjx-texclass="ORD"><mi>x</mi><mo>=</mo><mi>A</mi></mrow><mrow data-mjx-texclass="ORD"><mi>B</mi></mrow></munderover><mrow data-mjx-texclass="ORD"><mi>f</mi><mo stretchy="false">(</mo><mi>x</mi><mo stretchy="false">)</mo></mrow></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext"></span> </mjx-container></p>

### 입력 

 <p>첫 줄에 두 자연수 A, B가 주어진다. (1 ≤ A ≤ B ≤ 10<sup>16</sup>)</p>

### 출력 

 <p>1의 개수를 세어 출력한다.</p>

### 느낀점

- 시그마의 의미를 10년 가까이 완전히 까먹고 있었다가 이 문제를 풀고 다시 머리에 입력이 됐다. 그냥 문제를 글로 읽은 내용과 같다.
- 규칙이 있다는건 알겠는데, 숫자의 크기가 커서 쌩으로 누적합을 하면 분명 시간초과였다.
- 모르겠어서 다른 풀이를 참고했다. 그림을 직접 그려보면 이해가 잘 된다.

### 설계 : 60분

- 1부터 숫자들의 이진법 표기를 나열하면 규칙이 있다. 2진수의 길이가 1 늘어날때마다(즉, 2의 거듭제곱마다) 왼쪽에 1만 더해지고 처음부터 누적 나열된 뭉텅이가 반복된다.
- 이 규칙을 사용해 2의 각 거듭제곱까지의 이진수 1개수 누적합을 배열로 저장한다.
    - `dp[i] = ((long) Math.pow(2, i)) + (dp[i-1] * 2)`
- 배열에는 2의 거듭제곱 값에 대한 1의 개수만 저장 되어 있다. 따라서 그 사이의 수들은 바로 알 수 없다.
- 하지만 1번으로 언급한 규칙을 사용하면 목표한 수를 비트마스킹으로 역순회하며 dp에 저장된 값들을 더해 빠르게 구할 수 있다.
    
    ![Image](https://github.com/user-attachments/assets/d47fabbc-8bbe-4505-b0e1-be5f5c49be15)