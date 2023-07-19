import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int[] money = {500, 100, 50, 10, 5, 1};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cur = 1000 - Integer.parseInt(st.nextToken());
		int idx = 0;
		int answer = 0;
		while(cur!=0) {
			if(cur >= money[idx]) {
				answer += cur/money[idx];
				cur = cur%money[idx];
			}
			idx++;
		}
		System.out.println(answer);
	}
}