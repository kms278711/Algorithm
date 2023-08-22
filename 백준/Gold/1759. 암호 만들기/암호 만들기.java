import java.io.*;
import java.util.*;

public class Main {
	//암호길이 ,문자의 종류
	private static int L,C;
	private static String[] arr;
	//출력을 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();
	private static void find(int cnt, String cur) {
		//끝까지 돌았을때
		if(cnt == C) {
			//현재 문자열 길이가 L인지, 모음을 포함하고 있는지, 모음을 제외하고 자음길이가 2이상인지
			if(cur.length() == L && (cur.contains("a")||cur.contains("e")||cur.contains("i")||cur.contains("o")||cur.contains("u"))&& cur.replaceAll("[aeiou]", "").length()>=2)
				sb.append(cur).append("\n");
		} else {
			//해당 문자열 사용
			find(cnt+1, cur+arr[cnt]);
			//문자열 사용x
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