import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean cont=true;
		while (true) {
			ArrayList<String> list=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				cont = s != null;
				if (s==null || s.trim().equals("%")) break;
				list.add(s);
			}
			
			char [][] map=new char [list.size()][list.get(0).length()/2+1];
			for (int i=0;i<map.length;i++) {
				StringTokenizer st=new StringTokenizer(list.get(i));
				for (int i2=0;i2<map[i].length;i2++) map[i][i2]=st.nextToken().charAt(0);
			}
			
			int [][] ans=new int [map.length][map[0].length];
			int maxIndex=0;
			Stack<Integer> stkX=new Stack<>();
			Stack<Integer> stkY=new Stack<>();
			for (int row=0;row<ans.length;row++) for (int col=0;col<ans[row].length;col++) if (ans[row][col]==0) {
				maxIndex++;
				stkX.add(row);
				stkY.add(col);
				
				while (!stkX.isEmpty()) {
					int x=stkX.pop();
					int y=stkY.pop();
					if (ans[x][y]==0) {
						ans[x][y]=maxIndex;
						for (int i=Math.max(x-1, 0); i<Math.min(x+2, ans.length);i++) for (int i2=Math.max(y-1, 0); i2<Math.min(y+2, ans[x].length);i2++) {
							if (ans[i][i2]==0 && map[i][i2]==map[x][y]) {
								stkX.push(i);
								stkY.push(i2);
							}
						}
					}
				}
			}

			int [] colWidth=new int [ans[0].length];
			for (int col=0;col<ans[0].length;col++) for (int row=0;row<ans.length;row++) colWidth[col]=Math.max(colWidth[col], String.valueOf(ans[row][col]).length());

			StringBuilder sb=new StringBuilder();
			for (int row=0;row<ans.length;row++) {
				for (int col=0;col<ans[row].length;col++) {
					String s=String.valueOf(ans[row][col]);
					int pad=colWidth[col]-s.length();
					while (pad-->0) sb.append(' ');
					sb.append(ans[row][col]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			sb.append('%');
			System.out.println(sb.toString());
			
			if (!cont) break;
		}
	}

}