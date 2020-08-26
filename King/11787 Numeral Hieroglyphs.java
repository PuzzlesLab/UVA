import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] values=new int [128];
		values['B']=1;
		values['U']=10;
		values['S']=100;
		values['P']=1000;
		values['F']=10000;
		values['T']=100000;
		values['M']=1000000;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [] ch=br.readLine().toCharArray();
			boolean error=false;
			
			for (char c : ch) if (values[c]==0) {
				error=true;
				break;
			}
			
			if (!error) {
				boolean nonIncreasing=true;
				boolean nonDecreasing=true;
				for (int i=1;i<ch.length;i++) {
					nonIncreasing &= values[ch[i]]>=values[ch[i-1]];
					nonDecreasing &= values[ch[i]]<=values[ch[i-1]];
				}
				if (!nonIncreasing && !nonDecreasing) error=true;
			}

			if (!error) {
				int [] chCount=new int [128];
				for (char c : ch) {
					chCount[c]++;
					if (chCount[c]>9) {
						error=true;
						break;
					}
				}
			}
			
			if (!error) {
				int sum=0;
				for (char c : ch) sum+=values[c];
				System.out.println(sum);
			} else System.out.println("error");
		}
	}
}