import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for(int n : list) {
            sb.append(n).append("\n");
        }
        System.out.print(sb);
    }
}
