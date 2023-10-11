import java.util.*;
import java.io.*;

public class Solution {
	//배열길이, 최소 시간
	static int N, answer;
	//맵
	static int[][] map;
	//사람들 좌표(좌표, 해당 사람이 이용한 계단 번호)
	static List<int[][]> people;
	//각 참가자와 계단까지의 거리
	static int[] dis;
	//계단들(각 계단은 좌표, 대기자리스트, 움직이는 사람들리스트(움직이는 사람번호, 계단 진행상황)로 이루어져 있다.)
	static Stair[] stair = new Stair[2];
	//사람 별로 끝남 여부
	static boolean[] isEnd;
	
	//계단 클래스
	//좌표, 대기자리스트, 움직이는 사람들리스트(움직이는 사람번호, 계단 진행상황)
	static class Stair {
		int[] point;
		List<Integer> wait;
		List<int[]> move;
		
		public Stair(int[] point, List<Integer> wait, List<int[]> move) {
			super();
			this.point = point;
			this.wait = wait;
			this.move = move;
		}
	}
	
	//사람마다 계단 세팅
	private static void setStair(int cnt) {
		if(cnt==dis.length) {
			//정답 갱신
			answer = Integer.min(getTime(), answer);
		} else {
			//cnt번째 사람
			int[] cur = people.get(cnt)[0];
			//계단 수만큼 반복
			for (int i = 0; i < 2; i++) {
				//i번째 계단으로 설정
				people.set(cnt, new int[][] {{cur[0], cur[1]},{i}});
				//참가자와 현재 for문에서 선택된 계단 사이 거리 계산해서 넣기
				dis[cnt] = Math.abs(stair[i].point[0] - cur[0]) + Math.abs(stair[i].point[1] - cur[1]);
				//다음분기로 진행
				setStair(cnt+1);
			}
		}
	}
	
	private static int getTime() {
		//초기화
		isEnd = new boolean[people.size()];
		
		int time = 0;
		
		loop:
		while(true) {
			//시간진행
			time++;
			//계단 검사
			for (int i = 0; i < 2; i++) {
				//현재 계단
				Stair cur = stair[i];
				//계단을 진행하는 사람이 있다면
				if(cur.move.size() != 0) {
					//remove를 위해 뒤부터 진행
					for (int j = cur.move.size()-1; j >= 0; j--) {
						//진행한 계단 크기 증가
						cur.move.get(j)[1]++;
						//끝까지 계단을 오르면 isEnd에 표시하고 remove
						if(cur.move.get(j)[1] == map[cur.point[0]][cur.point[1]]+1) {
							isEnd[cur.move.get(j)[0]] = true;
							cur.move.remove(j);
						}
					}
				}
				
				//계단 대기자가 있다면
				if(cur.wait.size() != 0) {
					//계단이 들어갈 수 있는 상황이라면
					if(cur.move.size() != 3) {
						//remove를 위해 뒤부터 진행
						for (int j = cur.wait.size()-1; j >= 0; j--) {
							//정원이 차면 나오기
							if(cur.move.size() == 3) break;
							//대기자 한명 추가
							cur.move.add(new int[] {cur.wait.get(j), 1});
							//대기목록에서 제거
							cur.wait.remove(j);
						}
					}
				}
			}
			
			//거리가 현재 시간과 같은 사람이 있는지 체크
			for (int i = 0; i < dis.length; i++) {
				if(time == dis[i]) {
					//해당 사람이 가야될 계단번호
					int stairNum = people.get(i)[1][0];
					Stair cur = stair[stairNum];
					//해당 계단 대기자 목록에 추가
					cur.wait.add(i);
				}
			}
			
			//다 계단을 올라갔는지 확인
			for (int i = 0; i < isEnd.length; i++) {
				if(!isEnd[i]) continue loop;
			}
			break;
		}
		return time;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			people = new ArrayList<>();
			
			//계단 번호
			int stairCnt = 0;
			map = new int[N][N];
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					//사람이면 사람리스트에 추가
					if(map[j][k] == 1) people.add(new int[][] {{j, k},{0}});
					//계단이라면 계단배열에 추가
					if(map[j][k] >= 2) {
						stair[stairCnt] = new Stair(new int[] {j, k}, new ArrayList<>(), new ArrayList<>());
						//다음 계단으로
						stairCnt++;
					}	
				}
			}
			//사람 수만큼 거리 배열 초기화
			dis = new int[people.size()];
			
			//계단 세팅 후 시간 계산 시작
			setStair(0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}