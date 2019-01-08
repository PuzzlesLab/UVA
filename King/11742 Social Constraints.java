import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static int [][] constraints;
	public static int counter=0;
	
	public static void dfs (int [] currN, int [] location, boolean [] flag, int level) {
		if (level==currN.length) {
			 boolean meetsConstraint=true;
			 for (int i=0;i<constraints.length && meetsConstraint;i++) {
				 int a=constraints[i][0];
				 int b=constraints[i][1];
				 int c=constraints[i][2];
				 if (c<0) {
					 meetsConstraint=Math.abs(location[a]-location[b])>=-c;
				 } else {
					 meetsConstraint=Math.abs(location[a]-location[b])<=c;
				 }
			 }
			 if (meetsConstraint) {
				 counter++;
			 }
		} else {
			for (int i=0;i<currN.length;i++) {
				if (!flag[i]) {
					flag[i]=true;
					currN[level]=i;
					location[i]=level;
					dfs(currN,location,flag,level+1);
					flag[i]=false;
				}
			}
		}
	}
	
	public static void main (String [] abc) throws IOException  {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			if (m>0) {
				constraints = new int [m][3];
				for (int i=0;i<m;i++) {
					st=new StringTokenizer(br.readLine());
					constraints[i][0]=Integer.parseInt(st.nextToken());
					constraints[i][1]=Integer.parseInt(st.nextToken());
					constraints[i][2]=Integer.parseInt(st.nextToken());
				}
	
				counter=0;
				dfs(new int [n],new int [n],new boolean [n], 0);
			} else {
				counter=1;
				for (int i=2;i<=n;i++) {
					counter*=i;
				}
			}

			System.out.println(counter);
		}
 	}
}