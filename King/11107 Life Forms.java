import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

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

	private static void updateOwner(int [] sa, int [] owner) {
		int [] temp=new int [owner.length];
		for (int i=0;i<sa.length;i++) temp[i]=owner[sa[i]];
		for (int i=0;i<sa.length;i++) owner[i]=temp[i];
	}

	private static String decode(String s, int N) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c>=N) sb.append((char)(c+'a'-N));
			else sb.append((int)c);
		}
		return sb.toString();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean first=true;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			if (N==1) {
				if (first) first=false;
				else System.out.println();
				System.out.println(br.readLine());
				continue;
			}
			
			StringBuilder sb=new StringBuilder();
			int [] dnaLen=new int [N];
			for (int n=0;n<N;n++) {
				String s=br.readLine();
				for (int i=0;i<s.length();i++) sb.append((char)(s.charAt(i)-'a'+N));
				sb.append((char)n);
				dnaLen[n]=s.length()+1;
			}

			int [] owner=new int [sb.length()];
			int temp=0;
			for (int n=0;n<N;n++) {
				while (dnaLen[n]>0) {
					owner[temp++]=n;
					dnaLen[n]--;
				}
			}
				
			String s=sb.toString();
			int [] sa=makeSA(s);
			updateOwner(sa,owner);
			int [] lcp=makeLCP(s,sa);

			int minK=(N>>1);
			TreeSet<String> ansSet=new TreeSet<>();
			int maxLCP=0;
			for (int i=1;i<s.length();i++) if (lcp[i]>0 && lcp[i]!=lcp[i-1] && lcp[i]>=maxLCP) {
				HashSet<Integer> owners=new HashSet<>();

				// Search prev and next
				int i2=i;
				while (i2-1>=0 && lcp[i2-1]>=lcp[i] && owners.size()<=minK) { // If size > minK, don't waste time for search further.
					owners.add(owner[i2-1]);
					i2--;
				}
				owners.add(owner[i2-1]);
				i2=i;
				while (i2<s.length() && lcp[i2]>=lcp[i] && owners.size()<=minK) {
					owners.add(owner[i2]);
					i2++;
				}

				if (owners.size()>minK) {
					if (maxLCP<lcp[i]) ansSet.clear();
					maxLCP=lcp[i];
					String text=decode(s.substring(sa[i],sa[i]+lcp[i]),N);
					ansSet.add(text);
				}
			}
			
			sb.setLength(0);
			if (first) first=false;
			else sb.append('\n');
			for (String text: ansSet) {
				sb.append(text);
				sb.append('\n');
			}
			if (ansSet.isEmpty()) sb.append("?\n");
			System.out.print(sb);
		}
	}

}
