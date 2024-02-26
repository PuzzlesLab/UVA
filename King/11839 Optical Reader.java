import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int [] values=new int [5];
				ArrayList<Character> filled=new ArrayList<>();
				ArrayList<Character> unfilled=new ArrayList<>();

				for (int i=0;i<values.length;i++) {
					values[i]=Integer.parseInt(st.nextToken());
					if (values[i]<=127) filled.add((char)('A'+i));
					else unfilled.add((char)('A'+i));
				}
				
				if (filled.size()==1 && unfilled.size()==4) sb.append(filled.get(0));
				else sb.append('*');
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}
}