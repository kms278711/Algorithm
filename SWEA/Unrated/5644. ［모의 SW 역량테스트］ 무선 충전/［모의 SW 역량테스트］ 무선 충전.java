import java.util.*;
import java.io.*;

public class Solution {
	//비콘 클래스
	//좌표, 전력, 연결범위
	static class BC {
		int[] point;
		int power, range;
		public BC(int[] point, int power, int range) {
			super();
			this.point = point;
			this.power = power;
			this.range = range;
		}
	}
	
	//움직인 횟수, 비콘 개수
	private static int M,R;
	//A움직임, B움직임
	private static int[] aMove, bMove;
	// 지도 기준 부동,상,우,하,좌
	private static int[] dx = {0, 0, 1, 0, -1};
	private static int[] dy = {0, -1, 0, 1, 0};
	//현재 a좌표, b좌표
	private static int[] aPos;
	private static int[] bPos;
	//비콘 정보 배열
	private static BC[] BCList;
	
	//1초 움직이기. 해당 idx방향으로 이동
	private static void move(int idx) {
		aPos[0] += dx[aMove[idx]];
		aPos[1] += dy[aMove[idx]];
		bPos[0] += dx[bMove[idx]];
		bPos[1] += dy[bMove[idx]];
	}
	
	//pos(위치)가 BCIdx에 해당하는 비콘에 연결되어 있는지 확인
	//연결되어있으면 해당 비콘 전력 리턴
	private static int check(int[] pos, int BCIdx) {
		BC cur = BCList[BCIdx];
		int dis = Math.abs(pos[0]-cur.point[0]) + Math.abs(pos[1]-cur.point[1]);
		if(dis <= cur.range) return cur.power;
		return 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			aMove = new int[M];
			bMove = new int[M];
			aPos = new int[2];
			bPos = new int[] {9,9};
			BCList = new BC[R];
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				aMove[j] = Integer.parseInt(st.nextToken()); 
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				bMove[j] = Integer.parseInt(st.nextToken()); 
			}
			
			for (int j = 0; j < R; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				BCList[j] = new BC(new int[] {x,y}, power, range);
			}
			
			//해당 시간 최대 전력
			int max = 0;
			//A의 비콘
			for (int a = 0; a < R; a++) {
				//B의 비콘
				for (int b = 0; b < R; b++) {
					//해당 경우에 나올 수 있는 전력 최대값
					int sum = 0;
					//A가 a번째 비콘에서 얻을 수 있는 전력
					int aSum = check(aPos, a);
					//B가 b번째 비콘에서 얻을 수 있는 전력
					int bSum = check(bPos, b);
					//비콘이 다르다면 그냥 더하기
					if(a!=b) {
						sum = aSum+bSum;
					//비콘이 같은 경우 최대값을 구해서 더하기
					//같은 경우 한쪽 비콘의 파워값을 따라가게 되어있음.
					} else {
						sum = Math.max(aSum, bSum);
					}
					//최대값 갱신
					max = Math.max(sum, max);
				}
			}
			//정답에 해당 시간 최대 전력 더해주기
			answer += max;
			
			//a
			for (int j = 0; j < M; j++) {
				//1초 이동
				move(j);
				
				//해당 시간 최대 전력 초기화
				max = 0;
				//A의 비콘
				for (int a = 0; a < R; a++) {
					//B의 비콘
					for (int b = 0; b < R; b++) {
						//해당 경우에 나올 수 있는 전력 최대값
						int sum = 0;
						//A가 a번째 비콘에서 얻을 수 있는 전력
						int aSum = check(aPos, a);
						//B가 b번째 비콘에서 얻을 수 있는 전력
						int bSum = check(bPos, b);
						//비콘이 다르다면 그냥 더하기
						if(a!=b) {
							sum = aSum+bSum;
						//비콘이 같은 경우 최대값을 구해서 더하기
						//같은 경우 한쪽 비콘의 파워값을 따라가게 되어있음.
						} else {
							sum = Math.max(aSum, bSum);
						}
						//최대값 갱신
						max = Math.max(sum, max);
					}
				}
				//정답에 해당 시간 최대 전력 더해주기
				answer += max;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
