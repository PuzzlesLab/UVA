import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] X;
	private static int [] Y;
	private static String Ans;
	private static boolean [] Used;

	private static void find(int [][] curr, int x, int [][] yStates) {
		// yStates[Y][0] = count, [1] = sum so far.
		if (Ans!=null) return;
		if (x==X.length) {
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<curr.length;i++) {
				for (int i2=0;i2<curr.length;i2++) {
					sb.append(curr[i][i2]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			Ans=sb.toString();
			return;
		}

		for (int f1=1;f1<=X[x];f1++) if (X[x]%f1==0 && !Used[f1]) {
			int f2=X[x]/f1;
			if (f1==f2 || Used[f2]) continue;
			for (int y1=0;y1<X.length;y1++) if (yStates[y1][0]<2) {
				if (yStates[y1][0]==0 && Y[y1]%f1!=0) continue;
				if (yStates[y1][0]==1 && yStates[y1][1]*f1!=Y[y1]) continue;
				
				for (int y2=y1+1;y2<X.length;y2++) if (yStates[y2][0]<2) {
					if (yStates[y2][0]==0 && Y[y2]%f2!=0) continue;
					if (yStates[y2][0]==1 && yStates[y2][1]*f2!=Y[y2]) continue;

					curr[y1][x]=f1;
					curr[y2][x]=f2;
					yStates[y1][0]++;
					yStates[y1][1]+=f1;
					yStates[y2][0]++;
					yStates[y2][1]+=f2;
					Used[f1]=true;
					Used[f2]=true;
					
					find(curr,x+1,yStates);
					
					curr[y1][x]=0;
					curr[y2][x]=0;
					yStates[y1][0]--;
					yStates[y1][1]-=f1;
					yStates[y2][0]--;
					yStates[y2][1]-=f2;
					Used[f1]=false;
					Used[f2]=false;
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	X=new int [N];
        	Y=new int [N];
        	
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for (int n=0;n<N;n++) X[n]=Integer.parseInt(st.nextToken());
        	st=new StringTokenizer(br.readLine());
        	for (int n=0;n<N;n++) Y[n]=Integer.parseInt(st.nextToken());
        	
        	Ans=null;
        	Used=new boolean [1001];
        	find(new int [N][N],0,new int [N][2]);
        	System.out.println(Ans);
        }
	}

}