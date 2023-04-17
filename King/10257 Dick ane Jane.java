import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(line);
			int s=Integer.parseInt(st.nextToken());
			int p=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			/*
			 *  S=s+P
				P=p+Y
				S=y+Y
				S+P+Y=12+J
				s+P+P+Y=12+J
				s+p+Y+p+Y+Y=12+J
				s+2p+3Y=12+J
				3Y=12+J-s-2p
				Y=(12+J-s-2p)/3
			 */
			int div=12+j-y-p;
			int yertle=div/3;
			int puff=p+yertle;
			int spot=y+yertle;
			if (div%3==1) {
				if (p+s+1==y) puff++;
				else spot++;
			} else if (div%3==2) {
				spot++;
				puff++;
			}
			System.out.printf("%d %d %d\n",spot,puff,yertle);
		}
	}
}
