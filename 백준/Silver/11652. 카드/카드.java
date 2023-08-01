import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<Long, Integer> map = new HashMap<>();
		//map에 값 카운팅
		for (int i = 0; i < n; i++) {
			long input = Long.parseLong(br.readLine());
			map.put(input, map.getOrDefault(input, 0)+1);
		}
		//개수 비교 변수
		int max = Integer.MIN_VALUE;
		//저장할 key값
		long answer = Long.MAX_VALUE;
		for (long key : map.keySet()) {
			if(max < map.get(key)) {
				max = map.get(key);
				answer = key;
			} else if(max == map.get(key)) {
				answer = Math.min(answer, key);
			}
		}
		System.out.println(answer);
	}
}