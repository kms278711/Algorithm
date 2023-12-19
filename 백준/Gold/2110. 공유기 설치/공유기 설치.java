import java.io.*;
import java.util.*;

public class Main {
    //집 개수, 공유기 개수
    private static int N, C;
    //집 좌표 정보
    private static int[] house;
    public static void main(String[] args) throws IOException {
        //입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().strip());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        //오름차순 정렬
        Arrays.sort(house);
        getDis();
    }

    private static void getDis() {
        //최대 거리
        int right = house[N-1]-house[0]+1;
        //최소 거리
        int left = 1;
        while(left<right) {
            int mid = (right+left)/2;
            //중앙값과 공유기 개수를 비교
            //C개보다 최대설치 개수가 작다면 최대거리 작게 갱신
            if(count(mid) < C) {
                right = mid;
            //더 설치가 가능하거나 C개만큼 설치가 가능하면 최소거리 크게 갱신
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left-1);
    }

    private static int count(int dis) {
        //처음 집에 고정설치(가장 가까운 공유기 설치 집)
        int start = house[0];
        //개수
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            //매개변수로 주어진 거리(중앙값)보다
            //현제 탐색중인 집과 직전에 설치된 집(공유기가 설치된)의 거리가 크거나 같다면
            //카운팅하고 직전 설치 집 갱신
            if(house[i] - start >= dis) {
                cnt++;
                start = house[i];
            }
        }
        return cnt;
    }
}
