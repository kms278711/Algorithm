import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int answer = 1;
			int n = Integer.parseInt(br.readLine());
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String category = st.nextToken(); 
				if(map.containsKey(category)) {
					List<String> tmp = map.get(category);
					tmp.add(name);
					map.put(category, tmp);
				} else {
					List<String> tmp = new ArrayList<>();
					tmp.add(name);
					map.put(category, tmp);
				}
			}
			for (String key : map.keySet()) {
				int tmp = map.get(key).size() + 1;
				answer *= tmp;
			}
			sb.append(answer-1).append("\n");
		}
		System.out.println(sb);
	}
}