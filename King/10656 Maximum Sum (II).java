import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n++) {
				int num=Integer.parseInt(br.readLine());
				if (num!=0) {
					sb.append(num);
					sb.append(' ');
				}
			}
			if (sb.length()==0) System.out.println(0);
			else {
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
		}
	}

}