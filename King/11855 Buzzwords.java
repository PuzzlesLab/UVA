import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static void sort(int [] sa, int [] ra, int p) {
		int [] count=new int [Math.max(300,sa.length)];
		for (int i=0;i<ra.length;i++) count[i+p<ra.length?ra[i+p]:0]++;
		int sum=0;
		for (int i=0;i<count.length;i++) {
			int temp=count[i];
			count[i]=sum;
			sum+=temp;
		}
		int [] tempSA=new int [sa.length];
		for (int i=0;i<sa.length;i++) tempSA[count[sa[i]+p<sa.length?ra[sa[i]+p]:0]++]=sa[i];
		for (int i=0;i<sa.length;i++) sa[i]=tempSA[i];
	}

	private static int [] makeSA(String s) { 
		int [] sa=new int [s.length()];
		for (int i=0;i<sa.length;i++) sa[i]=i;
		
		int [] ra=new int [s.length()];
		for (int i=0;i<ra.length;i++) ra[i]=s.charAt(i);
		
		for (int i=1;i<s.length();i<<=1) {
			sort(sa,ra,i);
			sort(sa,ra,0);

			int [] tempRA=new int [s.length()];
			int rIdx=0;
			for (int i2=1;i2<s.length();i2++) {
				tempRA[sa[i2]]=(ra[sa[i2]]==ra[sa[i2-1]] && ra[sa[i2]+i]==ra[sa[i2-1]+i]) ? rIdx : ++rIdx;
			}
			for (int i2=0;i2<s.length();i2++) ra[i2]=tempRA[i2];
			if (ra[sa[sa.length-1]]==sa.length-1) break;
		}
		return sa;
	}
	
	private static int [] makeLCP(String s, int [] sa) {
		int [] prev=new int [sa.length];
		int [] plcp=new int [sa.length];
		int same=0;
		prev[sa[0]]=-1;
		for (int i=1;i<sa.length;i++) prev[sa[i]]=sa[i-1];
		for (int i=0;i<sa.length;i++) {
			if (prev[i]==-1) continue;
			while (i+same<sa.length && prev[i]+same<sa.length && s.charAt(i+same)==s.charAt(prev[i]+same)) same++;
			plcp[i]=same;
			same=Math.max(same-1,0);
		}

		int [] lcp=new int [sa.length];
		for (int i=0;i<plcp.length;i++) lcp[i]=plcp[sa[i]];
		return lcp;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean first=true;
		while (true) {
			String s=br.readLine();
			if (s==null) break;
			
			StringTokenizer st=new StringTokenizer(s);
			StringBuilder sb=new StringBuilder();
			while (st.hasMoreTokens()) sb.append(st.nextToken());
			sb.append("#");
			s=sb.toString();

			int [] sa=makeSA(s);
			int [] lcp=makeLCP(s,sa);
			int maxLCP=0;
			for (int i=0;i<lcp.length;i++) maxLCP=Math.max(maxLCP,lcp[i]);

			sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			for (int len=1;len<sa.length;len++) {
				int max=1;
				for (int i=1;i<sa.length;i++) {
					if (sa.length-sa[i]<len) continue;
					int curr=1;
					while (i+1<sa.length && lcp[i+1]>=len) {
						i++;
						curr++;
					}
					max=Math.max(max,curr);
				}
				if (max==1) break;
				sb.append(max);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}