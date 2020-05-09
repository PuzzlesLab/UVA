class Main {

	public static void main (String [] args) throws Exception {
		StringBuilder sb=new StringBuilder();
		for (int a=1;a<2000;a++) {
			double ad=a/100.0;
			for (int b=a;a+b<2000;b++) {
				double bd=b/100.0;
				for (int c=b;a+b+c<2000;c++) if (a*b*c > 1000000) {
					double cd=c/100.0;
					int d = (1000000*(a+b+c)) / (a*b*c - 1000000);
					if (d>=c && a+b+c+d<=2000) {
						double dd=d/100.0;
						double multiply=ad*bd*cd*dd;
						double sum=ad+bd+cd+dd;
						if (Math.abs(multiply-sum)<1e-9) {
							sb.append(String.format("%.2f %.2f %.2f %.2f\n",ad,bd,cd,dd));
						}
					}
				}
			}
		}
		System.out.print(sb.toString());
	}

}