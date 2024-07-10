package PRGS._2022Kakao_두큐합같게만들기;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        
        // 누적합 배열을 상대가 나에게 넘겨준 것까지 포함해서 작성해야 함 <- 이게 이 문제의 가장 큰 포인트인 듯
        // 따라서 2N 크기의 배열이 필요함
        int[] sum1 = new int[2*N+1];
        int[] sum2 = new int[2*N+1];
        
        // 0으로 누적합 패딩
        // 후에 쓸 포인터는 즉, 몇개를 넘길 것이냐를 뜻함.
        // index가 아니라! <- 헷갈리지 않게 주의
        sum1[0] = 0;
        sum2[0] = 0;
        
        // 누적합 생성
        for (int i = 1; i <= N; i++) {
            sum1[i] = sum1[i-1] + queue1[i-1];
            sum2[i] = sum2[i-1] + queue2[i-1];
        }
        
        // 상대가 넘겨줄 것을 가정하고 상대껄로까지 누적합 마저 생성
        for (int i = N+1; i <= 2*N; i++) {
            sum1[i] = sum1[i-1] + queue2[i-N-1];
            sum2[i] = sum2[i-1] + queue1[i-N-1];
        }
        
        // 투포인터
        // 마지막 원소까지의 합을 gap으로 둔 다음에 
        // 포인터가 각각 가리키는 누적합의 차이 *2 가
        // gap과 같아질 때 정답을 찾은 것이므로 break
        
        // 그 차이가 0보다 크면 지금 차이가 구해야하는 차이보다 크므로 point1을 전진시켜서 차이를 줄이고
        // 그 차이가 0보다 작으면 지금 차이가 구해야하는 차이보다 작으므로 point2를 전진시켜서 차이를 늘린다.
        int point1 = 0;
        int point2 = 0;
        
        int gap = sum1[N] - sum2[N];
        if (gap == 0) return 0;
        while (point1 <= 2*N && point2 <= 2*N) {
            int nowGap = (sum1[point1] - sum2[point2])*2;
            
            int temp = gap - nowGap;
            
            if (temp == 0) {
                return point1+point2;
            }
            if (temp > 0) {
                point1++;
                continue;
            }
            if (temp < 0) {
                point2++;
                continue;
            }
        }
        
        return -1;
    }
    
/*
     * 테스트 1 〉	통과 (0.01ms, 67.5MB)
테스트 2 〉	통과 (0.02ms, 76.6MB)
테스트 3 〉	통과 (0.02ms, 83.3MB)
테스트 4 〉	통과 (0.02ms, 81.4MB)
테스트 5 〉	통과 (0.03ms, 74MB)
테스트 6 〉	통과 (0.06ms, 72.8MB)
테스트 7 〉	통과 (0.06ms, 75.2MB)
테스트 8 〉	통과 (0.09ms, 79.5MB)
테스트 9 〉	통과 (0.11ms, 72.9MB)
테스트 10 〉	통과 (0.19ms, 78.6MB)
테스트 11 〉	통과 (8.04ms, 89.6MB)
테스트 12 〉	통과 (3.92ms, 90.5MB)
테스트 13 〉	통과 (4.46ms, 80.5MB)
테스트 14 〉	통과 (5.95ms, 88.6MB)
테스트 15 〉	통과 (6.28ms, 86.3MB)
테스트 16 〉	통과 (5.34ms, 81.7MB)
테스트 17 〉	통과 (6.65ms, 100MB)
테스트 18 〉	통과 (11.73ms, 135MB)
테스트 19 〉	통과 (12.35ms, 130MB)
테스트 20 〉	통과 (13.86ms, 128MB)
테스트 21 〉	통과 (8.89ms, 129MB)
테스트 22 〉	통과 (14.58ms, 137MB)
테스트 23 〉	통과 (12.62ms, 126MB)
테스트 24 〉	통과 (12.03ms, 136MB)
테스트 25 〉	통과 (0.03ms, 76.3MB)
테스트 26 〉	통과 (0.05ms, 76.4MB)
테스트 27 〉	통과 (0.03ms, 79.4MB)
테스트 28 〉	통과 (7.11ms, 99.2MB)
테스트 29 〉	통과 (0.84ms, 81.9MB)
테스트 30 〉	통과 (5.27ms, 100MB)
 */
}