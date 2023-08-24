import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//정답
		int answer = Integer.MIN_VALUE;
		//접시의수
		int N = Integer.parseInt(st.nextToken());
		//초밥의 가짓수
		int d = Integer.parseInt(st.nextToken());
		//연속해서 먹는 접시 수
		int k = Integer.parseInt(st.nextToken());
		//쿠폰번호
		int c = Integer.parseInt(st.nextToken());
		//회전 초밥 벨트
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//중복제거를 위해 set선언
		Map<Integer, Integer> map= new HashMap<>();
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		//lt가 제자리에 돌아올때까지
		int cnt = 0;
		for (int lt = 0; lt < N+k-1; lt++, cnt++) {
			if(cnt>=k) {
				map.put(list.get(0), map.get(list.get(0)) -1);
				if(map.get(list.get(0))==0) {
					set.remove(list.get(0));
				}
				list.remove(0);
			}
			list.add(arr[cnt%N]);
			set.add(arr[cnt%N]);
			map.put(arr[cnt%N], map.getOrDefault(arr[cnt%N], 0) + 1);
			//쿠폰 초밥 추가
			if(!map.containsKey(c) || map.get(c) == 0) {
				//개수 따져서 답 갱신
				answer = Math.max(set.size()+1, answer);
			} else {
				answer = Math.max(set.size(), answer);
			}
		}
		System.out.println(answer);
	}
}
