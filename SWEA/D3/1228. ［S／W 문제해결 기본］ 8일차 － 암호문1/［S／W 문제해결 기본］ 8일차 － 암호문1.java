import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	public static List<Integer> solution(int N, List<Integer> list, StringTokenizer st) {
		for (int i = 0; i < N; i++) {
			st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			List<Integer> addList = new ArrayList<>();
			for (int j = 0; j < y; j++) {
				addList.add(Integer.parseInt(st.nextToken()));
			}		
			list.addAll(x, addList);
		}
		return list.subList(0, 10);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int T = 1; T <= 10; T++) {
			sb.append("#").append(T).append(" ");
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (Integer num : solution(N, list, st)) {
				sb.append(num).append(" ");
			}	
			sb.append("\n");
		}
		System.out.println(sb);
	}
}