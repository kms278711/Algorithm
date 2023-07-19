import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char[] binary =Integer.toBinaryString(n).toCharArray();
			for (int j = binary.length-1; j >= 0; j--) {
				if(binary[j] == '1') System.out.print(binary.length-j-1 + " "); 
			}
		}
	}

}
