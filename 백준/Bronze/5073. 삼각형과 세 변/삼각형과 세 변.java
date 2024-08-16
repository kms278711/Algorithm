import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l1 = Integer.parseInt(st.nextToken());
            int l2 = Integer.parseInt(st.nextToken());
            int l3 = Integer.parseInt(st.nextToken());
            
            // 종료 조건
            if (l1 == 0 && l2 == 0 && l3 == 0) break;
            
            // 세 변의 길이를 정렬하여 가장 긴 변이 첫번째에 오도록 함
            int[] sides = {l1, l2, l3};
            Arrays.sort(sides);
            
            // 삼각형 조건 확인
            if (sides[2] < sides[0] + sides[1]) {
                if (sides[0] == sides[1] && sides[1] == sides[2]) {
                    sb.append("Equilateral").append("\n");
                } else if (sides[0] == sides[1] || sides[1] == sides[2]) {
                    sb.append("Isosceles").append("\n");
                } else {
                    sb.append("Scalene").append("\n");
                }
            } else {
                sb.append("Invalid").append("\n");
            }
        }
        System.out.print(sb);
    }
}
