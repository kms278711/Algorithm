import java.util.*;

public class Solution {
	//조합 카운팅할 map<String조합, 개수카운팅>
    private static Map<String, Integer> map;
    //길이가 len인 부분집합구하기 (현재 String쪼갠거, 현재부분집합으로 완성된 문자열, 단계, 구해야되는 부분집합  길이)
	private static void getPowerSet(String[] str, String cur, int cnt, int len) {
		//깊이가 string 길이까지 왔다면
		if(cnt == str.length) {
			//길이만큼이라면
			if(cur.length() == len) {
				//오름차순으로 문자 정렬
				char[] c = cur.toCharArray();
				Arrays.sort(c);
				cur = String.valueOf(c);
				//map에 카운팅
				map.put(cur, map.getOrDefault(cur, 0) + 1);
			}
		} else {
			//현재 문자열 해당 글자 사용할 경우
			cur += str[cnt];
			getPowerSet(str, cur, cnt+1, len);
			//현재 문자열 해당 글자 사용하지 않을 경우
			cur = cur.replace(str[cnt], "");
			getPowerSet(str, cur, cnt+1, len);
		}
	}
    public String[] solution(String[] orders, int[] course) {
        List<String> answerTmp = new ArrayList<String>();
        Arrays.sort(orders);
        //코스 길이만큼
        for (int courseTmp : course) {
			map = new HashMap<String, Integer>();
			for (String order : orders) {
				//길이가 더짧을떄 패스
				if(order.length() < courseTmp) continue;
				getPowerSet(order.split(""), "", 0, courseTmp);
			}
			//value들 내림차순
			List<Integer> listTmp = new ArrayList<Integer>(map.values());
			Collections.sort(listTmp, Collections.reverseOrder());
			//NullPointerException 방지. 가장 큰 value
			int cnt = listTmp.size() == 0 ? 0:listTmp.get(0);
			//시킨 경우가 2개이상일때만
			if(cnt > 1) {				
				for (String key : map.keySet()) {
					//가장 큰 value 가지고 있는 key값들 넣기
					if(map.get(key) == cnt) {
						answerTmp.add(key);
					}
				}
			}
        }
        Collections.sort(answerTmp);
        String[] answer = new String[answerTmp.size()];
        return answerTmp.toArray(answer);
    }
}