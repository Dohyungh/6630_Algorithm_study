import java.io.*;
import java.util.*;

public class Main {
	static int N, K, ans;
	static Map<String, Integer> map;
	static Set<Character> charSet;
	static char[] charArr, sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputArr = br.readLine().split(" ");

		N = Integer.parseInt(inputArr[0]);
		K = Integer.parseInt(inputArr[1]);

		// 단어에 항상 'a', 'c', 'i', 'n' ,'t' 가 포함되어 있으므로 그 사이에 들어갈 수 있는 다른 알파벳의 개수는 K-5개이다.
		// 따라서 K를 K-5로 변경
		K -= 5;
		// ans, num 초기화
		ans = 0;
		int num = 0;

		// K가 0보다 작을 경우 학생들이 읽을 수 있는 단어는 없다.
		if (K < 0) {
			System.out.println(0);
			return;
		}

		map = new HashMap<String, Integer>();
		charSet = new HashSet<Character>(); // 모든 단어에 포함된 알파벳들을 HashSet의 형태로 저

		// 가르칠 K개의 글자를 HashMap의 key 값으로 입력 받는다.
		for (int i = 0; i < N; i++) {
			map.put(br.readLine(), 0);
		}

		int minLen = Integer.MAX_VALUE;

		for (String key : map.keySet()) {
			char[] charArr = key.toCharArray();
			Set<Character> tmpSet = new HashSet<Character>(); // 각 단어마다 포함된 알파벳들을 HashSet의 형태로 저장

			// "anta" 와 "tica"는 항상 포함되어 있으므로 제외
			for (int j = 4; j < charArr.length - 4; j++) {
				if (charArr[j] != 'a' && charArr[j] != 'c' && charArr[j] != 'i' && charArr[j] != 'n'
						&& charArr[j] != 't') {
					// charSet에 없으면 추가
					if (!charSet.contains(charArr[j])) {
						charSet.add(charArr[j]);
					}
					// tmpSet에 없으면 추가
					if (!tmpSet.contains(charArr[j])) {
						tmpSet.add(charArr[j]);
					}
				}
			}

			// 해당 단어에서 "anta"와 "tica" 사이에 들어가는 알파벳의 개수 len
			int len = tmpSet.size();

			// len = 0 이면 항상 읽을 수 있으므로 num+1
			if (len == 0) {
				num++;
			}
			// 그렇지 않으면 map의 value에 len 입력
			else {
				map.replace(key, len);
			}

			// len의 최소값 minLen
			minLen = Math.min(minLen, len);
		}

		// minLen이 K 보다 클 경우 읽을 수 있는 단어의 개수는 없음
		if (K < minLen) {
			System.out.println(0);
			return;
		}
		// K가 charSet의 크기보다 작거나 같을 경우 모든 단어를 읽을 수 있음
		else if (K >= charSet.size()) {
			System.out.println(N);
			return;
		}

		charArr = new char[charSet.size()];
		sel = new char[K];

		Iterator<Character> it = charSet.iterator();

		// charSet을 배열 형태로 저장
		for (int i = 0; i < charSet.size(); i++) {
			charArr[i] = it.next();
		}

		combination(0, 0);

		// 항상 읽을 수 있는 단어의 개수 num + ans의 최대값이 최대로 읽을 수 있는 단어의 개수
		System.out.println(num + ans);
	}

	// 알파벳 조합
	public static void combination(int idx, int sidx) {
		// K개 조합했으면 isPossible 실행 후 return
		if (sidx >= K) {
			isPossible();
			return;
		}

		// charSet의 크기에서 K개를 골라 조합한다.
		for (int i = idx; i <= charSet.size() - K + sidx; i++) {
			sel[sidx] = charArr[i];
			combination(i + 1, sidx + 1);
		}
	}

	public static void isPossible() {
		// 조합하여 읽을 수 있는 단어의 개수 num 초기화
		int num = 0;

		label1: for (String key : map.keySet()) {
			// len이 0일 경우는 앞에서 고려했으며, K보다 클 경우 단어를 읽을 수 없다.
			if (map.get(key) > 0 && map.get(key) <= K) {
				char[] tmpCharArr = key.toCharArray();

				label2: for (int i = 4; i < tmpCharArr.length - 4; i++) {
					if (tmpCharArr[i] != 'a' && tmpCharArr[i] != 'c' && tmpCharArr[i] != 'i' && tmpCharArr[i] != 'n'
							&& tmpCharArr[i] != 't') {
						for (int j = 0; j < K; j++) {
							// 단어를 탐색하여 조합에 속한 알파벳과 일치하는 것이 있으면 label2로 돌아가 해당 단어 계속 탐색
							if (tmpCharArr[i] == sel[j]) {
								continue label2;
							}
						}
						// 일치하지 않은 단어가 있으면 label1로 돌아가 다음 단어 탐색
						continue label1;
					}
				}
				// label2의 반복문이 모두 실행되었으면 단어를 읽을 수 있는 것이므로 num+1
				num++;
			}
		}

		// 최대값 비교 : 기존의 ans와 해당 조합에서의 num 비교
		ans = Math.max(ans, num);
	}
}
