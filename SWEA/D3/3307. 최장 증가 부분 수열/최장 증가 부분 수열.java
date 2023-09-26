import java.util.*;
import java.io.*;

public class Solution {
	private static int N;
	
	//DP이용1
	private static int findMaxLen1(int[] arr) {
		int[] LIS = new int[N];
		int max = Integer.MIN_VALUE;
		
		//i를 부분수열의 끝으로 했을 경우
		for (int i = 0; i < N; i++) {
			//기본값 1
			LIS[i] = 1;
			//처음부터 현재수(i)까지 비교
			for (int j = 0; j < i; j++) {
				//값이 현재수보다 작고, 
				//현재수를 끝으로 하는 부분수열의 길이보다 (j번째 수를 끝으로 하는 거 + 현재수) 가 더 길경우 갱신
				if(arr[j]<arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			//정답 최대길이로 갱신
			max = Math.max(LIS[i], max);
		}
		return max;
	}
	
	//DP이용2 - 이분탐색
	private static int findMaxLen2(int[] arr) {
		//길이가 몇일지 모르기에 List로 정의
		//여기서 LIS는 해당 위치에 올 수 있는 가장 작은 값
		List<Integer> LIS = new ArrayList<>();
		//초기값 세팅
		LIS.add(arr[0]);
		//현재 수보다 큰 수가 없다면 List에 추가하기 위한 조건
		boolean flag = false;
		//첫번째 수부터 진행
		for (int i = 1; i < N; i++) {
			//리스트 사이즈만큼 비교
			for (int j = 0; j < LIS.size(); j++) {
				//리스트의 j번째 수가 현재 수보다 크다면
				if(LIS.get(j) > arr[i]) {
					//j번째를 현재 수(올 수 있는 가장 작은값)로 바꾼다.
					LIS.set(j, arr[i]);
					//리스트에 추가하지 말라고 표시
					flag = true;
					break;
				}
			}
			//현재수보다 큰 수가 없다면 리스트에 추가
			if(!flag) LIS.add(arr[i]);
			//초기화
			flag = false;
		}
		return LIS.size();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			sb.append("#").append(i).append(" ");
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			sb.append(findMaxLen2(arr)).append("\n");
		}
		System.out.println(sb);
	}
}