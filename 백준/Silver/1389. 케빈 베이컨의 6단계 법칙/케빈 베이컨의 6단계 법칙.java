import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] dis;
    public static void main(String[] args) throws IOException {
        //입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().strip());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dis = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dis[i], N);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().strip());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            dis[first][second] = 1;
            dis[second][first] = 1;
        }

        for (int middle = 1; middle <= N; middle++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if(start != end) {
                        dis[start][end] = Math.min(dis[start][end], dis[start][middle] + dis[middle][end]);
                    }
                }
            }
        }

        int kevin = Integer.MAX_VALUE;
        int idx = -1;

        loop:
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if(i!=j) {
                    sum += dis[i][j];
                    if(sum>=kevin) continue loop;
                }
            }
            kevin = sum;
            idx = i;
        }

        System.out.println(idx);
    }
}
