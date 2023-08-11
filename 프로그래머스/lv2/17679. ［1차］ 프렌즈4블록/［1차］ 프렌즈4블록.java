import java.io.*;
import java.util.*;

class Solution {
	public int solution(int m, int n, String[] board) {
	    int answer = 0;
	    String[][] arr = new String[m][n];
	    String[][] arr2 = new String[m][n];
	    
	    for (int i = 0; i < m; i++) {
	    	String[] tmp2 = board[i].split("");
	    	for (int j = 0; j < n; j++) {
	    		arr[i][j] = tmp2[j];
			}
	    }
	    //없앨수 있는게 더 남아있는지 체크할 flag
	    boolean flag = true;
	    while(flag) {
	    	flag = false;
		    for (int i = 0; i < m; i++) {
		    	for (int j = 0; j < n; j++) {
		    		arr2[i][j] = arr[i][j];
				}
		    }
		   int answerTmp = 0;
		   int answerTmp2 = 0;
		  //처음상태 0개수 카운팅
	    	for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(arr2[i][j].equals("0")) answerTmp++;
				}
			}
		    
		   
		    for (int i = 0; i < m-1; i++) {
				for (int j = 0; j < n-1; j++) {
					//4개가 같은경우 0으로 바꾸기
					if(!arr[i][j].equals("0") && arr[i][j].equals(arr[i][j+1]) && arr[i][j].equals(arr[i+1][j]) && arr[i][j].equals(arr[i+1][j+1])) {
						arr2[i][j] = "0";
						arr2[i+1][j] = "0";
						arr2[i][j+1] = "0";
						arr2[i+1][j+1] = "0";
						flag = true;
					}
				}
			}
		    
		    //바뀐게 있을때만
		    if(flag) {
		    	//0으로 치환하고 0개수 카운팅
		    	for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						if(arr2[i][j].equals("0")) answerTmp2++;
					}
				}
		    	answer += answerTmp2-answerTmp;
		    	//반복문break확인할 flag
			    boolean flag2 = true;
		    	while(flag2) {
		    		flag2 = false;
		    		for (int i = 0; i < m-1; i++) {
						for (int j = 0; j < n; j++) {
							if(arr2[i+1][j].equals("0")) {
								if(!arr2[i][j].equals("0")) {
									String tmp = arr2[i][j];
									arr2[i+1][j] = tmp;
									arr2[i][j] = "0";	
									flag2 = true;
								}
							}
						}
					}
		    	}
		    	
		    	//원본배열에 복사
			    for (int i = 0; i < m; i++) {
			    	for (int j = 0; j < n; j++) {
			    		arr[i][j] = arr2[i][j];
					}
			    }
		    } 
	    }
	    return answer;
	}
}