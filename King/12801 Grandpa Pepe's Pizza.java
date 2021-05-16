import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			int size=C/N;
			
			st=new StringTokenizer(br.readLine());
			int [] olive=new int [N];
			for (int n=0;n<N;n++) olive[n]=Integer.parseInt(st.nextToken());
			
			boolean flag=false;
			for (int start=0;start<C && !flag;start++) {
				int temp=start;
				
				flag=true;
				for (int n=0;n<N && flag;n++) {
					if (temp+size>=C) flag=(olive[n]>temp && olive[n]<=C) || (olive[n]>=0 && olive[n]<=(temp+size)%C);
					else flag=olive[n]>temp && olive[n]<=temp+size;
					temp=(temp+size)%C;
				}
			}
			
			System.out.println(flag ? 'S' : 'N');
		}
	}
}
