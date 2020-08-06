import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int answer=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			int correct=0;
			for (int i=0;i<5;i++) correct+=(Integer.parseInt(st.nextToken()) == answer)?1:0;
			System.out.println(correct);
		}
	}

}