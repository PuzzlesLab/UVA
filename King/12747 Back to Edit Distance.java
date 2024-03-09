import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int lowerBound(ArrayList<Integer> list, int v) {
		if (list.isEmpty()) return 0;
		if (list.get(list.size()-1)<v) return list.size();
		
		int min=0;
		int max=list.size()-1;
		int mid=0;
		while (min<max) {
			mid=(min+max)>>1;
			if (list.get(mid)>=v) max=mid;
			else min=mid+1;
		}
		if (mid<list.size() && list.get(mid)<v) mid++;
		return mid;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int len=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] s1=new int [len];
			int [] s1Idx=new int [len];
			for (int i=0;i<s1.length;i++) {
				s1[i]=Integer.parseInt(st.nextToken())-1;
				s1Idx[s1[i]]=i;
			}

			st=new StringTokenizer(br.readLine());
			int [] s2Idx=new int [len];
			for (int i=0;i<len;i++) {
				int v=Integer.parseInt(st.nextToken())-1;
				s2Idx[i]=s1Idx[v];
			}
			
			ArrayList<Integer> seq=new ArrayList<>();
			for (int i=0;i<len;i++) {
				int v=s2Idx[i];
				int seqIdx=lowerBound(seq,v);
				if (seqIdx==seq.size()) seq.add(v);
				else seq.set(seqIdx,v);
			}
			int ans=(len-seq.size())*2;
			System.out.printf("Case %d: %d\n",tc,ans);
		}
	}
}