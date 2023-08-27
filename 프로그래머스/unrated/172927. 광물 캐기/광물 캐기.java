import java.util.*;

public class Solution {
	//갱신할 정답, 총 미네랄 개수
	private static int answer=Integer.MAX_VALUE, L;
	//곡괭이와 광석 피로도 표
	private static int[][] tired = {{1,1,1},{5,1,1},{25,5,1}};
	//광석리스트
	private static String[] mineralList;
	//곡괭이 다 썻는지 체크
	private static boolean flag = false;
	/**
	 * 5개씩 선택된 곡괭이로 광질
	 * @param cnt
	 * @param picks
	 * @param cur
	 */
	private static void DFS(int cnt, int[] picks, int cur) {
		//이미나온 최소 피로도보다 크면 리턴
		if(cur>answer) return;
		//광석개수가 총개수보다 같거나 커지거나, 곡괭이을 다쓰면 해당분기 종료
		if(cnt>=L || flag) {
			answer = Math.min(answer, cur);
			//다시 초기화
			flag = false;
		} else {
			//해당분기 곡괭이 사용현황. 복사해서 파라미터로 넘김
			int[] tmp = Arrays.copyOf(picks, 3);
			//초기화
			flag = true;
			//곡괭이 종류만큼 반복
			for (int i = 0; i < 3; i++) {
				//해당곡괭이가 있다면
				if(tmp[i] != 0) {
					//아직 곡괭이가 남아있다고 표시
					flag = false;
					//해당곡괭이 개수 1감소
					tmp[i] --;
					//현재 피로도 임시로 넣는 변수
					int curTmp = cur;
					//남은 광석이 5개보다 적으면 해당 개수만큼, 아니라면 5개씩
					for (int j = 0; j < ((L-cnt >= 5) ? 5:L-cnt); j++) {
						if(mineralList[cnt+j].equals("diamond")) {
							curTmp += tired[i][0];
						} else if(mineralList[cnt+j].equals("iron")) {
							curTmp += tired[i][1];
						} else if(mineralList[cnt+j].equals("stone")) {
							curTmp += tired[i][2];
						} 
					}
					//개수 +5 , 현재 곡괭이 사용현황, 해당 분기 피로도
					DFS(cnt+5, tmp, curTmp);
					//곡괭이 안썼을때로 원상복귀
					tmp[i] ++;
				}
			}
			//곡괭이 다 썼을 경우 개수를 L로 넘겨서 해당분기 끝내게 설정
			if(flag) DFS(L, picks, cur);
		}
	}
	
    public int solution(int[] picks, String[] minerals) {
    	//길이 넣어줌.
    	L = minerals.length;
    	//정적변수로 사용하기 위해 해당 변수 넣어줌.ㄴ
    	mineralList = minerals;
    	//DFS시작
    	DFS(0, picks, 0);
        return answer;
    }
}