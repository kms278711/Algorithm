import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] check = new int[31];
		for (int i = 0; i < 28; i++) {
			st = new StringTokenizer(br.readLine());
			check[Integer.parseInt(st.nextToken())] = 1;
		}
		int cnt = 0;
		for (int i = 1; i < check.length; i++) {
			if(check[i]==0) {
				System.out.println(i);
				cnt++;
			}
			if(cnt == 2) break;
		}
	}
}
