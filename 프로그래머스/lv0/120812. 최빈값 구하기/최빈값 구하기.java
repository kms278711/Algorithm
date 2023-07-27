class Solution {
    public int solution(int[] array) {
        int answer = 0;
        //카운팅 배열
        int[] used = new int[1000];
        for (int i : array) {
			used[i]+=1;
		}
        //초기화
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < used.length; i++) {
        	if(used[i] > max) {
        		max = used[i];
        		answer = i;
        	} else if(used[i] == max) answer = -1;
		}
        return answer;
    }
}