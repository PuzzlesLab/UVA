import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());

			int stx=Integer.parseInt(st.nextToken());
			int sty=Integer.parseInt(st.nextToken());
			int etx=Integer.parseInt(st.nextToken());
			int ety=Integer.parseInt(st.nextToken());
			
			int sx=Math.min(stx,etx);
			int ex=Math.max(stx,etx);
			int sy=Math.min(sty,ety);
			int ey=Math.max(sty,ety);

			/* Transform input to the following format:
			 * sx,ey------ex,ey
			 *   |          |
			 *   |          |
			 * sx,sy------ex,sy
			 */

			boolean flag=false;
			flag|=(x1>=sx && x1<=ex && y1>=sy && y1<=ey); // Is first point in the rectangle?
			flag|=(x2>=sx && x2<=ex && y2>=sy && y2<=ey); // Is second point in the rectangle?
			if (x1==x2 && y1==y2) { // "Line" is a point.
				flag|=x1>=sx && x2<=ex && y1>=sy && y2<=ey;
			} else if (x1==x2) { // Vertical line
				int minY=Math.min(y1,y2);
				int maxY=Math.max(y1,y2);
				flag|=x1>=sx && x1<=ex && minY<=sy && maxY>=sy;
				flag|=x1>=sx && x1<=ex && minY<=ey && maxY>=ey;
			} else if (y1==y2) { // Horizontal line
				int minX=Math.min(x1,x2);
				int maxX=Math.max(x1,x2);
				flag|=minX<=sx && maxX>=sx && y1>=sy && y1<=ey;
				flag|=minX<=ex && maxX>=ex && y1>=sy && y1<=ey;
			} else { // Check if slope line cuts through any rectangle side
				int minY=Math.min(y1,y2);
				int maxY=Math.max(y1,y2);
				int minX=Math.min(x1,x2);
				int maxX=Math.max(x1,x2);

				// y=mx+c
				double m=(y2-y1)/(x2-x1+0.0);
				double c=y2-m*x2;
				double tx, ty;

				ty=m*sx+c;
				flag|=(ty>=sy && ty<=ey && ty>=minY && ty<=maxY);
				ty=m*ex+c;
				flag|=(ty>=sy && ty<=ey && ty>=minY && ty<=maxY);
				
				tx=(sy-c)/m;
				flag|=(tx>=sx && tx<=ex && tx>=minX && tx<=maxX);
				tx=(ey-c)/m;
				flag|=(tx>=sx && tx<=ex && tx>=minX && tx<=maxX);
			}
			System.out.println(flag?'T':'F');
		}
 	}

}