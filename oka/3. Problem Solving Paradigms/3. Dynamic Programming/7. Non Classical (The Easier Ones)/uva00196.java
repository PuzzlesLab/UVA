import java.util.*;
import java.io.*;

public class Main {
	public static String x[][];
	public static int m[][];
	public static boolean visited[][];
	
	public static int get(int r,int c) {
		if(!visited[r][c]) {
			readFormula(r,c,x[r][c]);
		}				
		return m[r][c];
	}
	
	public static void readFormula(int r,int c,String line) {
		String s = line.substring(1);
		StringTokenizer st = new StringTokenizer(s,"+");
		int total = 0;
		while(st.hasMoreTokens()) {
			Matrix mr = readMatrix(st.nextToken());
			total += get(mr.r,mr.c);
		}
		m[r][c] = total;
		visited[r][c] = true;
	}
	
	public static Matrix readMatrix(String cell) {
		int r = 0;
		int c = 0;
		for(int i=0;i<cell.length();i++) {
			int v = cell.charAt(i);
			if(v-'A'>=0&&v-'A'<=25) {
				c *= 26;
				c += v-'A'+1;
			}else {
				r *= 10;
				r += v-'0';
			}
		}
		return new Matrix(r-1,c-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			x = new String[r][c];
			m = new int[r][c];
			visited = new boolean[r][c];
			for(int j=0;j<r;j++) {
				Arrays.fill(m[j],-1);
			}
			for(int j=0;j<r;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<c;k++) {
					String s = st.nextToken();
					if(s.charAt(0)=='=') {
						x[j][k] = s;
					}else {
						m[j][k] = Integer.parseInt(s);
						visited[j][k] = true;
					}
				}
			}
			for(int j=0;j<r;j++) {
				for(int k=0;k<c;k++) {
					if(visited[j][k]) {
						System.out.print(m[j][k]);
					}else {
						
						readFormula(j,k,x[j][k]);
						System.out.print(m[j][k]);
					}
					if(k<c-1)
						System.out.print(" ");
				}
				System.out.println();
			}
		}

	}
}

class Matrix{
	int r;
	int c;
	Matrix(int r,int c){
		this.r = r;
		this.c = c;
	}
}
