import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] dp;
	private static Entry [] Entries;

	private static class Timestamp implements Comparable<Timestamp> {
		int year, month, day, hr, min;
		int [] data;
		
		public Timestamp(String s) {
			StringTokenizer st=new StringTokenizer(s,":");
			this.month=Integer.parseInt(st.nextToken());
			this.day=Integer.parseInt(st.nextToken());
			this.hr=Integer.parseInt(st.nextToken());
			this.min=Integer.parseInt(st.nextToken());
			this.data = new int [] {month,day,hr,min};
		}
		
		public int compareTo(Timestamp ts) {
			for (int i=0;i<this.data.length;i++) if (this.data[i]!=ts.data[i]) return this.data[i]-ts.data[i];
			return 0;
		}
	}

	private static class Entry {
		Timestamp ts;
		boolean isAdd;
		
		public Entry(String line) {
			StringTokenizer st=new StringTokenizer(line);
			this.ts=new Timestamp(st.nextToken());
			st.nextToken(); //useless
			this.isAdd=st.nextToken().charAt(0)=='+';
		}
	}

	private static int find(Entry lastRef, int currIdx, int currYear, int used) {
		if (currIdx==-1) return used;
		else {
			if (dp[currIdx]==Integer.MAX_VALUE) {
				Entry currEntry=Entries[currIdx];

				if (used<dp[currIdx] && !currEntry.isAdd) { // Don't include
					dp[currIdx]=Math.min(dp[currIdx], find(lastRef,currIdx-1,currYear,used));
				}

				if (used+1<dp[currIdx]) { 
					if (lastRef!=null && currEntry.ts.compareTo(lastRef.ts)>=0) currYear--;
					if (currEntry.ts.year==currYear) { // Include
						dp[currIdx]=Math.min(dp[currIdx], find(currEntry,currIdx-1,currYear,used+1));
					}
				}
			}

			return dp[currIdx];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Entries=new Entry[N];
			for (int n=0;n<N;n++) Entries[n]=new Entry(br.readLine());
			for (int n=1;n<N;n++) Entries[n].ts.year=Entries[n].ts.compareTo(Entries[n-1].ts)>0 ? Entries[n-1].ts.year : Entries[n-1].ts.year+1;
			
			dp=new int [N];
			Arrays.fill(dp,Integer.MAX_VALUE);
			find(null,N-1,Entries[N-1].ts.year,0);
			System.out.println(dp[N-1]);
		}
	}

}
