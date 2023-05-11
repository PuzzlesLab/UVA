import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		// Issue : Time limit to AC is too tight and we have to apply optimization purposely.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int l=N-1;
			int l2=(int)(Math.log10(N-1)/Math.log10(2)); // log10 is faster than log.
			sb.append(l+l2);
			sb.append('\n');
		}
		System.out.print(sb.toString()); // Only print out when all are done. Cut execution time into half.
	}

}
