import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String key = br.readLine().split("[.]")[1];
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
		//keySet 넣고 정렬
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ").append(map.get(list.get(i)));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}