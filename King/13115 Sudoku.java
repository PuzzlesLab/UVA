import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	int N=Integer.parseInt(br.readLine());
        	int sqrt=(int)Math.sqrt(N);
        	if (sqrt*sqrt!=N) sqrt++;

        	int [][] game=new int [N][N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		for (int n2=0;n2<N;n2++) game[n][n2]=Integer.parseInt(st.nextToken())-1;
        	}
        	
        	int [] rowMask=new int [N];
        	int [] colMask=new int [N];
        	int [][] sqrtMask=new int [sqrt][sqrt];
        	for (int r=0;r<N;r++) for (int c=0;c<N;c++) {
        		rowMask[r]|=1<<game[r][c];
        		colMask[c]|=1<<game[r][c];
        		sqrtMask[r/sqrt][c/sqrt]|=1<<game[r][c];
        	}

        	int END_MASK=(1<<N)-1;
        	boolean flag=true;
        	for (int r=0;r<N && flag;r++) flag&=rowMask[r]==END_MASK;
        	for (int c=0;c<N && flag;c++) flag&=colMask[c]==END_MASK;
        	for (int r=0;r<sqrt && flag;r++) for (int c=0;c<sqrt && flag;c++) flag&=sqrtMask[r][c]==END_MASK;

        	System.out.println(flag?"yes":"no");
        }
	}

}