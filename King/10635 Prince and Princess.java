import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	private static int findLteIdx(ArrayList<Integer> list, int lte) {
		if (list.isEmpty()) return 0;
		
		int min=0;
		int max=list.size();
		int mid=0;
		while (min<max) {
			mid=(min+max)>>1;
			if (list.get(mid)>=lte) max=mid;
			else min=mid+1;
		}
		if (mid<list.size() && list.get(mid)<lte) mid++;
		return mid;
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int TC=sc.nextInt();
		for (int tc=1;tc<=TC;tc++) {
			int N=sc.nextInt();
			int P=sc.nextInt()+1;
			int Q=sc.nextInt()+1;
			
			int [] s1=new int [P];
			int [] s1Idx=new int [N*N+1];
			Arrays.fill(s1Idx,-1);
			for (int p=0;p<P;p++) {
				s1[p]=sc.nextInt();
				s1Idx[s1[p]]=p;
			}
			
			ArrayList<Integer> s2Idx=new ArrayList<>();
			for (int q=0;q<Q;q++) {
				int v=sc.nextInt();
				if (s1Idx[v]!=-1) s2Idx.add(s1Idx[v]);
			}

			// Do LIS
			int len=0;
			ArrayList<Integer> seq=new ArrayList<>();
			for (int i=0;i<s2Idx.size();i++) {
				int v=s2Idx.get(i);
				int seqIdx=findLteIdx(seq,v);
				if (seqIdx==seq.size()) seq.add(v);
				else seq.set(seqIdx,v);
				len=Math.max(len,seq.size());
			}

			StringBuilder ans=new StringBuilder();
			ans.append("Case ");
			ans.append(tc);
			ans.append(": ");
			ans.append(len);
			System.out.println(ans);
		}
	}
}