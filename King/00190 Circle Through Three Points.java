import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static String format1(double h, double k, double r) {
		StringBuilder sb=new StringBuilder();
		if (h!=0) {
			sb.append("(x ");
			sb.append(h>0?"+ ":"- ");
			sb.append(String.format("%.3f",Math.abs(h)));
			sb.append(")^2 ");
		} else sb.append("x^2 ");
		sb.append("+ ");
		if (k!=0) {
			sb.append("(y ");
			sb.append(k>0?"+ ":"- ");
			sb.append(String.format("%.3f",Math.abs(k)));
			sb.append(")^2 ");
		} else sb.append("y^2 ");
		sb.append(String.format("= %.3f^2",r));
		return sb.toString();
	}
	
	private static String format2(double a, double b, double c) {
		StringBuilder sb=new StringBuilder();
		sb.append("x^2 + y^2 ");
		if (a!=0) {
			sb.append(a>0?"+ ":"- ");
			sb.append(String.format("%.3f",Math.abs(a)));
			sb.append("x ");
		}
		if (b!=0) {
			sb.append(b>0?"+ ":"- ");
			sb.append(String.format("%.3f",Math.abs(b)));
			sb.append("y ");
		}
		if (c!=0) {
			sb.append(c>0?"+ ":"- ");
			sb.append(String.format("%.3f",Math.abs(c)));
			sb.append(" ");
		}
		sb.append("= 0");
		return sb.toString();
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double x1=Double.parseDouble(st.nextToken());
			double y1=Double.parseDouble(st.nextToken());
			double x2=Double.parseDouble(st.nextToken());
			double y2=Double.parseDouble(st.nextToken());
			double x3=Double.parseDouble(st.nextToken());
			double y3=Double.parseDouble(st.nextToken());
			
			/*
			 * (x-x1)(x-x1) + (y-y1)(y-y1) = (x-x2)(x-x2) + (y-y2)(y-y2)
			 * 
			 * x^2 - 2xx1 + x1^2 + y^2 - 2yy1 + y1^2 = x^2 - 2xx2 + x2^2 + y^2 - 2yy2 + y2^2
			 * -2xx1 + x1^2 - 2yy1 + y1^2 = -2xx2 + x2^2 - 2yy2 + y2^2
			 * -2xx1 + x1^2 - 2yy1 + y1^2 + 2xx2 - x2^2 + 2yy2 - y2^2 = 0
			 *-2x(x2 - x1) + 2y(y2 - y1) + x1^2 + y1^2 - x2^2 - y2^2 = 0
			 * 
			 */
			double a1=2*(x2-x1);
			double b1=2*(y2-y1);
			double c1=x1*x1+y1*y1-x2*x2-y2*y2;
			
			double a2=2*(x3-x2);
			double b2=2*(y3-y2);
			double c2=x2*x2+y2*y2-x3*x3-y3*y3;
			
			double x=Integer.MIN_VALUE, y=Integer.MIN_VALUE;
			if (a1==0) { // 0x+b1y=-c1
				y=-c1/b1;
				x=(-c2-b2*y)/a2;
			} else if (a2==0) { // 0x+b2y=-c2
				y=-c2/b2;
				x=(-c1-b1*y)/a1;
			} else if (b1==0) {// a1x = -c1
				x=-c1/a1;
				y=(-c2-a2*x)/b2;
			} else if (b2==0) { //a2x = -c2
				x=-c2/a2;
				y=(-c1-a1*x)/b1;
			} else { // Solve simultaneous equation
				x=(b2*c1-c2*b1)/(b1*a2-b2*a1);
				y=(-c1-a1*x)/b1;
			}
			double r=Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));

			StringBuilder sb=new StringBuilder();
			sb.append(format1(-x,-y,r));
			sb.append('\n');
			sb.append(format2(2*-x,2*-y,x*x+y*y-r*r));
			sb.append('\n');
			System.out.println(sb);
		}
 	}

}