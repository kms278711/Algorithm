import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        for (int lt = 0; lt < cards.length; lt++) {
        	//방문배열
        	boolean[] visited = new boolean[cards.length];
        	//첫번째 그룹
        	List<Integer> list1 = new ArrayList<>();
        	//lt 값 현재 인덱스로 저장
        	int cur = lt;
        	while(true) {
        		//현재 값 방문
        		visited[cur] = true;
        		//그룹에 저장(인덱스 + 1)
        		list1.add(cur+1);
        		//현재 lt가 가리키는 값 (인덱스-1) 찾아서 현재 인덱스 바꿔줌
        		cur = cards[cur]-1;
        		//방문했던 곳이면 빠져나오기
        		if(visited[cur]) break;
        	}
        	//lt보다 한칸앞에서 탐색
			for (int rt = lt+1; rt < cards.length; rt++) {
				//두번째 그룹
				List<Integer> list2 = new ArrayList<>();
				//두번쨰 방문그룹
				boolean[] visited2 = new boolean[cards.length];
				//rt 값 현재 인덱스로 저장
				cur = rt;
				//방문한 곳이 아니라면 탐색 시작
				if(!visited[cur]) {
					while(true) {
						visited2[cur] = true;
		        		list2.add(cur+1);
		        		cur = cards[cur]-1;
		        		if(visited2[cur]) break;
					}
				}
				//리스트 사이즈 곱한 값과 현재의 answer값 중 큰 값을 answer에 집어 넣는다.
				answer = Math.max(answer, list1.size()*list2.size());
			}
		}
        return answer;
    }
}