import java.io.*;
import java.util.*;

public class Main {
    private static int N, L, R, answer;
    private static int[][] map;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static boolean[][] visited;
    private static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().strip());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().strip());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 구역 구하기
        while(true) {
            isEnd = true;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        BFS(i, j);
                    }
                }
            }
            if(isEnd) break;
            answer++;
        }
        System.out.println(answer);
    }

    private static void BFS(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> area = new ArrayList<>();
        int sum = 0;
        int cnt = 0;

        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        area.add(new int[] {x,y});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curVal = map[cur[0]][cur[1]];
            cnt++;
            sum += curVal;
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if(!isIn(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                int diff = Math.abs(curVal-map[nx][ny]);
                if(diff >= L && diff <= R) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    area.add(new int[] {nx,ny});
                }
            }
        }

        if(cnt != 1) {
            isEnd = false;
            for(int[] a : area) {
                map[a[0]][a[1]] = sum/cnt;
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x>=0 && y>=0 && x<N && y<N;
    }
}
