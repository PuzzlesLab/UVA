import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int tx=Integer.parseInt(st.nextToken());
			int ty=0;
			int tu=Integer.parseInt(st.nextToken());
			int tv=Integer.parseInt(st.nextToken());
			
			int cx=0;
			int cy=0;
			int cu=0;
			int cv=0;

			int time=0;
			while (cx<tx || cy<ty) {
				time++;

				tx+=tu;
				ty+=tv;
				
				if (cx+cu<tx) cu++;
				cx+=cu;

				if (cy+cv<ty) cv++;
				cy+=cv;
			}
			
			System.out.println(time);
		}
	}

}