import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==0) break;
			
			double maxSize=-1;
			int sol=-1;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				double w=Integer.parseInt(st.nextToken());
				double h=Integer.parseInt(st.nextToken());
				
				double min=Math.min(w,h);
				double max=Math.max(w,h);
				double size=0;
				if (min*4<=max)size=min;
				else size=Math.max(min/2,max/4);

				if (size>maxSize) {
					maxSize=size;
					sol=n+1;
				}
			}
			System.out.println(sol);
		}
 	}

}