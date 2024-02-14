package prgs_외벽점검;

public class Solution3 {

	public static void main(String[] args) {

		int n1 = 12;
		int n2 = 12;

		int[] weak1 = { 1, 5, 6, 10 };
		int[] weak2 = { 1, 3, 4, 9, 10 };

		int[] dist1 = { 1, 2, 3, 4 };
		int[] dist2 = { 3, 5, 7 };

		int[] weakExtend = new int[weak1.length * 2];

		// 확장 배열
		for (int i = 0; i < weakExtend.length; i++) {
			weakExtend[i] = weak1[i % weak1.length] + n1 * (i / weak1.length);
		}

		for (int i = 0; i < weakExtend.length; i++) {
			System.out.print(weakExtend[i] + " ");
		}
		System.out.println();
		System.out.println("-------------");

		

		int answer = -1;

		

		for (int start = 0; start < weak1.length; start++) {
			int idx = start;
			int cnt = 0;
			int i = start;
			System.out.println("start=" + start);
			
			out: for (int d = dist1.length - 1; d >= 0; d--) {
				System.out.println("d=" + dist1[d]);

				cnt++;

//				System.out.println("cnt=" + cnt);
//			int distSum = 0;
//			for (int i=idx; i<idx+weak1.length; i++) {
//				
//				
//				if (idx == weak1.length) {
//					System.out.println("cnt=" + cnt);
//					break out;
//				}
//				
//				if (weakExtend[i] > weakExtend[idx] + dist1[d]) {
//					idx = i - 1;
//					System.out.println(weakExtend[idx] + dist1[d] + ", idx=" + idx);
//					break;
//				}
//				
//			}

				
				
				while (weakExtend[i+1] <= weakExtend[idx] + dist1[d]) {
					i++;

					System.out.println(weakExtend[idx] + dist1[d] + ", idx=" + idx + ", i=" + i);

					if (idx == start + weak1.length-1) {
						System.out.println("cnt=" + cnt);
						break out;
					}

				}

				idx = i;
				System.out.println("i=" + i + ", idx=" + idx);
			}
		}

	}
}
