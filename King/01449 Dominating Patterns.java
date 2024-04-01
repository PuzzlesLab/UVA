import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int calc(String pattern, String line) {
		int [] back=new int [pattern.length()+1];
		back[0]=-1;
		int i=0;
		int i2=-1;
		while (i<pattern.length()) {
			while (i2>=0 && pattern.charAt(i)!=pattern.charAt(i2)) i2=back[i2];
			back[++i]=++i2;
		}
		
		i=0;
		i2=0;
		int count=0;
		while (i<line.length()) {
			while (i2>=0 && line.charAt(i)!=pattern.charAt(i2)) i2=back[i2];
			i++;
			i2++;
			if (i2==pattern.length()) {
				count++;
				i2=back[i2];
			}
		}
		return count;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			String [] patterns=new String [N];
			for (int n=0;n<N;n++) patterns[n]=br.readLine();
			
			String line=br.readLine();
			int [] count=new int [N];
			int max=0;
			for (int n=0;n<N;n++) {
				count[n]=calc(patterns[n],line);
				max=Math.max(count[n],max);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append(max);
			sb.append('\n');
			for (int n=0;n<N;n++) if (count[n]==max) {
				sb.append(patterns[n]);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}