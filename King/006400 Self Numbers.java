class Main {
	
	public static void main (String [] args) throws Exception {
		boolean [] notSelfNumber=new boolean [1000001];
		for (int i=1;i<notSelfNumber.length;i++) if (!notSelfNumber[i]) {
			int num=i;
			while (num<notSelfNumber.length) {
				int temp=num;
				while (temp>0) {
					num+=temp%10;
					temp/=10;
				}
				if (num<notSelfNumber.length) {
					if (!notSelfNumber[num]) notSelfNumber[num]=true;
					else break;
				}
			}
		}

		StringBuilder sb=new StringBuilder();
		for (int i=1;i<notSelfNumber.length;i++) if (!notSelfNumber[i]) {
			sb.append(i);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}