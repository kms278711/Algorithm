import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		//팀수
		int N = Integer.parseInt(st.nextToken());
		//문제수
		int M = Integer.parseInt(st.nextToken());
		//팀명이 key인 맵
		Map<String, List<String>> teamKeyMap = new HashMap<String, List<String>>();
		//멤버리스트가 key인 맵
		Map<List<String>, String> memberKeyMap = new HashMap<List<String>, String>();
		
		for (int i = 0; i < N; i++) {
			//팀이름
			String team = br.readLine();
			//인원수
			int num = Integer.parseInt(br.readLine());
			List<String> tmp = new ArrayList<String>();
			for (int j = 0; j < num; j++) {
				tmp.add(br.readLine());
			}
			//오름차순 정렬 후 map에 맞는 key value로 넣어주기
			Collections.sort(tmp);
			teamKeyMap.put(team, tmp);
			memberKeyMap.put(tmp, team);
		}
		for (int i = 0; i < M; i++) {
			//문제
			String q = br.readLine();
			//문제유형
			String type = br.readLine();
			if(type.equals("0")) {
				//멤버전부출력
				for (String member : teamKeyMap.get(q)) {
					sb.append(member).append("\n");
				}
			} else if(type.equals("1")) {
				//팀에 문제로 주어진 사람이 있다면 팀명 출력
				for (List<String> team: memberKeyMap.keySet()) {
					if(team.contains(q)) {
						sb.append(memberKeyMap.get(team)).append("\n");
						break;
					}
				}
			}
				
		}
		System.out.println(sb);
	}
}