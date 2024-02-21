package jol_1205_조커;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 카드의 수 입력
		int N = sc.nextInt();

		int[] cntArr = new int[1000000];

		int[] numArr = new int[N];

		
		boolean startOne = false;
		
		
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();

			cntArr[num]++;
			numArr[i] = num;
			
			if (num == 1) {
				startOne = true;
			}
		}

		int joker = cntArr[0];

		Arrays.sort(numArr);

		System.out.println(Arrays.toString(numArr));

		List<Integer> stepList = new ArrayList<Integer>();

		
		
		
		
		
		int idx = 0;

		
		
		
		int cont = 1;
		
		
		boolean addfirst = false;
		
		
		while (idx < numArr.length) {
			
			if (!startOne && addfirst == false) {
				if (numArr[idx] - 0 > 0) {
					stepList.add(numArr[idx] -1);
					addfirst = true;
				}
			}
			
			
			if (numArr[idx] != 0 && idx < numArr.length-1) {
				
				
				
				

				if (numArr[idx] + 1 == numArr[idx + 1]) {
					
					cont++;

				} else if (numArr[idx] == numArr[idx + 1]) {
					idx++;
					continue;
				}
				
				else {
					
					// 연속된 숫자의 개수를 list에 add.
					stepList.add(cont);
					
					// 그 다음 숫자가 등장할 때까지의 개수를 list에 add.
					stepList.add(numArr[idx+1] - numArr[idx] - 1);
					
//					System.out.println("add numArr[idx]=" + numArr[idx] + ", idx=" + idx);
					cont = 1;
				}

			}
			
			else if (idx == numArr.length-1) {
				stepList.add(cont);
				stepList.add(1000000 - numArr[idx]);
			}
			
			idx++;
		}
		
		
		
		System.out.println(stepList);
		
		
		int startIdx = 0;
		
		if (!startOne) {
			startIdx = 1;
		}
		
		
		int stepSum = 0;
		
		for (int i=startIdx; i<stepList.size(); i+=2) {
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		sc.close();

	}

}
