import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static List<Integer> list = new ArrayList<>();
	public static int[] check;
	public static StringBuilder sb = new StringBuilder();
	public static void DFS(int L) {
		if(L == M) {
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
				sb.append(" ");
			}
			sb.append("\n");
			return;
		} else {
			for (int i = 1; i <= N; i++) {
				if(check[i] == 1) continue;
				list.add(i);
				check[i] = 1;
				DFS(L+1);
				list.remove(list.indexOf(i));
				check[i] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		check = new int[N+1];
		DFS(0);
		System.out.print(sb);
	}
}