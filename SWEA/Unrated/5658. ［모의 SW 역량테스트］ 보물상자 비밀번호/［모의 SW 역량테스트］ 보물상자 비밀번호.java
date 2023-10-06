import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			//출력설정, 입력 및 초기화
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String[] arr = br.readLine().split("");
			//중복제거를 위해 set 사용
			Set<Integer> set = new HashSet<>();
			// 총 N/4바퀴 돌려야함.
			for (int rp = 0; rp < N/4; rp++) {
				//시작지점
				for (int j = 0; j < N; j = j + (N/4)) {
					//16진수 생성할 문자열
					String tmp = "";
					// 각 시작점부터 N/4만큼 반복하여 문자열에 붙이기
					for (int l = 0; l < N/4; l++) {
						// 배열 크기보다 클경우 나머지 연산 이용하기
						tmp += arr[(j+rp+l)%N];
					}
					//16진수 10진수로 변환
					set.add(Integer.parseInt(tmp, 16));
				}
			}
			// 정렬을 위해 list에 set 담기
			List<Integer> list = new ArrayList<>(set);
			// 오름차순 정렬
			Collections.sort(list);
			// K번째로 큰 수 => 오름차순 기준 (총 사이즈 - K) 번째 수
			sb.append(list.get(list.size() - K)).append("\n");
		}
		System.out.println(sb);
	}
	
}
