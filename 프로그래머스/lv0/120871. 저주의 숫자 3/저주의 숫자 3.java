class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] nArr = new int[101];
        int cur = 1;
        for (int i = 1; i < nArr.length; i++) {
            //3의 배수이거나 3이 포함되어있으면 현재 숫자 증가
        	while(cur%3 == 0 || Integer.toString(cur).contains("3")) {
        		cur++;
        	}
			nArr[i] = cur;
			if(i==n) break;
			cur++;
		}
        answer = nArr[n];
        return answer;
    }
}