import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
    	Map<String, Integer> map1 = new HashMap<String, Integer>();
    	Map<Integer, String> map2 = new TreeMap<Integer, String>();
    	for (int i = 1; i <= players.length; i++) {
			map1.put(players[i-1], i);
			map2.put(i, players[i-1]);
		}
    	for (String calling : callings) {
    		//calling된애
    		int curRank1 = map1.get(calling);
    		String curName1 = calling;
    		//바꿔질 애
    		int curRank2 = curRank1 - 1;
    		String curName2 = map2.get(curRank2);
			//랭크 바꾸기
    		map1.put(curName1, curRank2);
    		map1.put(curName2, curRank1);
    		map2.put(curRank1, curName2);
    		map2.put(curRank2, curName1);
		}
    	
    	String[] answer = new String[players.length];
    	return map2.values().toArray(answer);
    }
}