import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			s=st.nextToken(); s=s.substring(0, s.length()-1);
			int size=Integer.parseInt(s);
			
			int [] n=new int [size];
			int [] pos=new int [size];
			for (int i=0;i<size;i++) {
				n[i]=Integer.parseInt(st.nextToken());
				pos[n[i]]=i;
			}

			boolean flag=false;
			for (int start=0;start<size && !flag;start++) for (int delta=1;start+2*delta<size && !flag;delta++) {
				boolean b1=pos[start]-pos[start+delta]>0;
				boolean b2=pos[start+delta]-pos[start+delta+delta]>0;
				flag=b1 == b2;
			}
			
			if (flag) System.out.println("no");
			else System.out.println("yes");
		}
	}

}
