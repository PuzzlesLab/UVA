import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	private static class FTree {
		private int [] value;
		private int [] rsqCache;
		private int maxSearch;
		
		private static int fsone(int s) { return s & -s; }

		public FTree(int size) {
			this.value=new int [size];
			this.maxSearch=(int)Math.ceil(Math.log(size)/Math.log(2));
			this.rsqCache=new int [size];
			Arrays.fill(this.rsqCache,-1);
		}
		
		public int size() { return this.value.length; }
		
		public int rsq(int i) {
			int sum=0;
			for (;i>0;i-=FTree.fsone(i)) sum+=value[i];
			return sum;
		}
		
		public int rsqWithCache(int min, int max) {
			if (this.rsqCache[min]==-1) this.rsqCache[min]=this.rsq(min);
			if (this.rsqCache[max]==-1) this.rsqCache[max]=this.rsq(max);
			return this.rsqCache[max]-this.rsqCache[min];
		}

		public void update(int i, int v) { for (;i<this.size();i+=FTree.fsone(i)) value[i]+=v; }
		
		public int select(int rank) {
			int ans=0, num=0;
			for (int i=this.maxSearch;i>=0;i--) {
				ans += (1<<i);
				if (ans>=this.size() || num+this.value[ans]>=rank) ans-=(1<<i);
				else num+=this.value[ans];
			}
			return ans+1;
		}
	}
	
	private static int searchLessOrEqual(ArrayList<Integer> list, int value) {
		if (value<list.get(0)) return -1;
		else if (value>=list.get(list.size()-1)) return list.size()-1;
		int min=0, max=list.size()-1, mid=-1;
		int lastMid=0;
		while (min<max) {
			lastMid=mid;
			mid=(min+max)/2;
			if (mid==lastMid) return list.get(max)<=value ? max : max-1;

			int midValue=list.get(mid);
			if (midValue==value) return mid;
			else if (midValue>value) max=mid-1;
			else min=mid;
		}
		return max;
	}

	public static void main (String [] args) throws Exception {
		final int MAX=2000001;
		FTree ft=new FTree(MAX);
		for (int i=1;i<ft.size();i++) {
			if (i%2==1) ft.value[i]+=1;
			int parent=i+FTree.fsone(i);
			if (parent<ft.size()) ft.value[parent]+=ft.value[i];
		}
		
		for (int rank=2;rank<ft.size();rank++) {
			int numAtRank=ft.select(rank);
			if (numAtRank==ft.size()-1) break;
			for (int loop=numAtRank;loop<ft.size();loop+=numAtRank-1) {
				int curr=ft.select(loop);
				if (curr==ft.size()-1) break;
				ft.update(curr, -1);
			}
		}

		boolean [] isLucky=new boolean [MAX];
		ArrayList<Integer> luckyNumbers=new ArrayList<>();
		for (int i=1;i<MAX;i++) if (ft.rsqWithCache(i-1,i)==1) {
			isLucky[i]=true;
			luckyNumbers.add(i);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			boolean found=false;
			int left=-1;
			if (N%2==0) {
				int idx=searchLessOrEqual(luckyNumbers, N/2);
				for (int i=idx;i>=0;i--) {
					int curr=luckyNumbers.get(i);
					if (isLucky[N-curr]) {
						left=curr;
						found=true;
						break;
					}
				}
			}
			
			if (!found) System.out.printf("%d is not the sum of two luckies!\n", N);
			else System.out.printf("%d is the sum of %d and %d.\n",N,left,N-left);
		}
	}
}
