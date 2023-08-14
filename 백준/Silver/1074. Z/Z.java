import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, r, c, answer, curX, curY, num;
	public static int[] dx = {0, 0, 1, 1};
	public static int[] dy = {0, 1, 0, 1};
	public static void DFS(int x, int y, int size, int end) {
		if(size==2) {
			int tmp = end-3; 
			for (int i = 0; i < 4; i++) {
				if(x+dx[i]==r && y+dy[i]==c) {
					answer = tmp;
					return;
				}
				tmp++;
			}
			return;
		} else {
			int half = size/2;
			N--;
			if(x+half>r && y+half>c) {
				DFS(x, y, half, end-(int)(Math.pow(4, N))*3); 
			} else if(x+half>r && y+half<=c) {
				DFS(x, y+half, half, end-(int)(Math.pow(4, N))*2); 
			} else if(x+half<=r && y+half>c) {
				DFS(x+half, y, half, end-(int)(Math.pow(4, N))); 
			} else if(x+ half<=r && y+half<=c) {
				DFS(x+half, y+half, half, end);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		DFS(0, 0, (int)Math.pow(2, N), (int)Math.pow(2, N)*(int)Math.pow(2, N)-1);
		System.out.println(answer);			
	}
}