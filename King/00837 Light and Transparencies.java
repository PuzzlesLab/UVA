import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static class Film {
		double x1,y1,x2,y2;
		double a,b,c;
		double multi;
		
		public Film(StringTokenizer st) {
			this.x1=Double.parseDouble(st.nextToken());
			this.y1=Double.parseDouble(st.nextToken());
			this.x2=Double.parseDouble(st.nextToken());
			this.y2=Double.parseDouble(st.nextToken());
			this.multi=Double.parseDouble(st.nextToken());

			if (this.x1>this.x2) {
				double tx=this.x1;
				double ty=this.y1;
				this.x1=this.x2;
				this.y1=this.y2;
				this.x2=tx;
				this.y2=ty;
			}
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // Empty line
			int N=Integer.parseInt(br.readLine());
			Film [] films=new Film[N];
			for (int n=0;n<N;n++) films[n]=new Film(new StringTokenizer(br.readLine()));
			
			HashSet<Double> boundSet=new HashSet<>();
			for (int n=0;n<N;n++) {
				boundSet.add(films[n].x1);
				boundSet.add(films[n].x2);
			}
			ArrayList<Double> bounds=new ArrayList<>(boundSet);
			Collections.sort(bounds);
			
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			sb.append(bounds.size()+1);
			sb.append('\n');
			sb.append(String.format("-inf %.3f %.3f\n",bounds.get(0),1.0));
			for (int i=0;i<bounds.size()-1;i++) {
				double x=bounds.get(i)+0.001;
				double m=1.0;
				for (int i2=0;i2<films.length;i2++) {
					if (x<films[i2].x1 || x>films[i2].x2) continue;
					m*=films[i2].multi;
				}
				sb.append(String.format("%.3f %.3f %.3f\n",bounds.get(i),bounds.get(i+1),m));
				
			}
			sb.append(String.format("%.3f +inf %.3f\n",bounds.get(bounds.size()-1),1.0));
			
			System.out.print(sb);
		}
 	}

}
