import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		//정답리스트
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			//포함되어있으면 추가안함
			if(!list.contains(num)) list.add(num);
		}
		//오름차순정렬
		Collections.sort(list);
		for (Integer answer: list) {
			System.out.print(answer + " ");
		}
	}
}