import java.util.*;
import java.io.*;

public class Solution {
	//union-find를 위한 부모배열
	static int[] parents;
	static int N;
	
	//부모배열 초기화
	private static void make() {
		parents = new int[N+1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	//부모 비교 후 다르면 합치기
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) parents[aRoot] = bRoot;
	}
	
	//부모 찾기
	private static int find(int a) {
		if(parents[a] == a) return a;
		//자기자신이 아니라면 재귀로 부모 찾아서 현재값의 부모로 넣기
		return parents[a] = find(parents[a]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//부모배열 생성
			make();
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				//관계 비교 후 합치기
				union(first, second);
			}
			
			int answer = 0;
			//여전히 자기자신인 애들 == 무리의 수
			for (int j = 1; j <= N; j++) {
				if(j == parents[j]) answer ++;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
