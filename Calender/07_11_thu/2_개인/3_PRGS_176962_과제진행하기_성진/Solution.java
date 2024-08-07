import java.util.*;

// 과목에 대한 클래스를 생성
// 멤버 변수는 이름 / 시작 시각(내부 로직으로 분 형태로 int형 통일) /
class Subject implements Comparable<Subject>{
    String name;
    int start;
    int playtime;
    
    
    public Subject(String[] plan){
        // 과목의 이름의 경우 plan의 첫 번째 원소 그대로 String으로 받아오기
        this.name = plan[0];

        // hour와 min을 분리한다.
        // ":"으로 split하는 방법도 있는데 substring으로 잘라봤음
        int hour = Integer.parseInt(plan[1].substring(0, 2));
        int min = Integer.parseInt(plan[1].substring(3, 5));
        
        // 자른 시간(hour)과 분(min)을 분 단위로 변환
        // ex: 11:40의 경우 11*60 + 40 = 700 분으로 변환
        this.start = hour * 60 + min;
        this.playtime = Integer.parseInt(plan[2]);
    }
    
    // compareTo 연습해봤슴니당
    @Override
    public int compareTo(Subject s){
        return this.start - s.start;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        
        // 시작 시간을 기준으로 pq를 생성
        // 시작하기로 한 시간이 되면 바로바로 pq에서 과목을 꺼낸다
        PriorityQueue<Subject> pq = new PriorityQueue<>();
        // 만약 진행중이던 과제가 끝나지 않는다면 스택에 쌓아두고
        // 시간이 빌 때마다 스택에서 빼오는 구조를 설계
        Stack<Subject> stack = new Stack<>();
        
        for (int i = 0; i < plans.length; i++) {
            pq.add(new Subject(plans[i]));
        }
        
        // 가장 먼저 시작할 과목 꺼내기
        Subject doingSub = pq.poll();
        // 맨 처음 시작하는 시간을 기록
        int time = doingSub.start;
        
        // pq가 비거나, stack이 비는 경우 while문을 탈출
        // pq
        while (!pq.isEmpty() || !stack.isEmpty()) {
            if (!pq.isEmpty() && pq.peek().start < time + doingSub.playtime) { // 현재 과목을 끝내기 전에 다른 과목이 시작되면
                doingSub.playtime -= (pq.peek().start - time);
                stack.push(doingSub); // 현재 과목을 스택에 저장
                doingSub = pq.poll(); // 새로운 과목을 시작
                time = doingSub.start;
            } else { // 현재 과목을 끝낼 수 있으면
                time += doingSub.playtime;
                answer.add(doingSub.name); // 완료된 과목 리스트에 추가
                
                // 스택에 저장된 과목을 이어서 진행
                if (!stack.isEmpty()) {
                    doingSub = stack.pop();
                } else if (!pq.isEmpty()) {
                    doingSub = pq.poll();
                    time = Math.max(time, doingSub.start); // 새로운 과목 시작 시간으로 갱신
                }
            }
        }
        
        // 모든 과목을 완료
        answer.add(doingSub.name);
        
        String[] strAnswer = new String[answer.size()];
        
        for (int i = 0; i < answer.size(); i++) {
            strAnswer[i] = answer.get(i);
        }
        
        return strAnswer;
    }
}
