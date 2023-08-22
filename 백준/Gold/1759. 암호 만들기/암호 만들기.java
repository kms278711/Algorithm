import java.io.*;
import java.util.*;

public class Main {
	private static int L,C;
	private static String[] arr;
	private static StringBuilder sb = new StringBuilder();
	private static void find(int cnt, String cur) {
		if(cnt == C) {
			if(cur.length() == L && (cur.contains("a")||cur.contains("e")||cur.contains("i")||cur.contains("o")||cur.contains("u"))&& cur.replaceAll("[aeiou]", "").length()>=2)
				sb.append(cur).append("\n");
		} else {
			find(cnt+1, cur+arr[cnt]);
			find(cnt+1, cur);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new String[C];
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken();
		}
		Arrays.sort(arr);
		find(0, "");
		System.out.println(sb);
	}
}
