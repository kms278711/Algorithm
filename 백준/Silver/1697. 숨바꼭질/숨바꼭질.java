import java.io.*;
import java.util.*;

/**
 * 위치 클래스
 * 현재 장소와 소요 시간을 담음
 */
class Position{
	public int pos, time;
	public Position(int pos, int time) {
		this.pos = pos;
		this.time = time;
	}
}
public class Main {
	//각각 위치
	private static int N, K;
	/**
	 * 위치 찾기함수
	 * @return 최소 소요시간
	 */
	private static int BFS() {
		//큐 선언
		Queue<Position> queue = new ArrayDeque<>(); 
		boolean[] checked = new boolean[100001];
		//처음 위치 넣어줌
		queue.offer(new Position(N, 0));
		while(!queue.isEmpty()) {
			//현재값으로 하나를 poll
			Position cur = queue.poll();
			checked[cur.pos] = true;
			//현재 위치가 K와 같으면 리턴
			if(cur.pos == K) return cur.time;
			//순간이동
			if(cur.pos < K) {
				if(cur.pos*2 <= 100000 && !checked[cur.pos*2]) {
					queue.offer(new Position(cur.pos * 2, cur.time+1));
				}
				//한칸앞으로
				if(cur.pos+1 <= 100000 && !checked[cur.pos+1])
					queue.offer(new Position(cur.pos + 1, cur.time+1));
			}
			//한칸뒤로
			if(cur.pos-1 >= 0)
				if(!checked[cur.pos-1])
					queue.offer(new Position(cur.pos - 1, cur.time+1));
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(BFS());
	}
}