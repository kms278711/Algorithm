import java.util.*;
import java.io.*;

public class Main {
	public static int N,M;
	public static int[][] arr, tmp;
	public static StringBuilder sb = new StringBuilder();
	
	public static void copyArr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = tmp[i][j];
			}
		}
	}
	//1번연산
	public static void operation1() {
		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[N-i-1][j];
			}
		}
		copyArr();
	}
	
	//2번연산
	public static void operation2() {
		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][M-j-1];
			}
		}
		copyArr();
	}
	
	//3번연산
	public static void operation3() {
		int num = N;
		N = M;
		M = num;

		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[M-j-1][i];
			}
		}
		arr = new int[N][M];
		copyArr();
	}
	
	//4번연산
	public static void operation4() {
		int num = N;
		N = M;
		M = num;

		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[j][N-i-1];
			}
		}
		arr = new int[N][M];
		copyArr();
	}
	
	public static void operation5() {
		//배열 나누기
		int[][] arr1 = new int[N/2][M/2];
		int[][] arr2 = new int[N/2][M/2];
		int[][] arr3 = new int[N/2][M/2];
		int[][] arr4 = new int[N/2][M/2];
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr1[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr2[i][j] = arr[i][M/2+j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr3[i][j] = arr[N/2+i][M/2+j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr4[i][j] = arr[N/2+i][j];
			}
		}
		
		//회전해서 넣기
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[i][j] = arr4[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[i][M/2+j] = arr1[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[N/2+i][M/2+j] = arr2[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[N/2+i][j] = arr3[i][j]; 
			}
		}
	}
	
	public static void operation6() {
		int[][] arr1 = new int[N/2][M/2];
		int[][] arr2 = new int[N/2][M/2];
		int[][] arr3 = new int[N/2][M/2];
		int[][] arr4 = new int[N/2][M/2];
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr1[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr2[i][j] = arr[i][M/2+j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr3[i][j] = arr[N/2+i][M/2+j];
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr4[i][j] = arr[N/2+i][j];
			}
		}
		
		//회전해서 넣기
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[i][j] = arr2[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[i][M/2+j] = arr3[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[N/2+i][M/2+j] = arr4[i][j]; 
			}
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				arr[N/2+i][j] = arr1[i][j]; 
			}
		}
	}

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		int R = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {			
			switch(st.nextToken()) {
			case "1":
				operation1();
				break;
			case "2":
				operation2();
				break;
			case "3":
				operation3();
				break;
			case "4":
				operation4();
				break;
			case "5":
				operation5();
				break;
			case "6":
				operation6();
				break;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}