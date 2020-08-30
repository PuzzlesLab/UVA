import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static class Matrix {
		int x, y;
		public Matrix(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int M=Integer.parseInt(br.readLine());
		HashMap<Character,Matrix> matrixMap=new HashMap<>();
		for (int m=0;m<M;m++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			matrixMap.put(st.nextToken().charAt(0),new Matrix(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		String s;
		while ((s=br.readLine())!=null) {
			char [] ch=s.toCharArray();
			LinkedList<Matrix> stack=new LinkedList<>();
			int open=0, ans=0;
			for (char c : ch) {
				if (c=='(') open++;
				else if (c==')' && stack.size()>=2) {
					open--;
					Matrix m1=stack.removeLast();
					Matrix m2=stack.removeLast();
					if (m2.y==m1.x) {
						Matrix m3=new Matrix(m2.x,m1.y);
						stack.addLast(m3);
						ans+=m2.x*m2.y*m1.y;
					} else {
						open=1;
						break;
					}
				} else if (matrixMap.containsKey(c)) stack.addLast(matrixMap.get(c));
				else {
					open=1;
					break;
				}
			}
			System.out.println((open==0 && stack.size()==1) ? ans : "error");
		}
	}
}