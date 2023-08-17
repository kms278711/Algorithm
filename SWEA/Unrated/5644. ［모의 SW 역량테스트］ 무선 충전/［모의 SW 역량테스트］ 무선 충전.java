import java.util.*;
import java.io.*;

//영역 클래스(BC넘버, 해당BC파워)
class Area{
	public int areaNum;
	public int P;
	public Area(int areaNum, int P){
		this.areaNum = areaNum;
		this.P = P;
	}
}

public class Solution {
	//10*10짜리 Area리스트의 2차원 배열
	private static List<Area>[][] arr;
	//BC넘버
	private static int area;
	//BC설치
	private static void setBC(int y, int x, int C, int P) {
		int cnt = 0;
		//가운데 기준 위에C칸
		for (int i = C; i >= 0; i--) {
			for (int j = y-cnt; j <= y+cnt; j++) {
				if(x-i>=0 && x-i<10 && j>=0 && j<10) {
					if(arr[x-i][j] == null) {
						List<Area> tmp = new ArrayList<>();
						tmp.add(new Area(area,P));
						arr[x-i][j] = tmp;
					} else {
						arr[x-i][j].add(new Area(area, P));
					}
				}
			}
			cnt++;
		}
		cnt -= 2;
		//가운데 기준 아래 C-1칸
		for (int i = 1; i <= C; i++) {
			for (int j = y-cnt; j <= y+cnt; j++) {
				if(x+i>=0 && x+i<10 && j>=0 && j<10) {
					if(arr[x+i][j] == null) {
						List<Area> tmp = new ArrayList<>();
						tmp.add(new Area(area,P));
						arr[x+i][j] = tmp;
					} else {
						arr[x+i][j].add(new Area(area, P));
					}
				}
			}
			cnt--;
		}
	}
    public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//테스트케이스개수
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int answer = 0;
			arr = new List[10][10];
			//기본적인 출력폼 StringBuilder에 추가
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			//입력받기
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int[] aMove = new int[M];
			int[] bMove = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				aMove[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				bMove[j] = Integer.parseInt(st.nextToken());
			}
			
			//BC설치
			for (int j = 0; j < A; j++) {
				st = new StringTokenizer(br.readLine());
				area++;
				setBC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			//두사람의 초기위치
			int aCurX = 0;
			int aCurY = 0;
			int bCurX = 9;
			int bCurY = 9;
			
			//처음자리 체크
			List<Area> aList = arr[aCurX][aCurY];
			List<Area> bList = arr[bCurX][bCurY];
			if(aList==null && bList!=null) {
				//구역크기별로 정렬
				Collections.sort(bList, new Comparator<Area>() {
					@Override
					public int compare(Area o1, Area o2) {
						return o1.P-o2.P;
					}
				});
				//파워추가
				answer += bList.get(bList.size()-1).P;
			} else if(aList!=null && bList==null) {
				Collections.sort(aList, new Comparator<Area>() {
					@Override
					public int compare(Area o1, Area o2) {
						return o1.P-o2.P;
					}
				});
				answer += aList.get(aList.size()-1).P;
			} else if(aList!=null && bList!=null){
				Collections.sort(aList, new Comparator<Area>() {
					@Override
					public int compare(Area o1, Area o2) {
						return o1.P-o2.P;
					}
				});
				Collections.sort(bList, new Comparator<Area>() {
					@Override
					public int compare(Area o1, Area o2) {
						return o1.P-o2.P;
					}
				});
				
				//최대 경우의수 구할변수
				int max = Integer.MIN_VALUE;
				for (int k = 0; k < aList.size(); k++) {
					for (int h = 0; h < bList.size(); h++) {
						if(aList.get(k).areaNum == bList.get(h).areaNum) {
							max = Math.max(max, aList.get(k).P);
						} else {
							max = Math.max(max, aList.get(k).P+bList.get(h).P);
						}
					}
				}
				answer += max;
			}
			
			for (int j = 0; j < M; j++) {
				
				//다음 위치 이동
				if(aMove[j] == 1) {
					aCurX -= 1;
				} else if(aMove[j] == 2) {
					aCurY += 1;
				} else if(aMove[j] == 3) {
					aCurX += 1;
				} else if(aMove[j] == 4) {
					aCurY -= 1;
				}
				
				if(bMove[j] == 1) {
					bCurX -=1;
				} else if(bMove[j] == 2) {
					bCurY +=1;
				} else if(bMove[j] == 3) {
					bCurX +=1;
				} else if(bMove[j] == 4) {
					bCurY -=1;
				}
				
				aList = arr[aCurX][aCurY];
				bList = arr[bCurX][bCurY];
				if(aList==null && bList!=null) {
					Collections.sort(bList, new Comparator<Area>() {
						@Override
						public int compare(Area o1, Area o2) {
							return o1.P-o2.P;
						}
					});
					answer += bList.get(bList.size()-1).P;
				} else if(aList!=null && bList==null) {
					Collections.sort(aList, new Comparator<Area>() {
						@Override
						public int compare(Area o1, Area o2) {
							return o1.P-o2.P;
						}
					});
					answer += aList.get(aList.size()-1).P;
				} else if(aList!=null && bList!=null){
					Collections.sort(aList, new Comparator<Area>() {
						@Override
						public int compare(Area o1, Area o2) {
							return o1.P-o2.P;
						}
					});
					Collections.sort(bList, new Comparator<Area>() {
						@Override
						public int compare(Area o1, Area o2) {
							return o1.P-o2.P;
						}
					});
					
					int max = Integer.MIN_VALUE;
					for (int k = 0; k < aList.size(); k++) {
						for (int h = 0; h < bList.size(); h++) {
							if(aList.get(k).areaNum == bList.get(h).areaNum) {
								max = Math.max(max, aList.get(k).P);
							} else {
								max = Math.max(max, aList.get(k).P+bList.get(h).P);
							}
						}
					}
					answer += max;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
    }
}
