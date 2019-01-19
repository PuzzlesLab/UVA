import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class SegmentTree {
		int [] list, maxIndex;
		int listMaxIndex;
		
		public SegmentTree(int [] list) {
			this.list=list;
			this.maxIndex=new int [list.length*4];
			this.listMaxIndex=list.length-1;
			this.buildTree();
		}
		
		private void buildTree(int min, int max, int p) {
			if (min==max) maxIndex[p]=min;
			else {
				int mid=(min+max)>>1;
				int i1=p<<1;
				int i2=i1+1;
				this.buildTree(min, mid, i1);
				this.buildTree(mid+1, max, i2);
				maxIndex[p]=(list[maxIndex[i1]]<=list[maxIndex[i2]]) ? maxIndex[i2] : maxIndex[i1];
			}
		}
		public void buildTree() { this.buildTree(0, this.listMaxIndex, 1); }
		
		public int query(int p, int min, int max, int qMin, int qMax) {
			if (qMin>max || qMax<min) return -1;
			if (min>=qMin && max<=qMax) return this.maxIndex[p];
			
			int mid=(min+max)>>1;
			int q1=this.query(p<<1,min,mid,qMin,qMax);
			int q2=this.query((p<<1)+1,mid+1,max,qMin,qMax);
			if (q1==-1) return q2;
			if (q2==-1) return q1;
			return (this.list[q1]<=this.list[q2]) ? q2 : q1;
		}
		
		public int query(int qMin, int qMax) { return this.query(1, 0, this.listMaxIndex, qMin, qMax);}

	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			int [] nums=new int [N];
			int [] freqTable=new int [N];
			HashMap<Integer,Integer> numFirstIndex=new HashMap<>();
			HashMap<Integer,Integer> numLastIndex=new HashMap<>();
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				nums[n]=Integer.parseInt(st.nextToken());
				if (n==0 || nums[n-1]!=nums[n]) freqTable[n]=1;
				else freqTable[n]=freqTable[n-1]+1;
				
				if (!numFirstIndex.containsKey(nums[n])) numFirstIndex.put(nums[n], n);
				numLastIndex.put(nums[n], n);
			}
			for (int n=N-2;n>=0;n--) if (nums[n+1]==nums[n]) freqTable[n]=freqTable[n+1];
			
			SegmentTree tree=new SegmentTree(freqTable);
			
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int qMin=Integer.parseInt(st.nextToken())-1;
				int qMax=Integer.parseInt(st.nextToken())-1;
				if (nums[qMin]==nums[qMax]) System.out.println(qMax-qMin+1);
				else {
					int tempMin=qMin;
					if (numFirstIndex.get(nums[qMin])!=qMin) tempMin=numLastIndex.get(nums[qMin])+1;
					int tempMax=qMax;
					if (numLastIndex.get(nums[qMax])!=qMax) tempMax=numFirstIndex.get(nums[qMax])-1;

					int max=1;
					max=Math.max(max,tempMin-qMin);
					max=Math.max(max,qMax-tempMax);
					if (tempMax>=tempMin) max=Math.max(max,freqTable[tree.query(tempMin,tempMax)]);

					System.out.println(max);
				}
			}
		}
	}

}