class Solution { 
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        //평균? 가운데 수? 구하기
        int tmp = total/num;
        // 개수 짝수인지 홀수인지 확인
        if(num%2 == 1) {
        	//가운데 할당
        	answer[num/2] = tmp;
        	//양쪽으로 추가
        	for (int i = 1; i <= num/2; i++) {
        		answer[num/2-i] = tmp-i;
        		answer[num/2+i] = tmp+i;
     		}
        } else {
        	//가운데 할당
        	answer[num/2-1] = tmp;
        	answer[num/2] = tmp+1;
        	//양쪽으로 추가
        	for (int i = 1; i <= num/2-1; i++) {
				answer[num/2-1-i] = tmp - i;
				answer[num/2+i] = tmp+1+i;
			}
        }
        return answer;
    }
}