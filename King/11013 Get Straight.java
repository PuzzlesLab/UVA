import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class uva {
	
	private static class Card {
		private static int [] lookup;
		String str;
		int value;
		
		public static void initializeLookup() {
			lookup=new int [128];
			lookup['A']=0; lookup['T']=9; lookup ['J']=10; lookup['Q']=11; lookup['K']=12;
			for (int i='2';i<='9';i++) lookup[i]=i-'1';
		}
		
		public Card(String s) {
			this.str=s;
			this.value=Card.lookup[this.str.charAt(0)];
		}
		
		public Card(int v) {
			this.value=v;
		}
	}
	
	private static int calcEarning (Card [] cards) {
		int [] valueCount=new int [13];
		for (Card c : cards) valueCount[c.value]++;
		for (int i=0;i<valueCount.length;i++) if (valueCount[i]==1) {
			boolean straight=true;
			for (int delta=1;delta<5 && straight;delta++) straight&=valueCount[(i+delta)%13]==1;
			if (straight) return 100;
		}
		
		for (int i=0;i<valueCount.length;i++) if (valueCount[i]>0) {
			boolean neighbour=true;
			for (int delta=1;delta<4 && neighbour;delta++) neighbour&=valueCount[(i+delta)%13]>=1;
			if (neighbour) return 10;
		}
		
		for (int i=0;i<valueCount.length;i++) if (valueCount[i]>0) {
			boolean bnb=true;
			for (int delta=1;delta<3 && bnb;delta++) bnb&=valueCount[(i+delta)%13]>=1;
			if (bnb) {
				int ret=3;
				for (int delta=0;delta<3;delta++) valueCount[(i+delta)%13]-=1;
				
				for (int v=0;v<valueCount.length;v++) if (valueCount[v]==1 && valueCount[(v+1)%13]==1) {
					ret=5;
					break;
				}

				for (int delta=0;delta<3;delta++) valueCount[(i+delta)%13]+=1;
				return ret;
			}
		}
		
		int ddCount=0;
		for (int i=0;i<valueCount.length;i++) {
			while (valueCount[i]>0 && valueCount[(i+1)%13]>0) {
				ddCount++;
				valueCount[i]--;
				valueCount[(i+1)%13]--;
			}
		}
		if (ddCount==2) return 1;
		return 0;
	}
	
	public static void main (String [] args) throws Exception {
		Card.initializeLookup();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			int [] cardsLeft=new int[13];
			Arrays.fill(cardsLeft,4);
			
			StringTokenizer st=new StringTokenizer(s);
			Card [] cards=new Card[5];
			for (int i=0;i<cards.length;i++) {
				cards[i]=new Card(st.nextToken());
				cardsLeft[cards[i].value]--;
			}
			
			String exchangeMaxEarning=null;
			double maxEarning=calcEarning(cards)-1;
			for (int exchangeIdx=0;exchangeIdx<cards.length;exchangeIdx++) {
				if (exchangeIdx>0 && cards[exchangeIdx].value==cards[exchangeIdx-1].value) continue;
				
				double expectedEarning=0.0;
				for (int i=0;i<13;i++) if (cardsLeft[i]>0) {
					Card [] testCards=Arrays.copyOf(cards, cards.length);
					testCards[exchangeIdx]=new Card(i);

					double currExpectedEarning=calcEarning(testCards)*(cardsLeft[i]/47.0);
					expectedEarning+=currExpectedEarning;
				}
				expectedEarning-=2;
				if (expectedEarning>maxEarning) {
					exchangeMaxEarning=cards[exchangeIdx].str;
					maxEarning=expectedEarning;
				}
			}
			
			System.out.println(exchangeMaxEarning!=null ? "Exchange "+exchangeMaxEarning : "Stay");
		}
	}

}