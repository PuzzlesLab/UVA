import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class Color {
		int r, g, b;
		
		public Color(int r, int g, int b) {
			this.r=r;
			this.g=g;
			this.b=b;
		}
	}

	private static class Shape {
		Color color;
		public int isWithin(Point p) { return -1; };
		
		public void setColor(Color c) {
			this.color=c;
		}
	}
	
	public static class Circle extends Shape {
		Point p;
		int r;
		
		public Circle(Point p, int r) {
			this.p=p;
			this.r=r;
		}
		
		public int isWithin(Point p) {
			int dx=this.p.x-p.x;
			int dy=this.p.y-p.y;
			int r2=this.r*this.r;
			int distSq=dx*dx+dy*dy;
			if (distSq==r2) return 0;
			if (distSq<r2) return 1;
			return -1;
		}
	}
	
	public static class Square extends Shape {
		Point p1, p4;
		
		public Square(Point p, int l) {
			this.p1=new Point(p.x,p.y);
			this.p4=new Point(p.x+l,p.y+l);
		}

		public int isWithin(Point p) {
			if (p.x>this.p1.x && p.x<this.p4.x && p.y>this.p1.y && p.y<this.p4.y) return 1;
			if ((p.x>=this.p1.x && p.x<=this.p4.x) && (p.y>=this.p1.y && p.y<=this.p4.y)) return 0;
			return -1;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			
			Shape [] shapes=new Shape[R];
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				String objType=st.nextToken();
				Point p=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				int l=Integer.parseInt(st.nextToken());
				if (objType.equals("CIRCLE")) shapes[r]=new Circle(p,l);
				else if (objType.equals("SQUARE")) shapes[r]=new Square(p,l);
				shapes[r].setColor(new Color(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			StringBuilder sb=new StringBuilder();
			if (t>1) sb.append('\n');
			sb.append("Case ");
			sb.append(t);
			sb.append(":\n");
			for (int i=0;i<P;i++) {
				st=new StringTokenizer(br.readLine());
				Point p=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				ArrayList<Color> colors=new ArrayList<>();
				boolean onLine=false;
				for (int r=0;r<R;r++) {
					int state=shapes[r].isWithin(p);
					if (state==0) {
						colors.add(shapes[r].color);
						onLine=true;
						break;
					} else if (state==1) colors.add(shapes[r].color);
				}

				if (colors.isEmpty())  sb.append("(255, 255, 255)");
				else if (onLine) sb.append("(0, 0, 0)");
				else {
					double r=0.0;
					double g=0.0;
					double b=0.0;
					for (int c=0;c<colors.size();c++) {
						Color currColor=colors.get(c);
						r+=currColor.r;
						g+=currColor.g;
						b+=currColor.b;
					}
					r/=colors.size();
					g/=colors.size();
					b/=colors.size();
					sb.append('(');
					sb.append((int)(r+0.5));
					sb.append(", ");
					sb.append((int)(g+0.5));
					sb.append(", ");
					sb.append((int)(b+0.5));
					sb.append(')');
				}
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}

}
