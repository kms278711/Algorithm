import java.util.Arrays;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        int cnt = 0;
        
        //true 개수찾기
        for (int i = 0; i < attendance.length; i++) {
			if(attendance[i]) cnt ++;
		}
        int[] rank2 = new int[cnt];
        int idx = 0;
        
        //true인 것만 새로 담기
        for (int i = 0; i < rank.length; i++) {
			if(attendance[i]) {
				rank2[idx++] = rank[i];
			}
		}
        
        //정렬
        Arrays.sort(rank2);

        //위치찾기
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < rank.length; i++) {
			if(rank[i] == rank2[0]) a = i;
		}
        for (int i = 0; i < rank.length; i++) {
			if(rank[i] == rank2[1]) b = i;
		}
        for (int i = 0; i < rank.length; i++) {
			if(rank[i] == rank2[2]) c = i;
		}
        answer = 10000*a + 100*b+c;
        return answer;
    }
}