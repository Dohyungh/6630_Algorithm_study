import java.util.*;

class Solution {
    
    public int solution(int[][] points, int[][] routes) {
        
        int answer = 0;
        
        // 각 route별 경로를 모두 저장할 배열 allPath
        List<Integer>[] allPath = new ArrayList[routes.length];
        // 각 route별 경로마다 최대 이동 시간이 다르므로 이를 계산
        int maxLen = -1;
        
        
        // route마다 반복문 돌면서
        for (int r=0; r<routes.length; r++){
            
            // allPath 배열에 담길 리스트(경로를 담을) 선언
            List<Integer> path = new ArrayList<>();
            
            // 도착점의 r,c인 end r, end c 미리 선언
            int er = 0;
            int ec = 0;
            
            for (int i=0; i<routes[r].length-1; i++){
                // 각 route의 내부를 순회하며 시작포인트와 도착포인트를 갱신
                int start = routes[r][i]-1;
                int end = routes[r][i+1]-1;
                // System.out.println(start);
                // System.out.println(end);
                
                int sr = points[start][0];
                int sc = points[start][1];
                
                er = points[end][0];
                ec = points[end][1];
                // 도착점에 도착할 때까지 반복
                while (sr != er || sc != ec){
                    // 도착점에 도착했다면 path에 add
                    if (sr == er && sc == ec){
                        path.add(sr*100 + sc);
                    }
                    // 경로에 해당하는 r,c -> 1차원으로 변환해 list에 add
                    path.add(sr*100+sc);
                    
                    // r을 먼저, c를 다음에 움직이는 로직
                    if (sr > er){
                        sr--;
                        continue;
                    }
                    if (sr < er){
                        sr++;
                        continue;
                    }
                    if (sc > ec){
                        sc--;
                        continue;
                    }
                    if (sc < ec){
                        sc++;
                        continue;
                    }
                }
                
                // path.add(er*100+ec);
                
            }
            path.add(er*100+ec);
            // System.out.println(path);
            // 가장 긴 경로 이동 시간을 갱신
            if (path.size() > maxLen){
                maxLen = path.size();
            }
            // allPath 배열에 순서대로 path 리스트를 저장
            allPath[r] = path;
            
        }
        
        // 최대 이동 시간만큼 반복문 수행
        for (int l=0; l<maxLen; l++){
            // map을 통해 1초마다의 경로가 겹쳤는지 카운트
            Map<Integer, Integer> map = new HashMap<>();
            
            for (int i=0; i<allPath.length; i++){
                // 경로가 남은 것들만 대상으로 get 접근
                if (allPath[i].size() > l){
                    // 아직 어떠한 로봇도 해당 경로에 도착한적 없다면 map에 새롭게 put
                    if (!map.containsKey(allPath[i].get(l))){
                        map.put(allPath[i].get(l), 1);
                    }
                    // 만약 이미 다른 로봇이 경로에 있었다면(충돌), value인 카운트를 늘림
                    else{
                        int cnt = map.get(allPath[i].get(l));
                        // System.out.println(cnt);
                        map.replace(allPath[i].get(l), cnt+1);
                        // map.put(allPath[i].get(l), cnt+1);
                    }

                }
            }
            // System.out.println(map);
            
            // map을 순회하며 각각의 value가 1 이상인 것들의 개수(충돌 횟수)를 세서 answer로 반환
            for (Integer key : map.keySet()){
                if (map.get(key) > 1){
                    answer++;
                }
            }
            
        }
        
        return answer;
    }
    
    
}