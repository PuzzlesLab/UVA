import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			int left=(s.charAt(0)-'A')*26*26+(s.charAt(1)-'A')*26+(s.charAt(2)-'A');
			int right=(s.charAt(4)-'0')*1000+(s.charAt(5)-'0')*100+(s.charAt(6)-'0')*10+(s.charAt(7)-'0');
			int diff=Math.abs(left-right);
			System.out.println(diff<=100 ? "nice" : "not nice");
		}
	}
}
