import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;

		public Tuple(double d) {
			this.x=Math.sqrt((d*d)/2);
			this.y=this.x;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("END")) {
			StringTokenizer st=new StringTokenizer(s,",");
			
			double x=0;
			double y=0;
			while (st.hasMoreTokens()) {
				String curr=st.nextToken();
				int d=0;
				String dir="";
				for (int i=0;i<curr.length();i++) {
					char c=curr.charAt(i);
					if (Character.isDigit(c)) {
						d=d*10+(c-'0');
					} else if (Character.isAlphabetic(c)) {
						dir+=c;
					}
				}
				
				switch (dir) {
					case "N": {
						y+=d;
						break;
					}
					case "S": {
						y-=d;
						break;
					}
					case "W" :{
						x-=d;
						break;
					}
					case "E": {
						x+=d;
						break;
					}
					case "NW": {
						Tuple t=new Tuple(d);
						x-=t.x;
						y+=t.y;
						break;
					}
					case "NE": {
						Tuple t=new Tuple(d);
						x+=t.x;
						y+=t.y;
						break;
					}
					case "SW": {
						Tuple t=new Tuple(d);
						x-=t.x;
						y-=t.y;
						break;
					}
					case "SE": {
						Tuple t=new Tuple(d);
						x+=t.x;
						y-=t.y;
						break;
					}
				}
			}
			double dist=Math.sqrt(x*x+y*y);

			StringBuilder sb=new StringBuilder();
			sb.append("Map #");
			sb.append(tc++);
			sb.append("\nThe treasure is located at ");
			sb.append(String.format("(%.3f,%.3f).",x,y));
			sb.append("\nThe distance to the treasure is ");
			sb.append(String.format("%.3f.\n",dist));
			System.out.println(sb);
		}
 	}

}