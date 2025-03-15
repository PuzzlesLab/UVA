import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static double getCircle(double a, double b, double c) {
		double [] values= {a,b,c};
		Arrays.sort(values);
		if (values[0]*values[0]+values[1]*values[1]<values[2]*values[2]) return 0.5*values[2]; // Obtuse triangle (Special case)

		double sp=0.5*(a+b+c);
		double area=Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
		return (a*b*c)/(4*area);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int M=Integer.parseInt(s);
			double [] hole=new double [M];

			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) hole[m]=0.5*Double.parseDouble(st.nextToken());

			StringBuilder sb=new StringBuilder();
			int N=Integer.parseInt(br.readLine());
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				double size=getCircle(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				ArrayList<Integer> fits=new ArrayList<>();
				for (int m=0;m<M;m++) if (size<=hole[m]) fits.add(m+1);

				sb.append("Peg ");
				sb.append((char)('A'+n));
				sb.append(" will ");
				if (fits.size()==0) sb.append("not fit into any holes");
				else {
					sb.append("fit into hole(s):");
					for (int i=0;i<fits.size();i++) {
						sb.append(' ');
						sb.append(fits.get(i));
					}
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}
 	}

}