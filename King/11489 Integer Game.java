import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			char [] ch=br.readLine().toCharArray();
			int [] nums=new int [ch.length];
			int sum=0;
			for (int i=0;i<ch.length;i++) {
				nums[i]=ch[i]-'0';
				sum+=nums[i];
			}

			int m3Count=1;
			for (int i=0;i<ch.length;i++) if (nums[i]%3==0) m3Count++;
			
			int r=sum%3;
			int winner=-1;
			if (r==0) {
				winner=m3Count%2;
			} else {
				boolean canUnstuck=false;
				for (int i=0;i<ch.length && !canUnstuck;i++) canUnstuck|=(nums[i]%3)==r;
				if (!canUnstuck) { // First player can't unstuck the situation.
					winner=1; 
				} else { // First player unstuck the situation & become r==0 situation.
					winner=(m3Count+1)%2;
				}
			}

			System.out.printf("Case %d: %s\n",tc,winner==0?"S":"T");
		}
	}

}
