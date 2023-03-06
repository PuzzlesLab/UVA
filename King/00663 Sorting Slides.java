import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static Slide [] Slides;
	private static SlideLabel [] SlideLabels;

	private static class Slide {
		char label;
		int x1, x2, y1, y2;
		SlideLabel pair;

		public Slide(char label, int x1, int x2, int y1, int y2) {
			this.label=label;
			this.x1=x1;
			this.x2=x2;
			this.y1=y1;
			this.y2=y2;

		}
	}

	private static class SlideLabel {
		int idx, x, y;
		boolean visited;
		ArrayList<Slide> possSlides;

		public SlideLabel(int idx, int x, int y) {
			this.idx=idx;
			this.x=x;
			this.y=y;
			this.possSlides=new ArrayList<>();
		}
		
		
		private boolean canFit(Slide s) {
			return (this.x>=s.x1 && this.x<=s.x2 && this.y>=s.y1 && this.y<=s.y2);
		}
	}

	private static class Tuple {
		Slide slide;
		SlideLabel slideLabel;
		
		public Tuple(Slide slide, SlideLabel slideLabel) {
			this.slide=slide;
			this.slideLabel=slideLabel;
		}
	}

	private static int mcbm(SlideLabel l, Tuple blocked) {
		if (l.visited) return 0;

		l.visited=true;
		for (int i=0;i<l.possSlides.size();i++) {
			Slide r=l.possSlides.get(i);
			if (blocked!=null && blocked.slide==r && blocked.slideLabel==l) continue;

			if (r.pair==null || mcbm(r.pair,blocked)==1) {
				r.pair=l;
				return 1;
			}
		}
		return 0;
	}

	private static int bipartite(int N, Tuple blocked) {
		for (int n=0;n<N;n++) Slides[n].pair=null;

		int matchCount=0;
		for (int n=0;n<N;n++) {
			for (int n2=0;n2<N;n2++) SlideLabels[n2].visited=false;
			matchCount+=mcbm(SlideLabels[n],blocked);
		}
		return matchCount;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			
			Slides=new Slide[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Slides[n]=new Slide((char)('A'+n),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			SlideLabels=new SlideLabel[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				SlideLabels[n]=new SlideLabel(n+1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (SlideLabels[n].canFit(Slides[n2])) SlideLabels[n].possSlides.add(Slides[n2]);

			int result=bipartite(N,null);
			SlideLabel [] resultSet=new SlideLabel [N];
			for (int n=0;n<N;n++) resultSet[n]=Slides[n].pair;

			char [] ansLabel=new char[N];
			int [] ansIdx=new int [N];
			int ansCount=0;
			for (int n=0;n<N;n++) {
				Slide canS=Slides[n];
				if (bipartite(N,new Tuple(canS,resultSet[n]))<result) {
					ansLabel[ansCount]=canS.label;
					ansIdx[ansCount]=resultSet[n].idx;
					ansCount++;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Heap ");
			sb.append(testCase++);
			sb.append('\n');
			if (ansCount==0) sb.append("none\n");
			else {
				for (int n=0;n<ansCount;n++) {
					sb.append('(');
					sb.append(ansLabel[n]);
					sb.append(',');
					sb.append(ansIdx[n]);
					sb.append(") ");
				}
				if (sb.charAt(sb.length()-1)==' ') sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}
