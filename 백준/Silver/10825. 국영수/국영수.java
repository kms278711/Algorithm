import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
class score{
	String name;
	int score1;
	int score2;
	int score3;
	public score(String name, int score1, int score2, int score3) {
		this.name = name;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
	}
	@Override
	public String toString() {
		return "score [name=" + name + ", score1=" + score1 + ", score2=" + score2 + ", score3=" + score3 + "]";
	}
	
}
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		List<score> list = new ArrayList<>();
		for (int i = 0; i < n; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			score tmp = new score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(tmp);
		}
		Collections.sort(list, new Comparator<score>() {
			@Override
			public int compare(score o1, score o2) {
				if(o1.score1==o2.score1) {
					if(o1.score2==o2.score2) {
						if(o1.score3 == o2.score3) {
							return o1.name.compareTo(o2.name);
						}
						else return o2.score3-o1.score3;
					}
					else return o1.score2-o2.score2;
				} else return o2.score1-o1.score1;
			}
		});
		for (score score : list) {
			sb.append(score.name);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}