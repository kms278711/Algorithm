import java.util.*;
import java.io.*;

public class Solution {
	/**
	 * 영역 클래스 
	 * x좌표, 시작 y좌표, 끝y좌표, 나올 수 있는 최대값
	 */
	static class Area{
		int x, startY, endY, max;
		public Area(int x, int startY, int endY, int max) {
			super();
			this.x = x;
			this.startY = startY;
			this.endY = endY;
			this.max = max;
		}
		@Override
		public String toString() {
			return "Area [x=" + x + ", startY=" + startY + ", endY=" + endY + ", max=" + max + "]";
		}
		
	}
	
	private static int N, M, C, max=Integer.MIN_VALUE;
	private static int[][] arr;

	//주어진 영역 최대값 찾는 함수(해당영역 x좌표, 시작점, 해당 분기의 가능한 꿀 획득량, 분기, 현재까지 선택된 꿀양)
	private static void findMax(int x, int start, int value, int cnt, int sum) {
		if(cnt == M) {
			max = Math.max(max, value);
			return;
		} else {
			//더 담을 수 있다면 추가해서 진행
			if(sum+arr[x][start+cnt] <= C) {
				findMax(x, start, value+(arr[x][start+cnt]*arr[x][start+cnt]), cnt+1, sum+arr[x][start+cnt]);
			}
			//추가안하고 진행
			findMax(x, start, value, cnt+1, sum);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			//입력 및 초기화
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			//영역별로 담을 리스트
			List<Area> list = new ArrayList<>();
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					//영역밖으로 나가면 continue
					if(k>N-M) continue;
					//초기화
					max = Integer.MIN_VALUE;
					//최대수확량 찾기
					findMax(j,k,0,0,0);
					//추가
					list.add(new Area(j, k, k+M-1, max));
				}
			}
			
			//꿀수확량 내림차순으로 정렬
			Collections.sort(list, new Comparator<Area>() {
				@Override
				public int compare(Area o1, Area o2) {
					return o2.max-o1.max;
				}
			});
			
			//제일 큰 수확량 뽑기
			int answer = list.get(0).max;
			//두번쨰로 큰 수확량 뽑기
			//제일 큰 수확량과 겹치지 않는 영역 체크
			for (int j = 1; j < list.size(); j++) {
				if(list.get(j).x != list.get(0).x || ((list.get(j).startY < list.get(0).startY && list.get(j).endY < list.get(0).startY)||
						(list.get(j).startY > list.get(0).endY && list.get(j).endY > list.get(0).endY)))
				{
					answer += list.get(j).max;
					break;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
