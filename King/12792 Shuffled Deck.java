import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int P=Integer.parseInt(s);
			int [] cards=new int [P];
			for (int p=0;p<P;p++) cards[p]=p;
			int mid=P>>1;
			
			int count=0;
			int currPos=0;
			while (true) {
				currPos=((currPos&1)==0) ? mid+currPos/2 : currPos/2;
				count++;
				
				if (currPos==0) break;
			}
			
			System.out.println(count);
		}
	}

}
