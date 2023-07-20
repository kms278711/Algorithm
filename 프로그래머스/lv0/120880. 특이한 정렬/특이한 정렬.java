class Solution {
    public int[] solution(int[] numlist, int n) {
        int[] answer = new int[numlist.length];
        int[] check = new int[numlist.length];  
        for (int i = 0; i < answer.length; i++) {
        	int min = Integer.MAX_VALUE;
            int idx = -1;
            int minVal = 0;
        	//탐색시작
        	for (int j = 0; j < answer.length; j++) {
        		if(check[j] != 1) {
					int tmp = Math.abs(n-numlist[j]);
					if(min > tmp) {
						min = tmp;
						idx = j;
						minVal = numlist[j];
					} else if(min == tmp){
						if(minVal < numlist[j]) {
							minVal = numlist[j];
							idx = j;
						}
					}
        		}
			}
        	check[idx] = 1;
        	answer[i] = minVal;
		}
        
        return answer;
    }
}