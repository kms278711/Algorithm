import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		Map<Long, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		
		//키값들 저장 리스트(순서필요하기 때문)
		List<Long> keys = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			long num = Long.parseLong(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
			//키값들 들어온 순서대로 넣어주기
			if(!keys.contains(num)) keys.add(num);
		}
		//개수 내림차순 정렬
		List<Integer> values = new ArrayList<>(map.values());
		Collections.sort(values, Collections.reverseOrder());
		
		int size = map.keySet().size();
		int idx = 0;
		
		//key값들 다 탐색할 만큼만
		while(idx < size) {
			//반복할 정수
			long tmp = 0;
			//들어온 순서부터 탐색하여 키리스트에서 제거
			for (int i = 0; i < keys.size(); i++) {
				if(map.get(keys.get(i)).equals(values.get(idx))) {
					tmp = keys.get(i);
					keys.remove(i);
					break;
				}
			}
			//출력추가
			for (int i = 0; i < map.get(tmp); i++) {
				sb.append(tmp + " ");
			}
			idx++;
		}
		System.out.println(sb);
	}
}