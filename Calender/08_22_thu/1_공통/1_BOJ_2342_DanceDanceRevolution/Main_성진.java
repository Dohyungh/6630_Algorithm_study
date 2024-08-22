import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 입력 -> int형 배열 step에 0 빼고 저장
        String[] input = sc.nextLine().split(" ");
        int[] step = new int[input.length-1];
        for (int i=0; i<input.length-1; i++){
            step[i] = Integer.parseInt(input[i]);
        }

        // dp 테이블 생성
        // 왼발 / 오른발 / 스텝
        int[][][] dp = new int[5][5][input.length];

        // dp 초기화
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                for (int k=0; k<dp[i][j].length; k++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        // 0번째 스텝 (0, 0)은 필요한 힘: 0
        dp[0][0][0] = 0;

        // dp 조작
        for (int k=0; k<input.length-1; k++){
            int next = step[k];
            for (int i=0; i<5; i++){
                for (int j=0; j<5; j++){
                    int prevPower = dp[i][j][k];
                    if (prevPower == Integer.MAX_VALUE) {
                        continue;
                    }
                    // 왼발을 움직이는 경우
                    dp[next][j][k+1] = Math.min(dp[next][j][k+1], prevPower + stepPower(i, next));
                    // 오른발을 움직이는 경우
                    dp[i][next][k+1] = Math.min(dp[i][next][k+1], prevPower + stepPower(j, next));
                }
            }
        }


        int answer = Integer.MAX_VALUE;

        for (int i=0; i<5; i++){
//            System.out.println();
            for (int j=0; j<5; j++){
//                System.out.print(dp[i][j][1] + " ");
                if (answer > dp[i][j][input.length-1]){
                    answer = dp[i][j][input.length-1];
                }
            }
        }
        System.out.println(answer);

    }

    
    static int stepPower(int prev, int next){
        // 전과 후가 같은 칸인 경우
        if (prev == next){
            return 1;
        }
        // 전에 0번에 위치했던 경우
        else if(prev == 0){
            return 2;
        }
        // 차이가 2인 경우(건너 뛰어가는 경우)
        else if(Math.abs(prev - next) == 2){
            return 4;
        }
        // 인접한 곳으로 이동하는 경우
        else{
            return 3;
        }
    }


}