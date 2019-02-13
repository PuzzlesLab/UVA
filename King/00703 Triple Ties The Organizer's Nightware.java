import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int [][] data=new int [N][N];
			for (int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<N;i2++) data[i][i2]=Integer.parseInt(st.nextToken());
			}
			
			StringBuilder sb=new StringBuilder();
			int count=0;
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (i!=i2) for (int i3=0;i3<N;i3++) if (i2!=i3 && i!=i3) 
				if ((((i>i2 && i2>i3) || (i3>i2 && i2>i)) && data[i][i2]==1 && data[i2][i]==0 && data[i2][i3]==1 && data[i3][i2]==0 && data[i3][i]==1 && data[i][i3]==0) ||
					(i3>i2 && i2>i && data[i][i2]==0 && data[i2][i]==0 && data[i2][i3]==0 && data[i3][i2]==0 && data[i3][i]==0 && data[i][i3]==0)) {
						sb.append(i+1);
						sb.append(' ');
						sb.append(i2+1);
						sb.append(' ');
						sb.append(i3+1);
						sb.append('\n');
						count++;
			}
			
			
			System.out.println(count);
			System.out.print(sb.toString());
		}
	}

}