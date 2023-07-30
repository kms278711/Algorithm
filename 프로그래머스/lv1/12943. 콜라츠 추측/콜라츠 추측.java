class Solution {
    public int solution(int num) {
        int answer = 0;
        //int의 표현범위로는 한계가 있음
        double tmp = num;
        while(tmp != 1 && answer != 500) {
        	if(tmp %2 == 0) {
        		tmp = tmp/2;
        		answer++;
        	} else {
        		tmp = tmp*3 + 1;
        		answer++;
        	}
        }
        if(answer== 500) return -1;
        return answer;
    }
}