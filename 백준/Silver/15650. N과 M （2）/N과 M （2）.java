import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer> list = new ArrayList<Integer>();
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//시작지점 1로 시작
		DFS(1);
		System.out.println(sb);
	}
	public static void DFS(int start) {
		//출력해야되는 길이면 return
		if(list.size()==M) {
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
			return;
		} else {
			for (int i = start; i <= N; i++) {
				//추가
				list.add(i);
				//시작지점 다음 숫자로
				DFS(i+1);
				//삭제
				list.remove((Object)i);
			}
		}
	}
}