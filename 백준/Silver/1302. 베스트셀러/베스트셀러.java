import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//책을 담은 map
		Map<String, Integer> books = new HashMap<>();
		//정답들을 담을 리스트
		List<String> list = new ArrayList<String>();
		//책넣기(카운팅)
		for (int i = 0; i < n; i++) {
			String title = br.readLine();
			books.put(title, books.getOrDefault(title, 0)+1);
		}
		//최대개수 찾기
		int max = Integer.MIN_VALUE;
		for (String title : books.keySet()) {
			if(books.get(title)>max) {
				list = new ArrayList<String>();
				max = books.get(title);
				list.add(title);
			} else if (books.get(title)==max) {
				list.add(title);
			}
		}
		//오름차순 정렬
		Collections.sort(list);
		System.out.println(list.get(0));
	}
}