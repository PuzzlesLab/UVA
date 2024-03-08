import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class zz {

	private static final int NULL=100000;
	private static final int KEEP=1;
	private static final int REPLACE=2;
	private static final int INSERT=3;
	private static final int DELETE=4;
	private static char [] S1;
	private static char [] S2;
	private static int [][] Dp;
	private static int [][] Action;

	private static int find(int i, int i2) {
		if (i==0 && i2==0) return 0;
		
		if (Dp[i][i2]==NULL) {
			int ans=NULL-1;
			if (i==0) {
				ans=1+find(i,i2-1);
				Action[i][i2]=INSERT;
			} else if (i2==0) {
				ans=1+find(i-1,i2);
				Action[i][i2]=DELETE;
			} else {
				int [] d={(S1[i-1]==S2[i2-1]?0:1)+find(i-1,i2-1),1+find(i,i2-1),1+find(i-1,i2)};
				int minIdx=0;
				for (int di=1;di<d.length;di++) if (d[minIdx]>d[di]) minIdx=di;
				ans=d[minIdx];
				Action[i][i2]=minIdx+2;
				if (S1[i-1]==S2[i2-1] && Action[i][i2]==REPLACE) Action[i][i2]=KEEP;
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	private static void addStep(ArrayList<String> list, int i, int i2) {
		if (i==0 && i2==0) return;
		if (Action[i][i2]==KEEP) {
			addStep(list,i-1,i2-1);
			return;
		}
		if (Action[i][i2]==REPLACE) {
			addStep(list,i-1,i2-1);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Replace ");
			sb.append(i2);
			sb.append(',');
			sb.append(S2[i2-1]);
			list.add(sb.toString());
		} else if (Action[i][i2]==INSERT) {
			addStep(list,i,i2-1);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Insert ");
			sb.append(i2);
			sb.append(',');
			sb.append(S2[i2-1]);
			list.add(sb.toString());
		} else if (Action[i][i2]==DELETE) {
			addStep(list,i-1,i2);

			StringBuilder sb=new StringBuilder();
			sb.append("Delete ");
			sb.append(i2+1);
			list.add(sb.toString());
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			S1=s.toCharArray();
			S2=br.readLine().toCharArray();
			
			Dp=new int [S1.length+1][S2.length+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			Action=new int [S1.length+1][S2.length+1];
			find(S1.length,S2.length);

			ArrayList<String> list=new ArrayList<>();
			addStep(list,S1.length,S2.length);
			
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			sb.append(list.size());
			sb.append('\n');
			for (int i=0;i<list.size();i++) {
				sb.append(i+1);
				sb.append(' ');
				sb.append(list.get(i));
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}