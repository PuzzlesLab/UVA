import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			if (N<4 || (N%2==1)) {
				System.out.println("Impossible");
				continue;
			}
			
			int count=(N/2)*3;
			StringBuilder sb=new StringBuilder();
			sb.append(count);
			sb.append('\n');
			int diff=N/2-1;
			int start=1;
			while (count>0) {
				for (int i=start+1;i<=N && count>0;i+=diff) {
					sb.append(start);
					sb.append(' ');
					sb.append(i);
					sb.append('\n');
					count--;
				}
				start++;
			}
			System.out.print(sb.toString());
		}
	}
}
