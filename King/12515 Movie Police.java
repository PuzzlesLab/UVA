import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int M=Integer.parseInt(st.nextToken());
		int Q=Integer.parseInt(st.nextToken());
		char [][] sig=new char[M][];
		for (int m=0;m<M;m++) sig[m]=br.readLine().toCharArray();
		
		for (int q=0;q<Q;q++) {
			char [] ch=br.readLine().toCharArray();
			int [] similarity=new int [M];
			for (int m=0;m<M;m++) if (sig[m].length>=ch.length) {
				for (int leftPad=0;leftPad<=sig[m].length-ch.length;leftPad++) {
					int count=0;
					for (int pos=0;pos<ch.length;pos++) if (sig[m][pos+leftPad]==ch[pos]) count++;
					similarity[m]=Math.max(similarity[m],count);
				}
			}
			
			int maxIdx=0;
			for (int i=1;i<M;i++) if (similarity[i]>similarity[maxIdx]) maxIdx=i;
			System.out.println(maxIdx+1);
		}
	}

}