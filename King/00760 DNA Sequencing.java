import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {

	private static void sort(int [] sa, int [] ra, int p) {
		int [] count=new int [10000];
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
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			String s2=br.readLine();
			StringBuilder sb=new StringBuilder(s);
			sb.append('$');
			sb.append(s2);
			sb.append('#');
			
			String s3=sb.toString();
			int [] sa=makeSA(s3);
			int [] lcp=makeLCP(s3,sa);

			int maxLCP=0;
			for (int i=1;i<lcp.length;i++) {
				int owner1=sa[i]>=s.length()+1?2:1;
				int owner2=sa[i-1]>=s.length()+1?2:1;
				if (owner1!=owner2) maxLCP=Math.max(lcp[i],maxLCP);
			}
			
			TreeSet<String> ansSet=new TreeSet<>();
			if (maxLCP>0) {
				for (int i=1;i<lcp.length;i++) {
					int owner1=sa[i]>=s.length()+1?2:1;
					int owner2=sa[i-1]>=s.length()+1?2:1;
					if (owner1!=owner2 && lcp[i]==maxLCP) ansSet.add(s3.substring(sa[i],sa[i]+lcp[i]));
				}
			}
			
			sb.setLength(0);
			if (first) first=false;
			else sb.append('\n');
			for (String ans: ansSet) {
				sb.append(ans);
				sb.append('\n');
			}
			if (ansSet.isEmpty()) sb.append("No common sequence.\n");

			System.out.print(sb);
			
			br.readLine(); // Empty
		}
	}
}