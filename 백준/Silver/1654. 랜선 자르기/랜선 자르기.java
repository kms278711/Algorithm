import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		List<Integer> lines = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			lines.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(lines);
		
		long start = 1;
		long end = lines.get(K-1);
		while(start<=end) {
			long mid = (start+end)/2;
			long cnt = 0;
			for (int i = 0; i < K; i++) {
				cnt += lines.get(i)/mid;
			}			
			if(cnt < N) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(end);
	}
}
