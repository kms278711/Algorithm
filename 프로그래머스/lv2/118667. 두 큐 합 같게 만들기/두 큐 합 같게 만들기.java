import java.util.*;

class Solution {
    public long solution(int[] queue1, int[] queue2) {
        int answer = 0;
        //각 큐의 합
        long sum1 = 0;
        long sum2 = 0;
        //큐 선언
        Queue<Integer> q1 = new ArrayDeque<Integer>();
        Queue<Integer> q2 = new ArrayDeque<Integer>();        
        Queue<Integer> q3 = new ArrayDeque<Integer>();
        
        //합 구하기, 큐에 값 넣기
        for (int i = 0; i < queue1.length; i++) {
			sum1 += queue1[i];
			sum2 += queue2[i];
			q1.offer(queue1[i]);
			q2.offer(queue2[i]);
			q3.offer(queue2[i]);
		}
        //최종적으로 나와야 할 합
        if((sum1+sum2)%2 != 0) return -1;
        long tmp = (sum1+sum2)/2;
       
        while(sum1 != tmp) {
        	//큐1의 합이 더 크다면
        	if(sum1>sum2) {
        		int cur = q1.poll();
        		sum1 -= cur;
        		sum2 += cur;
        		q2.offer(cur);
    		//큐2의 합이 더 크다면
        	} else {
        		int cur = q2.poll();
        		sum1 += cur;
        		sum2 -= cur;
        		q1.offer(cur);
        	}
        	answer++;
        	//큐가 비었다면 불가능
        	if(q1.isEmpty() || q2.isEmpty() || queue1.length*4 < answer) return -1;	
         }
        return answer;
    }
}