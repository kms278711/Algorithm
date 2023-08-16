import java.util.*;
import java.io.*;

class Solution {
    private static Map<String, Integer> map;
	private static void getPowerSet(String[] str, String cur, int cnt, int len) {
		if(cnt == str.length) {
			if(cur.length() == len) {
				char[] c = cur.toCharArray();
				Arrays.sort(c);
				cur = String.valueOf(c);
				map.put(cur, map.getOrDefault(cur, 0) + 1);
			}
		} else {
			cur += str[cnt];
			getPowerSet(str, cur, cnt+1, len);
			cur = cur.replace(str[cnt], "");
			getPowerSet(str, cur, cnt+1, len);
		}
	}
    public String[] solution(String[] orders, int[] course) {
        List<String> answerTmp = new ArrayList<String>();
        Arrays.sort(orders);
        for (int courseTmp : course) {
			map = new HashMap<String, Integer>();
			for (String order : orders) {
				if(order.length() < courseTmp) continue;
				getPowerSet(order.split(""), "", 0, courseTmp);
			}
			List<Integer> listTmp = new ArrayList<Integer>(map.values());
			Collections.sort(listTmp, Collections.reverseOrder());
			int cnt = listTmp.size() == 0 ? 0:listTmp.get(0);
			if(cnt > 1) {				
				for (String key : map.keySet()) {
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