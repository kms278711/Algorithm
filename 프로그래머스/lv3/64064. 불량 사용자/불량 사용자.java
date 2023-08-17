import java.util.*;
import java.io.*;

class Solution {
	private static int len; 
	private static boolean[] checked;
	private static Set<List<String>> set = new HashSet<>();
	private static List<String> list;
	private static void count(int cnt, String[] user_id, String[] banned_id) {
		if(cnt==banned_id.length && list.size() == len) {
			Collections.sort(list);
			set.add(list);
		} else {
			String tmp = banned_id[cnt].replace("*", ".");
			for (int i = 0; i < user_id.length; i++) {
				if(user_id[i].matches(tmp) && !checked[i]) {
					checked[i] = true;
					list.add(user_id[i]);
					count(cnt+1, user_id, banned_id);
					checked[i] = false;
					list.remove((Object)user_id[i]); 
				}
			}
		}
	}
	
	public int solution(String[] user_id, String[] banned_id) {
        len = banned_id.length;
        checked = new boolean[user_id.length];
        list = new ArrayList<>();
        count(0, user_id, banned_id); 
        return set.size();
    }
}