import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		char [] notes= {'c','d','e','f','g','a','b','C','D','E','F','G','A','B'};
		int [] noteToIndex=new int [128];
		for (int i=0;i<notes.length;i++) noteToIndex[notes[i]]=i;
		
		String [] finger= { "0111001111","0111001110",
							"0111001100","0111001000",
							"0111000000","0110000000",
							"0100000000","0010000000",
							"1111001110","1111001100",
							"1111001000","1111000000",
							"1110000000","1100000000"};
		
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [] fingerCount=new int [10];
			boolean [] fingerUsed=new boolean[10];
			char [] ch=br.readLine().toCharArray();
			for (char c : ch) {
				int idx=noteToIndex[c];
				for (int i=0;i<10;i++) if (finger[idx].charAt(i)=='1') {
					if (!fingerUsed[i]) fingerCount[i]++;
					fingerUsed[i]=true;
				} else fingerUsed[i]=false;
			}
			
			StringBuilder sb=new StringBuilder();
			for (int fc : fingerCount) {
				sb.append(fc);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
