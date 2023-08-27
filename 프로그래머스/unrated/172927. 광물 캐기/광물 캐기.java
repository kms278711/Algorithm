import java.util.*;


public class Solution {
	private static int answer=Integer.MAX_VALUE, L;
	private static int[][] tired = {{1,1,1},{5,1,1},{25,5,1}};
	private static String[] mineralList;
	private static boolean flag = false;
	private static void DFS(int cnt, int[] picks, int cur) {
		if(cur>answer) return;
		System.out.println(Arrays.toString(picks));
		System.out.println(cur);
		if(cnt>=L || flag) {
			System.out.println(cur);
			answer = Math.min(answer, cur);
			flag = false;
		} else {
			int[] tmp = Arrays.copyOf(picks, 3);
			flag = true;
			for (int i = 0; i < 3; i++) {
				if(tmp[i] != 0) {
					flag = false;
					tmp[i] --;
					int curTmp = cur;
					for (int j = 0; j < ((L-cnt >= 5) ? 5:L-cnt); j++) {
						if(mineralList[cnt+j].equals("diamond")) {
							curTmp += tired[i][0];
						} else if(mineralList[cnt+j].equals("iron")) {
							curTmp += tired[i][1];
						} else if(mineralList[cnt+j].equals("stone")) {
							curTmp += tired[i][2];
						} 
					}
					DFS(cnt+5, tmp, curTmp);
					tmp[i] ++;
				}
			}
			if(flag) DFS(L, picks, cur);
		}
	}
	
    public int solution(int[] picks, String[] minerals) {
    	L = minerals.length;
    	mineralList = minerals;
    	DFS(0, picks, 0);
        return answer;
    }
}