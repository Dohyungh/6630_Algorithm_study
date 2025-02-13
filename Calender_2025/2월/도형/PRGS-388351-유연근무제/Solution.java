package PRGS._2025CodeChallenge_유연근무제;

class Solution {
    int getTenLater (int time) {
        int hour = time / 100;
        int minute = time % 100 + 10;
        
        if (minute >= 60) {
            hour += 1;
            minute = minute - 60;
        }
        return 100 * hour + minute;
        
    }
    public int solution(int[] schedules, int[][] timelogs, int startday) {
               
        int N = schedules.length;
        
        int prize = 0;
        
        label:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 7; j++) {
                int day = (startday + j + 7) % 7;
                if (day == 6 || day == 0) continue;
                if (timelogs[i][j] > getTenLater(schedules[i])) continue label;
            }

            prize++;
        }
        
        return prize;
    }
}