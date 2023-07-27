class Solution {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        int p = 0;
        int q = 0;
        int r = 0;
        int[] used = new int[7];
        //카운팅
        used[a] += 1;
        used[b] += 1;
        used[c] += 1;
        used[d] += 1;
       
        //검사
        for (int i = 1; i <= 6; i++) {
        	//사용된 개수 일일이 비교
			if(used[i] == 4) {
				answer = 1111*i;
				break;
			} else if(used[i] == 3) {
				p = i;
				for (int j = 1; j <= 6; j++) {
					if(used[j] ==1) {
						q = j;
						break;
					}
				}
				answer =(int)Math.pow(10*p+q, 2);
				break;
			} else if(used[i] == 2) {
				p = i;
				for (int j = 1; j <= 6; j++) {
					if(j!=i && used[j]==2) {
						q = j;
						answer = (p+q)*Math.abs(p-q);
						break;
					} else if(used[j] == 1) {
						q = j;
						for (int k = 1; k <= 6; k++) {
							if(k!=j && used[k]==1) {
								r = k;
								break;
							}
						}
						answer = q*r;
						break;
					}
				}
				break;
			}
			if(i==6) {
				for (int j = 1; j <= 6; j++) {
					if(used[j] == 1) {
						answer = j;
						break;
					}
				}
			}
        }
        
        return answer;
    }
}