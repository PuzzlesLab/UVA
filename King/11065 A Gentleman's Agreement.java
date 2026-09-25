import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class BitSet {
		long low;
		long high;
		
		public void set(int pos) {
			if (pos<63) low|=1L<<pos;
			else high|=1L<<(pos-63);
		}
		
		public int count() {
			return Long.bitCount(low)+Long.bitCount(high);
		}
		
		public boolean isSet(int pos) {
			if (pos<63) return (low&(1L<<pos))!=0;
			return (high&(1L<<(pos-63)))!=0;
		}
		
		public BitSet or(BitSet bs) {
			BitSet r=new BitSet();
			r.high=this.high|bs.high;
			r.low=this.low|bs.low;
			return r;
		}
	}

	private static int I;
	private static BitSet [] Selections;
	private static int Count=0;
	private static int MaxSize=0;
	
	private static void count(int curr, int visit, BitSet mask) {
		if (mask.count()==I) {
			Count++;
			MaxSize=Math.max(MaxSize,visit);
			return;
		}
		// Pick any unselected intersection. i starts with curr to prevent same combi.
		for (int i=curr;i<I;i++) if (!mask.isSet(i)) count(i,visit+1,mask.or(Selections[i]));
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	I=Integer.parseInt(st.nextToken());
        	int R=Integer.parseInt(st.nextToken());
        	
        	Selections=new BitSet [I];
        	for (int i=0;i<Selections.length;i++) {
        		Selections[i]=new BitSet();
        		Selections[i].set(i);
        	}
        	for (int r=0;r<R;r++) {
        		st=new StringTokenizer(br.readLine());
        		int n1=Integer.parseInt(st.nextToken());
        		int n2=Integer.parseInt(st.nextToken());
        		Selections[n1].set(n2);
        		Selections[n2].set(n1);
        	}

        	Count=0;
        	MaxSize=0;
        	count(0,0,new BitSet());
        	
        	System.out.println(Count);
        	System.out.println(MaxSize);
        }
	}

}