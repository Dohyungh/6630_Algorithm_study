package boj_13502_단어퍼즐2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main_url {
	
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	static Trie trie;
	static char[][] alpha;
	static boolean[][] visited;
	static int answer;
	static final String DOWNLOAD_LINK = "https://d2gd6pc034wcta.cloudfront.net/data/1165.zip";
	
	public static void main(String[] args) throws IOException {
		
		URL url = new URL(DOWNLOAD_LINK);
        StringBuilder sb = new StringBuilder();

        InputStream in = new BufferedInputStream(url.openStream(), 1024 * 4);
        ZipInputStream stream = new ZipInputStream(in);
        byte[] buffer = new byte[1024 * 4];

        ZipEntry entry;
        while((entry = stream.getNextEntry()) != null) {
            int read;
            String data = "";
            while((read = stream.read(buffer, 0, 1024 * 4)) >= 0) {
                data = new String(buffer, 0, read);
                sb.append(data);
            }
        }
		
		StringTokenizer st = new StringTokenizer(sb.toString(), "\n");
		trie = new Trie();
		
		while(st.hasMoreTokens()) {
			trie.insert(st.nextToken());
		}

		stream.close();
        in.close();
		
		alpha = new char[5][5];
		visited = new boolean[5][5];
		answer = 0;
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
			char[] tmp = sc.nextLine().toCharArray();
			int idx = 0;
			for (int j = 0; j < 9; j+=2) {
				alpha[i][idx++] = tmp[j];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Node curr = trie.root;
				visited[i][j] = true;
				search(i, j, curr.child.getOrDefault(alpha[i][j], null));
				visited[i][j] = false;
			}
		}
		
		System.out.println(answer);
		
		sc.close();
	}
	
	private static void search(int r, int c, Node curr) {
		if (curr == null) return;
		
		if (curr.endOfWord) {
			curr.endOfWord = false;
			answer++;
		}
		
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			search(nr, nc, curr.child.getOrDefault(alpha[nr][nc], null));
			visited[nr][nc] = false;
		}
	}
}
