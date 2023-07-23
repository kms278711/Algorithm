import java.util.Arrays;
class Solution {
	public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double cur = stages.length;
        int[] check = new int[N+1];
        double[] tmp = new double[N];
        for (int i = 1; i <= N; i++) {
        	double cnt = 0;
        	for (int j = 0; j < stages.length; j++) {
				if(stages[j] == i) cnt += 1;
        	}
        	if(cnt == 0) {
        		tmp[i-1] = 0;
        	} else {
        		tmp[i-1] = cnt/cur;
            	cur -= cnt;
        	}
        	
		}
        double[] tmp2 = Arrays.copyOf(tmp, N);
        Arrays.sort(tmp);
        System.out.println(Arrays.toString(tmp));
        System.out.println(Arrays.toString(tmp2));
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