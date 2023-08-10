import java.io.*;
import java.util.*;

public class Solution {
	public static int N,answer=Integer.MAX_VALUE;
	//시너지배열
	public static int[][] arr;
	//사용 여부 판단 배열
	public static int[] check;
    public static void DFS(int L) {
    	if(L==N) {
    		//체크 개수 변수
    		int tmp = 0;
    		for (int i = 0; i < N; i++) {
    			//체크한것만 카운팅
    			if(check[i] == 1) tmp ++;
    			//넘어가면 굳이 더 할 필요 없음
    			if(tmp>N/2) break;
			}
    		//주어진 재료 반 개가 선택되었다면
    		if(tmp==N/2) {
    			//음식A 재료
    			List<Integer> list1 = new ArrayList<>();
    			//음식B 재료
    			List<Integer> list2 = new ArrayList<>();
    			for (int i = 0; i < N; i++) {
        			if(check[i] == 1) {
        				list1.add(i);
        			} else {
        				list2.add(i);
        			}
    			}
    			
    			int sum1 = 0;
    			int sum2 = 0;
    			
    			for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(list1.contains(i) && list1.contains(j)) sum1+=arr[i][j];
						if(list2.contains(j) && list2.contains(i)) sum2+=arr[i][j];
					}
				}
    			//절대값 계산 후 최소값으로 정답 변경
    			answer = Math.min(answer, Math.abs(sum1-sum2));
    		}
        	return;
        } else {
        	//현재L번쨰 재료 A에 사용
			check[L] = 1;
			DFS(L+1);	
        	//현재L번쨰 재료 B에 사용
			check[L] = 0;
			DFS(L+1);	
		}
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			check = new int[N];
			arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				} 
			}
			DFS(0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}