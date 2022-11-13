import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Choices;
	private static int [] DpLength;
	private static long [] DpWays;

	private static int findLongest(int currIdx) {
		if (DpLength[currIdx]!=-1) return DpLength[currIdx];
		
		DpWays[currIdx]=1;
		int length=1;
		int v1=Choices[currIdx]*5;
		for (int i=currIdx+1;i<Choices.length;i++) {
			int v2=Choices[i]*4;
			if (v1<=v2) {
				int nLength=1+findLongest(i);
				if (nLength>length) {
					DpWays[currIdx]=DpWays[i];
					length=nLength;
				} else if (length==nLength) {
					DpWays[currIdx]+=DpWays[i];
				}
			}
		}
		return DpLength[currIdx]=length;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			boolean [] existsChar=new boolean [26];
			int choiceCount=0;
			String s=br.readLine();

			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (Character.isAlphabetic(c)) {
					int cIdx=c-'A';
					if (!existsChar[cIdx]) {
						existsChar[cIdx]=true;
						choiceCount++;
					}
				}
			}
			
			if (choiceCount==0) {
				System.out.println("0 0");
				continue;
			}

			Choices=new int [choiceCount];
			choiceCount=0;
			for (int i=0;i<existsChar.length;i++) if (existsChar[i]) Choices[choiceCount++]=i+1;

			DpLength=new int [choiceCount];
			Arrays.fill(DpLength,-1);
			DpWays=new long [choiceCount];

			int length=findLongest(0);
			long ways=0;
			for (int i=0;i<choiceCount;i++) if (findLongest(i)==length) ways+=DpWays[i];

			System.out.printf("%d %d\n",length,ways);
		}
	}

}