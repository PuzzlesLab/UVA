import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] lastList= {BigInteger.ONE};
		BigInteger limit=BigInteger.TEN.pow(60);
		
		StringBuilder sb=new StringBuilder();
		sb.append("1\n");
		
		while (true) {
			boolean toStop=false;
			
			BigInteger [] currList=new BigInteger[lastList.length+1];
			currList[0]=BigInteger.ONE;
			for (int i=1;i<currList.length-1;i++) {
				currList[i]=lastList[i-1].add(lastList[i]);
				toStop |= currList[i].compareTo(limit)>=0;
			}
			currList[currList.length-1]=BigInteger.ONE;
			
			for (int i=0;i<currList.length;i++) {
				sb.append(currList[i]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
			
			if (toStop) break;
			else lastList=currList;
		}
		
		System.out.print(sb.toString());
	}

}