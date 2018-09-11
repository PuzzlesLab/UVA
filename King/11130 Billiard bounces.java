import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	String s;
    	while (!(s=br.readLine()).equals("0 0 0 0 0")) {
    		StringTokenizer st=new StringTokenizer(s);
    		double a=Integer.parseInt(st.nextToken());
    		double b=Integer.parseInt(st.nextToken());
    		double v=Integer.parseInt(st.nextToken());
    		double A=((90.0-Integer.parseInt(st.nextToken()))/180.0)*Math.PI;
    		double S=Integer.parseInt(st.nextToken());

    		double L=(v*S)/2;
    		double at=L*Math.sin(A);
    		double bt=L*Math.cos(A);
    		
    		int aa=((int)((at/a)+0.5));
    		int bb=((int)((bt/b)+0.5));
    		System.out.println(aa+" "+bb);
    	}
    }

}