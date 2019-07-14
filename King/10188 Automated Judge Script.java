import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int length=Integer.parseInt(s);
			
			StringBuilder expectedNum=new StringBuilder();
			StringBuilder expectedNonNum=new StringBuilder();
			for (int i=0;i<length;i++) {
				for (char c : br.readLine().toCharArray()) {
					if (c>='0' && c<='9') {
						expectedNum.append(c);
						expectedNonNum.append(' ');
					} else expectedNonNum.append(c);
				}
				expectedNonNum.append('\n');
			}

			length=Integer.parseInt(br.readLine());
			
			StringBuilder actualNum=new StringBuilder();
			StringBuilder actualNonNum=new StringBuilder();
			for (int i=0;i<length;i++) {
				for (char c : br.readLine().toCharArray()) {
					if (c>='0' && c<='9') {
						actualNum.append(c);
						actualNonNum.append(' ');
					} else actualNonNum.append(c);
				}
				actualNonNum.append('\n');
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Run #");
			sb.append(testCase++);
			sb.append(": ");

			if (expectedNum.toString().equals(actualNum.toString())) {
				if (expectedNonNum.toString().equals(actualNonNum.toString())) sb.append("Accepted");
				else sb.append("Presentation Error");
			} else sb.append("Wrong Answer");
			
			System.out.println(sb.toString());
		}
	}

}
