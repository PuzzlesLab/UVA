import java.io.BufferedReader;
import java.io.InputStreamReader;

class zz {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			/*           x
			 *   ------------------
			 *   |^ \    C     / ^|
			 *   |/   \      /   \|
			 *   |\  B  \ / B    /|
			 *   | |    /Z\     | |
			 *   | |  /     \   | |
			 *   | \/         \/  |
			 *   |C/\    A    /\C |
			 *   |/   \     /   \ |
			 *   |\  B  \ / B    /|
			 *   | |    / \     / |
			 *   | /  /     \  \  |
			 *   |. /    C    \ . |
			 *   ------------------
			 *   
			 *   area = x*x
			 *   A+4B+4C=area         --- (1)
			 *   A+3B+2C=(PI*area/4)  --- (2)
			 *   
			 *   (1)-(2) => B+2C = (1-Pi/4)*area, B=(1-Pi/4)*area - 2C   --- (3)
			 *   
			 *   Angle Corner-Z-Corner = 60 degree
			 *   let M = Triangle Corner-Z-Corner
			 *   let H = Bound to Z length
			 *   cos 30 = 0.5x / H, H = sqrt(3)/4 x
			 *   Area M = H*x = sqrt(3)/4 x * x = sqrt(3)/4 * area
			 *   
			 *   Let N = Triangle beside M to side.
			 *   Area N = (30/360)*PI*area
			 *   
			 *   C = area-M-2*N
			 *     = area-(sqrt(3)/4)x-(1/6)*PI*area
			 *   
			 *   Then we calc B (with eq 3) and A (with eq 1).
			 */
			double x=Double.parseDouble(s);
			double area=x*x;
			double c=area-((30.0/360)*Math.PI*area*2)-((Math.sqrt(3)/4)*area);
			double b=(1-Math.PI/4)*area-2*c;
			double a=area-4*b-4*c;
			System.out.printf("%.3f %.3f %.3f\n",a,4*b,4*c);
		}
 	}
}
