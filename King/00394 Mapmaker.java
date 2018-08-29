import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	private static class Array {
		int baseAdd;
		int elementByte;
		int dimension;
		int [][] bounds;
		
		public Array(StringTokenizer st) {
			this.baseAdd=Integer.parseInt(st.nextToken());
			this.elementByte=Integer.parseInt(st.nextToken());
			this.dimension=Integer.parseInt(st.nextToken());
			this.bounds=new int[this.dimension][2];
			for (int i=0;i<this.dimension;i++) {
				this.bounds[i][0]=Integer.parseInt(st.nextToken());
				this.bounds[i][1]=Integer.parseInt(st.nextToken());
			}
		}
		
		public int getAddress(int [] index) {
			int v=0;
			for (int i=0;i<index.length;i++) {
				int boundSize=this.bounds[i][1]-this.bounds[i][0]+1;
				v=v*boundSize+(index[i]-this.bounds[i][0]);
			}
			return this.baseAdd+v*this.elementByte;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int R=Integer.parseInt(st.nextToken());
		HashMap<String, Array> map=new HashMap<>();
		
		for (int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), new Array(st));
		}
		
		StringBuilder sb=new StringBuilder();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			String name=st.nextToken();
			int [] index=new int [map.get(name).dimension];
			for (int i=0;i<index.length;i++) index[i]=Integer.parseInt(st.nextToken());
			int value=map.get(name).getAddress(index);
			sb.append(name);
			sb.append('[');
			for (int i=0;i<index.length;i++) {
				sb.append(index[i]);
				sb.append(", ");
			}
			sb.setLength(sb.length()-2);
			sb.append(']');
			sb.append(" = ");
			sb.append(value);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
