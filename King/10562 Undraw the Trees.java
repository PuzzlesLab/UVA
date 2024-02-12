import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static final boolean [] INVALID_CHAR=new boolean [128];

	private static void traverse(ArrayList<String> lines, int x, int y, StringBuilder sb) {
		sb.append(lines.get(x).charAt(y));
		sb.append('(');
		if (x+3<lines.size() && y<lines.get(x+1).length() && lines.get(x+1).charAt(y)=='|') {
			String range=lines.get(x+2);
			String children=lines.get(x+3);

			int minY=y;
			int maxY=y;
			while (minY>0 && range.charAt(minY)=='-') minY--;
			while (maxY<range.length() && range.charAt(maxY)=='-') maxY++;

			for (int i=minY;i<maxY && i<children.length();i++) {
				if (!INVALID_CHAR[children.charAt(i)]) {
					traverse(lines,x+3,i,sb);
				}
			}
		}
		sb.append(')');
	}

	public static void main (String [] args) throws Exception {
		INVALID_CHAR['-']=true;
		INVALID_CHAR['|']=true;
		INVALID_CHAR[' ']=true;
		INVALID_CHAR['#']=true;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			ArrayList<String> lines=new ArrayList<>();
			String s;
			while (!(s=br.readLine()).equals("#")) lines.add(s);
			
			StringBuilder sb=new StringBuilder();
			sb.append('(');
			if (lines.size()>0) {
				int y=-1;
				for (int i=0;i<lines.get(0).length();i++) if (!INVALID_CHAR[lines.get(0).charAt(i)]) {
					y=i;
					break;
				}
				traverse(lines,0,y,sb);
			}
			sb.append(')');
			System.out.println(sb.toString());
		}
	}

}
