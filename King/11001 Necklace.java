import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			double vTotal=Double.parseDouble(st.nextToken());
			double v0=Double.parseDouble(st.nextToken());
			
			int result=0;
			if (vTotal>v0) {
				double [] len=new double[(int)Math.ceil(vTotal/v0)];
				double maxLen=0.0;
				for (int i=1;i<len.length;i++) {
					len[i]=0.3*i*Math.sqrt(vTotal/i-v0);
					len[i]=((int)(len[i]*100000))/100000.0;
					
					if (len[i]>maxLen) maxLen=len[i];
				}
				
				int maxCount=0;
				for (double d : len) if (maxLen==d) maxCount++;
				
				if (maxCount==1) for (int i=1;i<len.length;i++) if (maxLen==len[i]) result=i;
			}
			System.out.println(result);
		}
	}

}
