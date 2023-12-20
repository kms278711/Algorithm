import java.io.*;
import java.util.*;

public class Main {
    //가로길이, 세로길이
    private static int N,M;
    //맵정보
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        //입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int x = 0; x < N; x++) {
            String[] tmp = br.readLine().split("");
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(tmp[y]);
            }
        }
        find();
    }

    private static void find() {
        //int[0] : 이동횟수
        //int[1] : 현재좌표 
        Queue<int[][]> queue = new ArrayDeque<>();
        queue.offer(new int[][] {{1},{0,0}});
        //방문배열
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        //우하좌상
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        while(!queue.isEmpty()) {
            int[][] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[1][0] + dx[dir];
                int ny = cur[1][1] + dy[dir];
                if(!isIn(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;
                //다음 갈 좌표가 도착좌표면 해당 칸까지의 이동횟수 출력
                if(nx == N-1 && ny == M-1) {
                    System.out.println(cur[0][0] + 1);
                    return;
                }
                queue.offer(new int[][] {{cur[0][0]+1}, {nx,ny}});
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x>=0 && y>=0 && x<N && y<M;
    }
}
