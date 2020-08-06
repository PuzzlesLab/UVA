import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			int O=Integer.parseInt(st.nextToken());
			System.out.println((H>O-P) ? "Hunters win!" : "Props win!");
		}
	}

}