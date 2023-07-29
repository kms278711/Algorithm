import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		//문자열
		List<String> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			arr.add(br.readLine());
		}
		
		for (int i = 0; i < m; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < arr.size(); j++) {
				if(arr.get(j).equals(tmp)) {
					answer ++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}