import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(String s) {
			StringTokenizer st=new StringTokenizer(s, ",");
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
		}
		
		public void makeXLarger() {
			if (this.x<this.y) {
				int temp=this.x;
				this.x=this.y;
				this.y=temp;
			}
		}
	}

	private static double calcPeak(Tuple p, Tuple c, double deg) {
		double ang=Math.toRadians(deg);
		double ext=p.x*Math.cos(ang)+p.y*Math.sin(ang)-c.x;
		double height=ext*Math.tan(ang)+p.y*Math.cos(ang);
		return height;
	}

	public static void main(String [] args) throws Exception {
		// Explanation figure: https://i.imgur.com/8s89TxF.png
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		final double prec=0.1;
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Tuple piano=new Tuple(st.nextToken());
			piano.makeXLarger();
			ArrayList<Tuple> corridors=new ArrayList<>();
			while (st.hasMoreTokens()) corridors.add(new Tuple(st.nextToken()));

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<corridors.size();i++) {
				Tuple c=corridors.get(i);
				if (c.x<piano.y) { // Input < short side of piano
					sb.append('N');
					continue;
				}

				boolean flag=true;
				for (double ang=0.0;ang<90 && flag;ang+=prec) flag&=calcPeak(piano,c,ang)<=c.y;
				sb.append(flag?'Y':'N');
			}
			System.out.println(sb);
		}
 	}

}