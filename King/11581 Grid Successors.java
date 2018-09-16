import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static int encode(int [][] ints) {
		int value=0;
		for (int i=0;i<ints.length;i++) for (int i2=0;i2<ints.length;i2++) value=(value << 1) + ints[i][i2];
		return value;
	}
	
	public static int [][] f(int [][] ints) {
		int [][] newInts=new int [ints.length][ints.length];
		for (int i=0;i<ints.length;i++) for (int i2=0;i2<ints.length;i2++) {
			int sum=0;
			if (i>0) sum+=ints[i-1][i2];
			if (i<ints.length-1) sum+=ints[i+1][i2];
			if (i2>0) sum+=ints[i][i2-1];
			if (i2<ints.length-1) sum+=ints[i][i2+1];
			
			newInts[i][i2]=sum%2;
		}
		return newInts;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //empty
			
			int [][] ints=new int [3][3];
			for (int i=0;i<ints.length;i++) {
				String s=br.readLine();
				for (int i2=0;i2<ints.length;i2++) ints[i][i2]=s.charAt(i2)-'0';
			}
			
			int [] position=new int [512];
			Arrays.fill(position, -1);
			position[encode(ints)]=0;
			int ans=-1;
			for (int i=0;true;i++) {
				ints=f(ints);
				int index=encode(ints);
				if (position[index]!=-1) {
					ans=i-1;
					break;
				} else position[index]=i;
			}
			
			System.out.println(ans);
		}
	}

}
