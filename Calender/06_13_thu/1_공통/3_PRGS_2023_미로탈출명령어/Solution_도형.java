package PRGS._2023Kakao_미로탈출명령어;

class Solution {
    // 매번 d , l , r , u 순으로 이동했을 때를 가정하고, (k 는 하나 줄고, 위치도 바꾸고)
	// 목적지에 도달할 수 있는가 없는가만 생각하면 됨.
	// 도달할 수 있다면, answer에 해당 이동을 적고, 다음 루프로 들어가면 됨.
	// 도달할 수 없다면, 다음 사전순의 이동을 테스트 해본다.
	// 그 어떤 이동으로도 도달할 수 없다면, impossible 출력
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        while(k != 0) {
            if (check(n,m,x+1,y,r,c,k-1)) {
                answer += "d";
                x++;
                k--;
                continue;
            }
            if (check(n,m,x,y-1,r,c,k-1)) {
                answer +="l";
                y--;
                k--;
                continue;
            }
            if (check(n,m,x,y+1,r,c,k-1)) {
                answer +="r";
                y++;
                k--;
                continue;
            }
            if (check(n,m,x-1,y,r,c,k-1)) {
                answer +="u";
                x--;
                k--;
                continue;
            }
            return "impossible";
        }
        
        return answer;
    }
    
    public boolean check(int n, int m, int x, int y, int r, int c, int k) {
    	// 맵을 벗어나는 이동이면 불가
        if (x <1 || x > n || y <1 || y > m) return false;
        // mahattan 거리를 구해서
        int manhattan = Math.abs(r-x) + Math.abs(c-y);
        // 거리가 남은 k 보다 멀면 불가
        if (manhattan > k) return false;
        // 거리와 k 의 차가 2로 나눈 것이 나누어 떨어지지 않으면 불가
        if ((k - manhattan) % 2 != 0) return false;
        return true;
    }
    
}