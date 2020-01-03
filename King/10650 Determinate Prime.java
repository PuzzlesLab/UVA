import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static boolean [] notPrime=new boolean [40001];
	public static int [] primeIndex=new int [40001];
	
	public static void writeStr(LinkedList<Integer> seq, StringBuilder sb, int min, int max) {
		if (seq.size()>2) {
			int diff=seq.get(1)-seq.getFirst();
			int extendLeft=seq.getFirst()-diff;
			if (!notPrime[extendLeft] && primeIndex[seq.getFirst()]-1==primeIndex[extendLeft] && extendLeft<min) return;

			int extendRight=seq.getLast()+diff;
			if (!notPrime[extendRight] && primeIndex[seq.getLast()]+1==primeIndex[extendRight] && extendRight>max) return;
			
			for (int n : seq) {
				sb.append(n);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
		}
	}
	
	public static void main (String [] args) throws Exception {
		notPrime[0]=true;
		notPrime[1]=true;
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		int idx=1;
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primeIndex[i]=idx++;
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if (a==0 && b==0) break;
			
			int min=Math.min(a,b);
			int max=Math.max(a,b);

			StringBuilder sb=new StringBuilder();
			if (min<max) {
				LinkedList<Integer> seq=new LinkedList<>();
				int seqDiff=-1;
				for (int i=min;i<=max;i++) if (!notPrime[i]) {
					if (seq.size()<2) {
						seq.add(i);
						if (seq.size()==2) seqDiff=i-seq.getFirst();
					} else {
						int currDiff=i-seq.getLast();
						if (currDiff!=seqDiff) {
							if (seq.size()==2) {
								seq.removeFirst();
								seqDiff=currDiff;
							} else {
								writeStr(seq,sb,min,max);
								int last=seq.getLast();
								seq.clear();
								seq.addLast(last);
							}
						}
						seq.addLast(i);
						if (seq.size()==2) seqDiff=i-seq.getFirst();
					}
				}
				writeStr(seq,sb,min,max);
			}
			
			System.out.print(sb.toString());
		}
	}

}