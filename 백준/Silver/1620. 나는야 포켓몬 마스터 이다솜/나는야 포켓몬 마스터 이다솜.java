import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<Integer, String> pokemon = new HashMap<>();
		Map<String, Integer> pokemon2 = new HashMap<>();
		
		for (int i = 1; i <= n; i++) {
			String name = br.readLine();
			pokemon.put(i , name);
			pokemon2.put(name, i);
		}
		
		for (int i = 0; i < m; i++) {
			String input = br.readLine();
			try {
				int num = Integer.parseInt(input);
				sb.append(pokemon.get(num));
				sb.append("\n");
			} catch(NumberFormatException e) {
				sb.append(pokemon2.get(input));
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}