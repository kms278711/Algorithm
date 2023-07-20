class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] nArr = new int[101];
        int cur = 1;
        for (int i = 1; i < nArr.length; i++) {
        	while(cur%3 == 0 || cur/10 == 3 || cur%10 ==3 || cur/10%10 == 3) {
        		cur++;
        	}
			nArr[i] = cur;
			cur++;
		}
        answer = nArr[n];
        return answer;
    }
}