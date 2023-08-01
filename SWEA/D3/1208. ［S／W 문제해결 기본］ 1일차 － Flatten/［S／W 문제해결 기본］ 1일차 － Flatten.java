import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class Solution {
	public static StringBuilder sb = new StringBuilder();
	public static void solution(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			List<Integer> tmp = new ArrayList<>(list);
			Collections.sort(tmp);
			int minIdx = -1;
			int maxIdx = -1;
			for (int j = 0; j < 100; j++) {
				if(list.get(j) == tmp.get(0)) {
					minIdx = j;
				}	
				if(list.get(j) == tmp.get(tmp.size()-1)) {
					maxIdx = j;
				}	
			}
			list.set(minIdx, list.get(minIdx)+1);
			list.set(maxIdx, list.get(maxIdx)-1);
		}
		Collections.sort(list);
		sb.append(list.get(list.size()-1) - list.get(0));
		sb.append("\n");
	}	
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 1; i <= 10; i++) {
			sb.append("#").append(i).append(" ");
			int n = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 100.; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			solution(list, n);
		}
		System.out.println(sb);
	}
}