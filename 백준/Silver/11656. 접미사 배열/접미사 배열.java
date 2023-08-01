import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		List<String> list = new ArrayList<>();
		//substring 사용해서 자르기
		for (int i = 0; i < str.length(); i++) {
			list.add(str.substring(i, str.length()));
		}
		//정렬
		Collections.sort(list);
		//출력
		for (String string : list) {
			System.out.println(string);
		}
	}
}