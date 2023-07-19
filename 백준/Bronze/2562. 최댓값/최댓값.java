import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int maxIdx = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			if(max < tmp) {
				max = tmp;
				maxIdx = i;
			}
		}
		System.out.println(max);
		System.out.println(maxIdx+1);
	}
}
