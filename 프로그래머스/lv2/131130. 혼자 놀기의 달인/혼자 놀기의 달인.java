import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        for (int lt = 0; lt < cards.length; lt++) {
        	boolean[] visited = new boolean[cards.length];
        	List<Integer> list1 = new ArrayList<>();
        	int cur = lt;
        	while(true) {
        		visited[cur] = true;
        		list1.add(cur+1);
        		cur = cards[cur]-1;
        		if(visited[cur]) break;
        	}
			for (int rt = lt+1; rt < visited.length; rt++) {
				List<Integer> list2 = new ArrayList<>();
				boolean[] visited2 = new boolean[cards.length];
				cur = rt;
				if(!visited[cur]) {
					while(true) {
						visited2[cur] = true;
		        		list2.add(cur+1);
		        		cur = cards[cur]-1;
		        		if(visited2[cur]) break;
					}
				}
				answer = Math.max(answer, list1.size()*list2.size());
			}
		}
        return answer;
    }
}