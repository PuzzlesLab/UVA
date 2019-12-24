import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] sq=new int [225];  //Ceil(Math.sqrt(50000)) = 224
		for (int i=0;i<sq.length;i++) sq[i]=i*i;
		
		String [] table=new String[50001];
		
		for (int i=0;i<=224;i++) for (int i2=i;i2<=224;i2++) for (int i3=i2;i3<=224;i3++) {
			int sum=sq[i]+sq[i2]+sq[i3];
			if (sum<table.length && table[sum]==null) {
				StringBuilder sb=new StringBuilder();
				sb.append(i);
				sb.append(' ');
				sb.append(i2);
				sb.append(' ');
				sb.append(i3);
				table[sum]=sb.toString();
			}
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int i=Integer.parseInt(br.readLine());
			sb.append(table[i]==null ? "-1" : table[i]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}