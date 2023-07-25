import java.util.Arrays;
class Solution {
	public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        //현재인원
        double cur = stages.length;
        
        //떨어진 인원 체크 배열
        int[] check = new int[N+1];
        
        //실패율
        double[] tmp = new double[N];
        for (int i = 1; i <= N; i++) {
        	double cnt = 0;
        	
        	//i스테이지에서 멈춘 사람 카운팅
        	for (int j = 0; j < stages.length; j++) {
				if(stages[j] == i) cnt += 1;
        	}
        	
        	//남은 사람 계산
        	if(cnt == 0) {
        		tmp[i-1] = 0;
        	} else {
        		tmp[i-1] = cnt/cur;
            	cur -= cnt;
        	}
        	
		}
        //실패율 정렬
        double[] tmp2 = Arrays.copyOf(tmp, N);
        Arrays.sort(tmp);

        //실패율 몇번째 인덱스인지 찾기
        for (int i = tmp.length-1; i >= 0; i--) {
			for (int j = 1; j < tmp2.length+1; j++) {
				if(tmp[i] == tmp2[j-1]) {
					if(check[j] == 0) {
						answer[tmp.length-1-i] = j;
						check[j] = 1;
						break;
					}
				}
			}
		}
        return answer;
	}
}