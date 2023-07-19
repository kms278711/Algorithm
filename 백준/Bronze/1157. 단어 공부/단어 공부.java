import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		char[] arr = st.nextToken().toCharArray();
		int cnt = Integer.MIN_VALUE;
		HashMap<Character, Integer> map = new HashMap<>();
		char answer = '?';
		for (char c : arr) {
			char tmp = Character.toUpperCase(c);
			map.put(tmp, map.getOrDefault(tmp, 0)+1);
		}
		for (char c : map.keySet()) {
			if(map.get(c) > cnt) {
				cnt = map.get(c);
				answer = c;
			} else if(map.get(c) == cnt) {
				answer ='?';
			}
		}
		sb.append(answer);
		System.out.println(sb);
	}
}