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
        			//거리계산
					int tmp = Math.abs(n-numlist[j]);
					if(min > tmp) {
						//최소값 교체
						min = tmp;
						idx = j;
						minVal = numlist[j];
					} else if(min == tmp){
						// 같을경우 더 큰 값이 앞으로 나오게
						if(minVal < numlist[j]) {
							minVal = numlist[j];
							idx = j;
						}
					}
        		}
			}
        	//사용한 수는 체크
        	check[idx] = 1;
        	answer[i] = minVal;
		}
        
        return answer;
    }
}