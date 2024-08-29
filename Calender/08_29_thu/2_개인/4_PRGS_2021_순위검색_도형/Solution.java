package PRGS._2021Kakao_순위검색;

import java.util.*;

/*
 * 구상: 
 * 
 * 데이터 한 개가 3 * 2 * 2 * 2 = 24개의 경우의 수를 가짐
 * => 4차원 배열을 [3][2][2][2] 사이즈로 만들어서 그 안에 점수 List를 저장하자. 
 * 
 * Query문이 모든 파라미터를 지정해서 들어오면 그냥 O(1)로 접근 가능하다.
 * 
 * 만약 " - " 를 쓰게 되면 해당 차원을 모두 훑어 줘야 하는데 이 부분이 조금 까다로웠다.
 * => 배열 사이즈에 따라서 입력이 들어오면 다르게 분기 처리 해주었다.
 * 
 * "특정 점수 이상" 조건을 처리하는게 시간초과가 날 것 같았는데,
 * 50_000 개 데이터에 대해서 쿼리 100_000 번 호출이라 O(N)으로 검색하면 너무 느리다.
 * 점수 주어지면 그 점수가 몇번째인지가 궁금한거라 이분탐색으로 찾아보자 생각했고,
 * 
 * 배열에 넣은 다음 정렬하는 것보다
 * 주어진 info 배열을 정렬한 다음에 하나씩 배열에 넣어주는게 더 간단하고 빠르다.
 *  
 * 
 */

class Solution {
	
    static List[][][][] table;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        List<P> infos = new ArrayList<P>();
        
        for (String i : info) {
            infos.add(new P(i));
        }
        
        // 미리 정렬해서 하나씩 뽑아서 P 객체로 만들어 넣을 때 자동으로 정렬돼서 들어가도록 
        Collections.sort(infos);
        
        table = new ArrayList[3][2][2][2];
        
        for (int i = 0; i < infos.size(); i++) {
            P p = infos.get(i);
            List li = table[p.lang][p.fb][p.js][p.pc];
            if (li == null) {
                li = new ArrayList<Integer>();
            }
            li.add(p.score);
            
            // 이거 안해도 되는 줄 알았는데 해야 되네??
            table[p.lang][p.fb][p.js][p.pc] = li;
        }
        
        for (int i = 0; i < query.length; i++) {
            answer[i] = answerQuery(query[i]);
        }
        
        
        return answer;
    }
    class P implements Comparable<P> {
        int lang; // cpp, java, python
        int fb; // front, back
        int js; // junior, senior
        int pc; // pizza, chicken
        int score;
        
        // 생성자 분기처리
        P(String str) {
            StringTokenizer st = new StringTokenizer(str);
            String l = st.nextToken();
            if (l.equals("cpp")) this.lang = 0;
            else if (l.equals("java")) this.lang = 1;
            else this.lang = 2;
            
            if (st.nextToken().equals("backend")) this.fb = 0;
            else this.fb = 1;
            
            if (st.nextToken().equals("junior")) this.js = 0;
            else this.js = 1;
            
            if (st.nextToken().equals("chicken")) this.pc = 0;
            else this.pc = 1;
            
            this.score = Integer.parseInt(st.nextToken());
        }
        
        // Collections.sort 에 쓰는 compareTo
        @Override
        public int compareTo(P p) {
            return this.score - p.score;
        }
    }
    
    // 정렬되어 있는 List에서 주어진 value가 몇번째 인지를 이분탐색으로 찾아
    // 해당 value 이상이 몇개인지 리턴해주는 함수
    int binarySearch(int value, List<Integer> li) {
        int left = 0;
        int right = li.size();

        while (left < right) {
            int mid = (left + right) / 2;
    
            if (li.get(mid) < value) {
                left = mid+1;
            } else {
                right = mid;
            }
            
        }
        
        return li.size() - right;
        
    }
    
    int answerQuery(String query) {
        int answer = 0;
        
        StringTokenizer st = new StringTokenizer(query, " "); // " and " 로 구분하고 싶은데 잘 안됐음
        
        int[] filter1 = fromTo(st.nextToken(), true);
        st.nextToken(); // and
        int[] filter2 = fromTo(st.nextToken(), false);
        st.nextToken(); // and
        int[] filter3 = fromTo(st.nextToken(), false);
        st.nextToken(); // and
        int[] filter4 = fromTo(st.nextToken(), false);
        
        int score = Integer.parseInt(st.nextToken());
        
        for (int i = filter1[0]; i <= filter1[1]; i++) {
            for (int j = filter2[0]; j <= filter2[1]; j++) {
                for (int k = filter3[0]; k <= filter3[1]; k++) {
                    for (int l = filter4[0]; l <=filter4[1]; l++) {
                        
                        if (table[i][j][k][l] != null) {
                            answer += binarySearch(score, table[i][j][k][l]);
                        }
                        
                    }
                }
            }
        }
        return answer;
        
    }
    
    int[] fromTo(String filter, boolean isLang) {
        
    	// 핀포인트로 지정
        if (filter.equals("cpp") || filter.equals("backend") || filter.equals("junior") || filter.equals("chicken")) return new int[] {0, 0};
        if (filter.equals("java") || filter.equals("frontend") || filter.equals("senior") || filter.equals("pizza")) return new int[] {1, 1};
        if (filter.equals("python")) return new int[] {2,2};
        
        // " - " 로 범위 지정
        // 프로그래밍 언어만 3칸이라서 예외 처리
        if (filter.equals("-") && isLang) return new int[] {0, 2};
        if (filter.equals("-") && !isLang) return new int[] {0, 1};
        return null;
    }
}