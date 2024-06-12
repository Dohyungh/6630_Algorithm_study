package prgs_k진수에서소수개수구하기;


import java.util.*;

class Solution {
    
	// 소수를 담아놓을 set 생성
    static Set<Long> pSet;
    /// 소수가 아닌 자연수를 담아놓을 set 생성
    static Set<Long> npSet;    
    
    public int solution(int n, int k) {
        // 조건에 맞는 소수의 개수(정답 변수)
    	int answer = 0;
        
    	// 자연수 n을 k진수로 표현했을 때의 숫자를 String으로 변환한 변수 kNum 
        String kNum = convert(n, k);
        
        // k진수를 0을 기준으로 split해 배열로 저장
        String[] kArr = kNum.split("0");
        
        // 소수 담을 set과 소수가 아닌 수 담을 set 생성
        pSet = new HashSet<>();
        npSet = new HashSet<>();
        
        // kArr의 원소 하나씩을 꺼내와 각 부분을 반복 
        for (String part : kArr) {
            if (!part.isEmpty()) {
            	// (k진수) part의 크기가 int형을 넘어가기도 하므로
            	// parseInt가 아니라 parseLong으로 바꿔야 모든 테스트 케이스를 통과할 수 있음...(1번, 11번 test case)
                long num = Long.parseLong(part);
                // isPrime 메소드로 long형의 num이 소수인지 아닌지를 판단하고,
                // 소수가 맞다면 답을 1 증가
                if (isPrime(num)) {
                    answer++;
                }
            }
        }
        // 정답 출력
        return answer;
    }
    
    // 자연수 n을 k진수로 변환하는 메소드
    public String convert(int n, int k){
    	// k진수로 변환할 때 나머지를 이용, 해당 나머지를 append해서 k진수를 만들기 위한 스트링빌더
    	// + 프로그래머스에서 스트링빌더 쓰는 연습해볼 겸...
        StringBuilder sb = new StringBuilder();
        
        // 자연수 n을 temp에 저장
        int temp = n;
        
        // temp를 k로 나누면서 나머지값을 sb에 append하기
        // ex) 자연수 6를 2진수로 표현한다면?
        // 6 % 2 == 0을 append
        // temp <- 6/2
        // 3 % 2 == 1을 append
        // temp <- 3/2
        // 1 % 2 == 1을 append
        // temp <- 1/2 => 0이므로 while문 탈출
        // 이렇게 더한 sb를 reverse로 읽는다면 2진수로 변환 완료
        // 6 == 110(2진수)
        while (temp > 0){
            sb.append(temp % k);
            temp /= k;
        }
        return sb.reverse().toString();
    }

    // 소수인지 아닌지를 판별하는 메소드
    public boolean isPrime(long num) {
    	// 일단 1 이하는 소수가 아니므로 false
        if (num <= 1) {
            return false;
        }
        // 소수 집합에 포함되면 계산 안 하고 바로 true
        if (pSet.contains(num)) {
            return true;
        }
        // 비 소수 집합에 포함되면 계산 안 하고 바로 false
        if (npSet.contains(num)) {
            return false;
        }
        // 2, 3은 소수이므로 먼저 체크
        if (num == 2 || num == 3) {
            pSet.add(num);
            return true;
        }
        // 2 또는 3의 배수라면 false
        if (num % 2 == 0 || num % 3 == 0) {
            npSet.add(num);
            return false;
        }
        
        // 소수의 성질
        // 1. n이 소수인지 파악하기 위해서는 sqrt(n)보다 작거나 같은 수들까지만 배수를 확인하면 된다.(에라토스테네스의 체)
        // 2. 2, 3을 제외한 모든 소수는 6k + 1, 6k - 1꼴이다. 
        long sqrt = (long) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            // i == 5, 11, 17, 23, ... => 6k-1 꼴
            // i+2 == 7, 13, 19, 25, ... => 6k+1 꼴
            if (num % i == 0 || num % (i + 2) == 0) {
                npSet.add(num);
                return false;
            }
        }
        
        // 위에 for문에도 걸리지 않았다면 소수이므로
        // pSet에 더하고, true를 출력
        pSet.add(num);
        return true;
        
        
    }
    
}