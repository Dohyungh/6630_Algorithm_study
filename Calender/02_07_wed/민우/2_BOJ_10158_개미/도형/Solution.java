package BOJ._10158_개미;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	// 시간초과 시치
//	public static int dir(int dx, int dy, int p, int q) {
//		if (dx>p && dy>q) {
//			return 1;
//		}
//		if (dx<p && dy>q) {
//			return 2;
//		}
//		if (dx>p && dy<q) {
//			return 3;
//		}
//		if (dx<p && dy<q) {
//			return 4;
//		}
//		else return -1;
//	}
	
	public static void main(String[] args) throws Exception {
		//한무 대칭 시키자
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		int p = Integer.parseInt(st2.nextToken());
		int q = Integer.parseInt(st2.nextToken());
		int t = Integer.parseInt(br.readLine());
		
 		
		int dir = 1; // 우상을 1, 좌상을 2, 우하를 3, 좌하를 4

		// 현재위치 p,q 업데이트 (부딪힐 벽 선택) 
		// 목적지 좌표를 부딪힐 벽에 대칭시켜 업데이트
		// w-p 와 h-q 비교. 더 작은 쪽에 부딪힘.
		
		int dx = p+t;
		int dy = q+t;
		
		int xgap = 0;
		int ygap = 0;
		
		
		int pt = 0;
		int big = Math.max(w, h);
		int small = Math.min(w,h);
		if (big%small==0) {
			pt = big*2;
		} else {
			pt = big*small*2;
		}
		
//		시뮬레이션 돌리면 짤 없이 시간초과
//		int px = p;
//		int py = q;
//		int pt = 0;
//		while (dir !=1 || px != p || py !=q||pt==0) {
//		
//			if (dir == 1) { // w h 우상
//				px++;
//				py++;
//				if (px == w && py!=h) {
//					dir = 2;
//				} else if (py == h && px !=w) {
//					dir = 3;
//				} else if (px==w && py==h) {
//					dir = 4;
//				}
//			}
//			else if (dir == 2) { // 0 h 좌상
//				px--;
//				py++;
//				if (px == 0 && py!=h) {
//					dir = 1;
//				} else if (py == h && px!=0) {
//					dir = 4;
//				} else if (px==0 && py==h){
//					dir = 3;
//				}
//			}
//			else if (dir == 3) { // w 0 우하
//				px++;
//				py--;
//				if (px == w && py!=0) {
//					dir = 4;
//				} else if (py == 0 && px !=w) {
//					dir = 1;
//				} else if (px==w && py==0) {
//					dir = 2;
//				}
//
//			}
//			else if (dir == 4) { // 0 0 좌하
//				px--;
//				py--;
//				if (px == 0 && py!=0) {
//					dir = 3;
//				} else if (py == 0 && px !=0) {
//					dir = 2;
//				} else if(px==0 && py==0) {
//					dir = 1;
//				} 
//
//			}
//			pt++;
//			
//		}
		t = t%pt;
//		System.out.println(pt);
		
		
		
		while (dx<0 || dx>w || dy<0 || dy>h) {
			if (dir == 1) { // w h 우상
				xgap = w-p;
				ygap = h-q;
				if (xgap < ygap) {
					p = w;
					q = q+xgap;
					dx = 2*w - dx;
					dir = 2;
				} else if (xgap > ygap) {
					q = h;
					p = p+ygap;
					dy = 2*h - dy;
					dir = 3;
				} else if (xgap == ygap) {
					dir = 4;
					p = w;
					q = h;
					dx = 2*w - dx;
					dy = 2*h - dy;
				}
					
				
			}
			else if (dir == 2) { // 0 h 좌상
				xgap = p-0;
				ygap = h-q;
				if (xgap < ygap) {
					p = 0;
					q = q+xgap;
					dx = -dx;
					dir = 1;
				} else if (xgap > ygap) {
					q = h;
					p = p-ygap;
					dy = 2*h - dy;
					dir = 4;
				} else if (xgap == ygap) {
					dir = 3;
					p = 0;
					q = h;
					dx = -dx;
					dy = 2*h - dy;
				}
			}
			else if (dir == 3) { // w 0 우하
				xgap = w-p;
				ygap = q-0;
				if (xgap < ygap) {
					p = w;
					q = q-xgap;
					dx = 2*w - dx;
					dir = 4;
				} else if (xgap > ygap) {
					q = 0;
					p = p+ygap;
					dy = -dy;
					dir = 1;
				} else if (xgap == ygap) {
					dir = 2;
					p = w;
					q = 0;
					dx = 2*w-dx;
					dy = -dy;
				}

			}
			else if (dir == 4) { // 0 0 좌하
				xgap = p-0;
				ygap = q-0;
				if (xgap < ygap) {
					p = 0;
					q = q-xgap;
					dx = -dx;
					dir = 3;
				} else if (xgap > ygap) {
					q = 0;
					p = p-ygap;
					dy = -dy;
					dir = 2;
				} else if (xgap == ygap) {
					dir = 1;
					p = 0;
					q = 0;
					dx = -dx;
					dy = -dy;
				}

			}
			
		}
		System.out.printf("%d %d",dx,dy);
		// 
		
		
		
		
	}
	
	
	

}
