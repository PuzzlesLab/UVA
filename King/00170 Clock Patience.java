import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			LinkedList<String> [] pile=new LinkedList [13];
			for (int i=0;i<pile.length;i++) pile[i]=new LinkedList<>();
			for (int i=0;i<4;i++) {
				StringTokenizer st=new StringTokenizer(s);
				for (int i2=pile.length-1;i2>=0;i2--) pile[i2].addLast(st.nextToken());
				if (i<3) s=br.readLine();
			}

			int [] rankToID=new int [128];
			for (int i='2';i<='9';i++) rankToID[i]=i-'1';
			rankToID['A']=0; rankToID['T']=9; rankToID['J']=10; rankToID['Q']=11; rankToID['K']=12;
			
			int count=0;
			int currPos=pile.length-1;
			while (true) {
				count++;
				String currCard=pile[currPos].removeFirst();
				currPos=rankToID[currCard.charAt(0)];
				
				if (pile[currPos].isEmpty()) {
					System.out.printf("%02d,%s\n",count,currCard);
					break;
				}
			}
			
		}
	}

}
