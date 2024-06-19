package boj_1062_가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_Set {
	
	static Map<Character, Boolean> canTeach;
	static Character[] middles;
	static int leng;
	static int selectCnt;
	static String[] words;
	static int answer;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			words[i] = tmp.substring(4, tmp.length()-4);
		}
		
		answer = 0;
		
		if (K >= 5) {
			char[] fix = {'a', 'n', 't', 'i', 'c'};
			Set<Character> cSet = new HashSet<>();
			canTeach = new HashMap<>();
			
			for (String s : words) {
				for (char c : s.toCharArray()) {
					canTeach.put(c, false);
					cSet.add(c);
				}
			}
			
			for (char c : fix) {
				canTeach.put(c, true);
				cSet.remove(c);
			}
			
			middles = cSet.toArray(new Character[0]);
			leng = middles.length;
			selectCnt = K - 5;
			
			if (selectCnt > leng) {
				answer = words.length;
			} else comb(0,0);
			
		}
		
		System.out.println(answer);
		
		br.close();
	}
	
	private static void comb(int idx, int cnt) {
		if (cnt >= selectCnt) {
			answer = Math.max(answer, check());
			return;
		}
		
		if (idx >= leng) return;
		
		canTeach.put(middles[idx], true);
		comb(idx+1, cnt+1);
		canTeach.put(middles[idx], false);
		comb(idx+1, cnt);
		
	}

	private static int check() {
		int result = 0;
		
		here:for (String word : words) {
			for (char c : word.toCharArray()) {
				if (!canTeach.get(c)) continue here;
			}
			result++;
		}
		
		return result;
	}
}