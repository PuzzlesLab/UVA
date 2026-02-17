import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static class Antenna {
		double minx, maxx, miny, maxy;
		
		public Antenna(double x, double y, double r) {
			this.minx=x-r;
			this.maxx=x+r;
			this.miny=y-r;
			this.maxy=y+r;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Antenna [] antennas=new Antenna [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				antennas[n]=new Antenna(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			
			TreeSet<Double> xSet=new TreeSet<>();
			TreeSet<Double> ySet=new TreeSet<>();
			for (int n=0;n<N;n++) {
				xSet.add(antennas[n].minx);
				xSet.add(antennas[n].maxx);
				ySet.add(antennas[n].miny);
				ySet.add(antennas[n].maxy);
			}
			
			 // Split area into small boxes & check existence in any antenna.
			ArrayList<Double> xList=new ArrayList<>(xSet);
			ArrayList<Double> yList=new ArrayList<>(ySet);

			double area=0;
			for (int n=0;n<xList.size()-1;n++) for (int n2=0;n2<yList.size()-1;n2++) {
				double midX=(xList.get(n)+xList.get(n+1))/2;
				double midY=(yList.get(n2)+yList.get(n2+1))/2;
				for (int n3=0;n3<N;n3++) {
					if (midX>=antennas[n3].minx && midX<=antennas[n3].maxx && midY>=antennas[n3].miny && midY<=antennas[n3].maxy) {
						area+=(xList.get(n+1)-xList.get(n))*(yList.get(n2+1)-yList.get(n2));
						break;
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append(tc++);
			sb.append(' ');
			sb.append(String.format("%.2f",area));
			System.out.println(sb);
		}
	}

}