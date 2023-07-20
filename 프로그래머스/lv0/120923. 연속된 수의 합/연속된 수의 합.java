class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        int tmp = total/num;
        if(num%2 == 1) {
        	answer[num/2] = tmp;
        	for (int i = 1; i <= num/2; i++) {
        		answer[num/2-i] = tmp-i;
        		answer[num/2+i] = tmp+i;
     		}
        } else {
        	answer[num/2-1] = tmp;
        	answer[num/2] = tmp+1;
        	for (int i = 1; i <= num/2-1; i++) {
				answer[num/2-1-i] = tmp - i;
				answer[num/2+i] = tmp+1+i;
			}
        }
        return answer;
    }
}