import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static int usageToBill(int usage) {
		int [] price= {2,3,5,7};
		int [] lim= {100,9900,990000,Integer.MAX_VALUE};
		int bill=0;
		for (int i=0;i<price.length;i++) {
			bill+=price[i]*Math.min(lim[i], usage);
			usage=Math.max(0, usage-lim[i]);
		}
		return bill;
	}
	
	public static int billToUsage(int bill) {
		int [] price= {2,3,5,7};
		int [] lim= {200,29700,4950000,Integer.MAX_VALUE};
		int [] fill=new int [price.length];

		int usage=0;
		for (int i=0;i<price.length;i++) {
			fill[i]=Math.min(bill,lim[i]);
			usage+=(fill[i]/price[i]);
			bill-=fill[i];
		}
		return usage;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int sum=Integer.parseInt(st.nextToken());
			int diff=Integer.parseInt(st.nextToken());
			
			int totalUsage=billToUsage(sum);
			int min=0;
			int max=totalUsage;
			int ans=0;
			while (min<max) {
				int mid=(min+max)/2;
				int testDiff=usageToBill(totalUsage-mid) - usageToBill(mid);
				if (testDiff>diff) min=mid;
				else if (testDiff<diff) max=mid;
				else {
					ans=mid;
					break;	
				}
			}
			System.out.println(usageToBill(ans));
		}
	}

}