class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        int[][] answerMap = new int[n][n];
        int idx = 0;
        
        for(int x=0; x<n; x++) {
            int tmp = arr1[x];         
            for(int k=n-1; k>=0; k--) {
                if(tmp >= Math.pow(2, k)) {
                    map1[x][idx] = 1;
                    tmp = tmp % (int)Math.pow(2, k);
                } 
                idx ++;
            }
            idx = 0;
        }
        
        for(int x=0; x<n; x++) {
            int tmp = arr2[x];         
            for(int k=n-1; k>=0; k--) {
                if(tmp >= Math.pow(2, k)) {
                    map2[x][idx] = 1;
                    tmp = tmp % (int)Math.pow(2, k);
                } 
                idx ++;
            }
            idx = 0;
        }
        
        String str = "";
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map1[i][j] == 1 | map2[i][j] == 1) {
					str += "#";
				} else if(map1[i][j] == 0 && map2[i][j] == 0) {
					str += " ";
				}
			}
			answer[i] = str;
			str = "";
		}
        return answer;
    }
}