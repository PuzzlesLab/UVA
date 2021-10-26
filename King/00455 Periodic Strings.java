import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			char [] line=br.readLine().toCharArray();
			int ans=line.length;
			for (int size=1;size<line.length;size++) if (line.length%size==0) {
				boolean flag=true;
				for (int start=size;start<line.length && flag;start+=size) {
					for (int delta=0;delta<size && flag;delta++) {
						flag &= line[delta]==line[start+delta];
					}
				}
				if (flag) {
					ans=size;
					break;
				}
			}
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}
