import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());

			int charCountOnCurrLine=0;
			int currLineOnCurrPage=0;
			int currPage=1;
			for (int n=0;n<N;n++) {
				String currWord=st.nextToken();
				int testC=charCountOnCurrLine;
				if (testC!=0) testC++;
				testC+=currWord.length();
				if (testC<=C) {
					charCountOnCurrLine=testC;
				} else {
					charCountOnCurrLine=currWord.length();
					currLineOnCurrPage++;
					if (currLineOnCurrPage==L) {
						currLineOnCurrPage=0;
						currPage++;
					}
				}
			}
			
			System.out.println(currPage);
		}
	}

}