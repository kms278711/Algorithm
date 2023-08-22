import java.util.*;
import java.io.*;

public class Solution {
	private static int[] parents;
	private static int n,m;
	
	//부모 찾기
    private static int find(int a) {
    	//자기자신(최상단)이라면 그대로 리턴
    	if(parents[a] == a) return a;
    	//아니라면 해당 번호의 부모의 부모를 구함
    	return parents[a] = find(parents[a]);
    }
    
    //합치기
    private static boolean union(int a, int b) {
    	//부모 찾기
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	//부모가 같다면 같은 집합에 있음. 합집합 일어나지 않음
    	if(aRoot == bRoot) return false;
    	//다르다면 합치기. 부모 a부모를 b부모로 넣기
    	parents[bRoot] = aRoot;
    	return true;
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parents = new int[n];
			//자기자신(서로소)으로 초기화
			for (int j = 0; j < n; j++) {
				parents[j] = j;
			}
			
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("1")) {
					//부모가 같다면 같은 집합
					if(find(Integer.parseInt(st.nextToken())-1) == find(Integer.parseInt(st.nextToken())-1)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				// 합집합 수행
				} else {
					union(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
				}
			}
			sb.append("\n");
		}
    	System.out.println(sb);
	}
}