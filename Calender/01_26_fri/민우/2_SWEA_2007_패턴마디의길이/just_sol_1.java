package Study_for_2024_01_26.problem2;

import java.util.*;
class just_sol_1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        sc.nextLine();    // 엔터 이슈 쉽게 해결하네
        for(int t=1; t<=T; t++){
            String txt=sc.nextLine();
            System.out.println("#"+t+" "+txt.indexOf(txt.substring(0,3),3));    // input에 맞춘 쓰레기 코드같지만 indexOf를 잘 쓰면 맛있게 짤 수 있을 듯
        }}}