import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double x0=Integer.parseInt(st.nextToken());
			double y0=Integer.parseInt(st.nextToken());
			double x1=Integer.parseInt(st.nextToken());
			double y1=Integer.parseInt(st.nextToken());
			double cx=Integer.parseInt(st.nextToken());
			double cy=Integer.parseInt(st.nextToken());
			double r=Integer.parseInt(st.nextToken());
			
			double width=x1-x0;
			double height=y1-y0;
			double ratio=height/width;
			double tcx=x0+(width)*0.45;
			double tcy=y0+(height)*0.5;
			double tr=width/5;
			
			System.out.println(ratio==0.6&&cx==tcx&&cy==tcy&&r==tr?"YES":"NO");
		}
 	}

}
