import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int num = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			if(L==0) break;
			sb.append("Case ");
			sb.append(num++);
			sb.append(": ");
			if(V%P<=L) sb.append(V/P*L + V%P);
			else sb.append(V/P*L + L);
			sb.append("\n");
		}
		System.out.print(sb);
	}
}