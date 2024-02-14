package prgs_외벽점검;

public class Solution2 {

	public static void main(String[] args) {

		int n1 = 12;
		int n2 = 12;
		
		int[] weak1 = { 1, 5, 6, 10 };
		int[] weak2 = {1, 3, 4, 9, 10};
		
		int[] dist1 = {1, 2, 3, 4};
		int[] dist2 = {3, 5, 7};

		int[][] arr = makeWeakDistMatrix(weak2);

		printArr(arr);
		
		
		for (int d=dist1.length-1; d>=0; d--) {
			
			int distSum = 0;
			
			for (int r=0; r<arr.length-1; r++) {
				for (int c=r+1; c<arr.length-r; c++) {
					while (distSum <= d) {
						distSum += arr[r][c];
					}
							
				}
			}
			
			
		}
		
		

	}

	
	// makeWeakDistMatrix 메소드
	public static int[][] makeWeakDistMatrix(int[] weak) {
		int[][] arr = new int[weak.length][weak.length];

		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				
				if ((Math.abs(c - r) < weak.length - 1 && Math.abs(c - r) > 1) || r >= c) {
					arr[r][c] = 0;
				}

				else {
					
					if (r == 0 && c == weak.length - 1) {
						
						arr[r][c] = weak[r] + 12 - weak[c];
						
					} else {
						
						arr[r][c] = weak[c] - weak[r];
						
					}


				}
			}
		}

		return arr;

	}

	
	
	
	
	// 2차원 배열 출력 메소드
	public static void printArr(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	}
}
