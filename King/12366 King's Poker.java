import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] cards= {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			Arrays.sort(cards);
			
			boolean hasSolution=true;
			if (cards[0]==cards[1] && cards[1]==cards[2]) {
				if (cards[0]==13) hasSolution=false;
				else for (int i=0;i<cards.length;i++) cards[i]++;
			} else if (cards[0]==cards[1] && cards[1]!=cards[2]) {
				if (cards[2]<13) {
					cards[2]++;
					if (cards[1]==cards[2]) cards[2]++;
					if (cards[2]>13) cards=new int [] {1,cards[0]+1,cards[1]+1};
				}
				else cards=new int [] {1,cards[0]+1,cards[1]+1};
			} else if (cards[0]!=cards[1] && cards[1]==cards[2]) {
				if (cards[0]<13) {
					cards[0]++;
					if (cards[0]==cards[1]) cards[0]++;
					if (cards[0]>13) {
						if (cards[1]+1<=13) cards=new int [] {1,cards[1]+1,cards[2]+1};
						else cards=new int [] {1,cards[1]+1,cards[2]+1};
						if (cards[1]>13) cards=new int[] {1,1,1};
					}
				}
				else cards=new int [] {1,cards[1]+1,cards[2]+1};
			} else {
				cards=new int [] {1,1,2};
			}
			Arrays.sort(cards);
			System.out.println(hasSolution? cards[0]+" "+ cards[1]+" "+cards[2] : "*");
		}
	}

}