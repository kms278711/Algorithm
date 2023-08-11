import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		//오름차순으로 정렬
		Collections.sort(list);
		for (int i = 0; i < N; i++) {
			//먹을 수 있다면 길이 증가
			if(L>=list.get(i)) {
				L++;
			//못먹으면 뒤에 더 비교할 이유 없음
			} else {
				break;
			}
		}
		System.out.println(L);
	}
}