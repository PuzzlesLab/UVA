import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			int [] startPos=new int [N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) startPos[Integer.parseInt(st.nextToken())-1]=i;
			
			int [] endPos=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) endPos[Integer.parseInt(st.nextToken())-1]=i;
			
			int overtake=0;
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (i!=i2 && startPos[i]>startPos[i2] && endPos[i]<endPos[i2]) overtake++;
			
			System.out.println(overtake);
		}
	}

}