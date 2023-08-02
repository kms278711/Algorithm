import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//테스트케이스
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int answer = 1;
			int n = Integer.parseInt(br.readLine());
			//key : 카테고리, value : 옷 이름들
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String category = st.nextToken(); 
				//있으면 value에 추가
				if(map.containsKey(category)) {
					List<String> tmp = map.get(category);
					tmp.add(name);
					map.put(category, tmp);
				//없으면 새로 생성해서 추가
				} else {
					List<String> tmp = new ArrayList<>();
					tmp.add(name);
					map.put(category, tmp);
				}
			}
			// 각 옷들 사이즈 + 1(안입는경우)
			for (String key : map.keySet()) {
				int tmp = map.get(key).size() + 1;
				//입을 수 있는 조합 곱셈
				answer *= tmp;
			}
			//맨몸인 경우 뺴기
			sb.append(answer-1).append("\n");
		}
		System.out.println(sb);
	}
}