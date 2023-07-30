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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<Integer, List<String>> member = new HashMap<>();
		//hashMap에 집어넣기
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			if(member.containsKey(age)) {	
				member.get(age).add(st.nextToken());
			} else {
				List<String> list = new ArrayList<>();
				list.add(st.nextToken());
				member.put(age, list);
			}
		}
		//keySet은 정렬이 불가능하기때문에 리스트로 값 옮기기
		List<Integer> keySet = new ArrayList<>();
		for (Integer age : member.keySet()) {
			keySet.add(age);
		}
		//정렬
		Collections.sort(keySet);
		//나이순으로 출력하고, 그다음 들어온 순서대로 출력
		for (Integer age : keySet) {
			for (String m: member.get(age)) {
				System.out.println(age + " " + m);
			}
		}
	}
}