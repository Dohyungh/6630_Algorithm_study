import java.util.Arrays;

public class Practice {
	public static void print(int[][] arr) {
		for (int i = 0; i<arr.length; i++) {
			System.out.print(Arrays.toString(arr[i])+" ");
			
		}
		System.out.println();
	}
	public static void main(String[] args) {
		
		// Arrays.sort(arr, 람다표현식+익명클래스) 방식을 사용할 것.
		
		int[][] arr = {
				{1,2},
				{2,3},
				{3,2},
				{3,1},
				{1,1},
				{1,4},
				{1,1},
				{2,1}
		};
		
		// 첫번째 원소로 오름차순 (안정정렬됨)
		Arrays.sort(arr, (o1, o2)-> {
			return o1[0] - o2[0];
		});
		
		print(arr); 
			
		// 첫번째 원소로 내림차순 (안정정렬됨)
		Arrays.sort(arr, (o1, o2)-> {
			return o2[0] - o1[0];			
		});
		
		print(arr);
		
		// 첫번째 원소로 오름차순, 두번째 원소로 오름차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];			
		});
		
		print(arr);
		
		// 첫번째 원소로 오름차순, 두번째 원소로 내림차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o1[0] == o2[0]) {
				return o2[1] - o1[1];
			}
			return o1[0] - o2[0];			
		});
		
		print(arr);
		
		// 두번째 원소로 오름차순, 첫번째 원소로 오름차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o1[1] == o2[1]) {
				return o1[0] - o2[0];
			}
			return o1[1] - o2[1];			
		});
		
		print(arr);
		
		// 두번째 원소로 오름차순, 첫번째 원소로 내림차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o1[1] == o2[1]) {
				return o2[0] - o1[0];
			}
			return o1[1] - o2[1];			
		});
		
		print(arr);
		
		// 첫번째 원소로 내림차순, 두번째 원소로 오름차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o2[0] == o1[0]) {
				return o1[1] - o2[1];
			}
			return o2[0] - o1[0];			
		});
		
		print(arr);
		
		// 첫번째 원소로 내림차순, 두번째 원소로 내림차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o2[0] == o1[0]) {
				return o2[1] - o1[1];
			}
			return o2[0] - o1[0];			
		});
		
		print(arr);
		
		// 두번째 원소로 내림차순, 첫번째 원소로 오름차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o2[1] == o1[1]) {
				return o1[0] - o2[0];
			}
			return o2[1] - o1[1];			
		});
		
		print(arr);
		
		// 두번째 원소로 내림차순, 첫번째 원소로 내림차순
		Arrays.sort(arr, (o1, o2)-> {
			if (o2[1] == o1[1]) {
				return o2[0] - o1[0];
			}
			return o2[1] - o1[1];			
		});
		
		print(arr);
		
			
		
	}
	
}


