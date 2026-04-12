import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Values;
	private static int [] SuitValues;
	private static String [] ComboText;
	private static int Ans;
	private static String [] Hand;
	private static String [] Deck;

	private static class CombiStat {
		String [] cards;
		int [] suitCount;
		int [] valueCount;
		int twoCount;
		int threeCount;

		private static int compareCards(String x, String y) {
			if (Values[x.charAt(0)]!=Values[y.charAt(0)]) return Values[x.charAt(0)]-Values[y.charAt(0)];
			return SuitValues[x.charAt(1)]-SuitValues[y.charAt(1)];
		}

		public CombiStat(String [] cards) {
			Arrays.sort(cards,(x,y)->compareCards(x,y));
			this.cards=cards;
			this.suitCount=new int [5];
			this.valueCount=new int [14];

			for (int i=0;i<cards.length;i++) {
				valueCount[Values[cards[i].charAt(0)]]++;
				suitCount[SuitValues[cards[i].charAt(1)]]++;
			}

			this.twoCount=0;
			this.threeCount=0;
			for (int i=0;i<this.valueCount.length;i++) {
				if (this.valueCount[i]==2) twoCount++;
				else if (this.valueCount[i]==3) threeCount++;
			}
		}
	}

	private static boolean straightFlush(CombiStat stat) {
		return flush(stat) && straight(stat);
	}

	private static boolean fourOfAKind(CombiStat stat) {
		for (int i=0;i<stat.valueCount.length;i++) if (stat.valueCount[i]==4) return true;
		return false;
	}

	private static boolean fullHouse(CombiStat stat) {
		return stat.twoCount==1 && stat.threeCount==1;
	}

	private static boolean flush(CombiStat stat) {
		for (int i=0;i<stat.suitCount.length;i++) if (stat.suitCount[i]==5) return true;
		return false;
	}

	private static boolean straight(CombiStat stat) {
		int lastV=Values[stat.cards[0].charAt(0)];
		boolean flag=true;
		for (int i=1;i<stat.cards.length && flag;i++) {
			int currV=Values[stat.cards[i].charAt(0)];
			flag&=lastV+1==currV;
			lastV=currV;
		}
		if (flag) return true;
		
		// Test T to A.
		return stat.cards[1].charAt(0)=='T'
				&& stat.cards[2].charAt(0)=='J'
				&& stat.cards[3].charAt(0)=='Q'
				&& stat.cards[4].charAt(0)=='K'
				&& stat.cards[0].charAt(0)=='A';
	}

	private static boolean threeOfAKind(CombiStat stat) {
		return stat.threeCount==1;
	}

	private static boolean twoPairs(CombiStat stat) {
		return stat.twoCount==2;
	}

	private static boolean onePair(CombiStat stat) {
		return stat.twoCount==1;
	}

	public static void main(String[] args) throws Exception {
		Values=new int [128];
		Values['A']=1;
		for (int i='2';i<='9';i++) Values[i]=i-'0';
		Values['T']=10;
		Values['J']=11;
		Values['Q']=12;
		Values['K']=13;

		SuitValues=new int [128];
		SuitValues['C']=1;
		SuitValues['D']=2;
		SuitValues['H']=3;
		SuitValues['S']=4;

		ComboText=new String [10];
		ComboText[0]="";
		ComboText[1]="highest-card";
		ComboText[2]="one-pair";
		ComboText[3]="two-pairs";
		ComboText[4]="three-of-a-kind";
		ComboText[5]="straight";
		ComboText[6]="flush";
		ComboText[7]="full-house";
		ComboText[8]="four-of-a-kind";
		ComboText[9]="straight-flush";

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Hand=new String [5];
			Deck=new String [5];
			for (int i=0;i<5;i++) Hand[i]=st.nextToken();
			for (int i=0;i<5;i++) Deck[i]=st.nextToken();

			Ans=1;
			String [] combi=new String [5];
			for (int mask=0;mask<32;mask++) {
				int combiIdx=0;
				int deckIdx=0;
				for (int i=0;i<5;i++) {
					if ((mask&(1<<i))==0) combi[combiIdx++]=Deck[deckIdx++];
					else combi[combiIdx++]=Hand[i];
				}
				CombiStat stat=new CombiStat(combi);
				
				int curr=1;
				if (straightFlush(stat)) curr=9;
				else if (fourOfAKind(stat)) curr=8;
				else if (fullHouse(stat)) curr=7;
				else if (flush(stat)) curr=6;
				else if (straight(stat)) curr=5;
				else if (threeOfAKind(stat)) curr=4;
				else if (twoPairs(stat)) curr=3;
				else if (onePair(stat)) curr=2;
				Ans=Math.max(Ans,curr);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Hand:");
			for (int i=0;i<Hand.length;i++) {
				sb.append(' ');
				sb.append(Hand[i]);
			}
			sb.append(" Deck:");
			for (int i=0;i<Deck.length;i++) {
				sb.append(' ');
				sb.append(Deck[i]);
			}
			sb.append(" Best hand: ");
			sb.append(ComboText[Ans]);
			System.out.println(sb);
		}
	}

}