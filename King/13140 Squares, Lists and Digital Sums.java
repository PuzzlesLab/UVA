class Main {

	private static boolean ok(int n) {
		int sum=0;
		while (n>0) {
			sum+=n%10;
			n/=10;
		}
		int sqrt=(int)Math.sqrt(sum);
		return sqrt*sqrt==sum;
	}

	public static void main(String[] args) throws Exception {
		int ans=-1;
		for (int s=10;s<10000;s++) {
			boolean allOK=true;
			for (int e=s;e<s+7;e++) {
				allOK&=ok(e*e);
			}
			if (allOK) {
				ans=s;
				break;
			}
		}
		
		for (int d=0;d<7;d++) {
			int c=ans+d;
			System.out.printf("%d %d\n",c,c*c);
		}
	}

}
