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
		//케이스
		int n = Integer.parseInt(br.readLine());
		//회사리스트 생성
		Map<String, Boolean> company = new HashMap<String, Boolean>();
		List<String> names = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//이름 저장
			String name = st.nextToken();
			//enter일 경우 추가, 아닐 경우 제거
			if(st.nextToken().equals("enter")) company.put(name, true);
			else company.remove(name);
		}
		//내림차순
		for (String name : company.keySet()) {
			names.add(name);
		}
		Collections.sort(names, Collections.reverseOrder());
		//출력
		for (String name : names) {
			System.out.println(name);
		}
	}
}