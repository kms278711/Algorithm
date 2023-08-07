import java.util.*;

//좌표 클래스
class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
    //체크 배열
	public static int[][] check;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        //체크 배열 생성
        check = new int[m][n];

        for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				//체크를 안한 곳이면서 0이 아니라면
				if(check[i][j] == 0 && picture[i][j] != 0) {
					//구역 + 1
					numberOfArea++;
					//구역 사이즈 받아오기
                    //(BFS시작)
					int tmp = BFS(i,j, picture[i][j], picture, m,n);
					if(maxSizeOfOneArea < tmp) {
						maxSizeOfOneArea = tmp;
					}
				}
			}
		}
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public static int BFS(int x, int y, int curValue, int[][] picture, int m, int n) {
    	Queue<Point> q = new LinkedList<Point>();
    	//구역 수
    	int cnt = 0;
    	//큐 초기 세팅
    	q.offer(new Point(x, y));
    	//상우하좌(시계방향)
    	int[] dx = {-1,0, 1, 0};
    	int[] dy = {0, 1, 0, -1};
    	while(!q.isEmpty()) {
            // 하나 뽑기
    		Point cur = q.poll();
            //이미 탐색한 곳이라면 continue(중복 제거)
    		if(check[cur.x][cur.y] == 1) continue; 
    		else check[cur.x][cur.y] = 1;
            //구역 카운팅
    		cnt++;
    		for (int k = 0; k < 4; k++) {
    			//다음좌표가 좌표 안에서 움직이는지 확인
    			if(cur.x+dx[k]>=0 && cur.x+dx[k]< m&& cur.y+dy[k]>=0 && cur.y+dy[k]<n) {
    				//체크안했고 같은 값이면 큐에 추가
    				if(check[cur.x+dx[k]][cur.y+dy[k]] == 0 && picture[cur.x+dx[k]][cur.y+dy[k]] == curValue) {
    					q.offer(new Point(cur.x+dx[k], cur.y+dy[k]));
    				}	
    			}
    		}    		
    	}
    	return cnt;
    }
}