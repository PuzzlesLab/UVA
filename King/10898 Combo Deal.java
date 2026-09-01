import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int [] Pow10={1,10,100,1000,10000,100000,1000000};
	private static int [][] Items;
	private static int [] Prices;
	private static int [][] Dp;
	
	private static int hash(int [] remItem) {
		int r=0;
		for (int i=0;i<remItem.length;i++) r=r*10+remItem[i];
		return r;
	}

	private static int find(int curr, int [] remItem, int remCount) {
		if (remCount==0) return 0;
		if (curr==Prices.length) return 1000000;
		
		int key=hash(remItem);
		if (Dp[curr][key]==-1) {
			int ans=find(curr+1,remItem,remCount);
			boolean flag=true;
			for (int i=0;i<remItem.length;i++) flag&=remItem[i]>=Items[curr][i];
			
			if (flag) {
				for (int i=0;i<remItem.length;i++) {
					remItem[i]-=Items[curr][i];
					remCount-=Items[curr][i];
				}
				ans=Math.min(ans,Prices[curr]+find(curr,remItem,remCount));
				for (int i=0;i<remItem.length;i++) remItem[i]+=Items[curr][i];
			}
			Dp[curr][key]=ans;
		}
		return Dp[curr][key];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int [] indiv=new int [Integer.parseInt(st.nextToken())];
			for (int i=0;i<indiv.length;i++) indiv[i]=Integer.parseInt(st.nextToken());
			
			// Model individual as combo.
			Items=new int [Integer.parseInt(br.readLine())+indiv.length][indiv.length];
			Prices=new int [Items.length];
			for (int i=0;i<indiv.length;i++) Items[i][i]=1;
			for (int i=0;i<indiv.length;i++) Prices[i]=indiv[i];
	
			for (int i=indiv.length;i<Items.length;i++) {
				st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<indiv.length;i2++) Items[i][i2]=Integer.parseInt(st.nextToken());
				Prices[i]=Integer.parseInt(st.nextToken());
			}
			Dp=new int [Prices.length][Pow10[indiv.length]];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
	
			int TC=Integer.parseInt(br.readLine());
			for (int tc=0;tc<TC;tc++) {
				int [] req=new int [indiv.length];
				int total=0;
				st=new StringTokenizer(br.readLine());
				for (int i=0;i<req.length;i++) {
					req[i]=Integer.parseInt(st.nextToken());
					total+=req[i];
				}
	
				System.out.println(find(0,req,total));
			}
		}
	}

}