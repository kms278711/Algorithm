import java.io.*;
import java.util.*;

public class Main {
	static int N, answer=Integer.MAX_VALUE;
	static int[][] arr;
	
	static void DFS(List<Integer> list, int pre, int sum) {
		//이미나온 최적해보다 작으면 리턴
		if(sum>answer) return;
		//N개 탐색했으면
		if(list.size() == N) {
			//마지막 간 곳과 처음 간곳을 연결하는 길이 있다면
			if(arr[pre][list.get(0)] != 0) {
				//정답 갱신
				answer = Math.min(answer, sum+arr[list.get(list.size()-1)][list.get(0)]);
			}
		} else {
			for (int i = 0; i < N; i++) {
				//이미 포함되어 있는지 확인
				if(!list.contains(i)) {
					//해당 i와 연결되는 길이 있다면
					if(arr[pre][i]!=0) {
						//리스트에 추가하고
						list.add(i);
						//다음 분기로 넘기기
						DFS(list, i, sum + arr[pre][i]);
						//리스트에서 제거하고 반복
						list.remove((Object)i);
					}
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			} 
		}
		List<Integer> list = new ArrayList<>();
		//어디에서 시작하던 상관없으니 0부터
		list.add(0);
		DFS(list,0,0);
		System.out.println(answer);
	} 
}
