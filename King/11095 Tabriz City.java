import java.util.Scanner;

class Main {

	private static int N;
	private static int M;
	private static int [] AdjMat;
	private static int [][] Streets;
	private static int [] Sol;

	private static void find(int currN, int mask) {
		if (Sol!=null && Integer.bitCount(mask)>=Sol.length) return;
		if (currN==N) {
			for (int i=0;i<Streets.length;i++) {
				boolean f1=(mask&(1<<Streets[i][0]))!=0;
				boolean f2=(mask&(1<<Streets[i][1]))!=0;
				if (!f1 && !f2) return;
			}

			Sol=new int [Integer.bitCount(mask)];
			int temp=0;
			for (int i=0;i<N;i++) if ((mask&(1<<i))!=0) Sol[temp++]=i;
			return;
		}

		if ((mask&(1<<currN))!=0) find(currN+1,mask);
		else {
			find(currN+1,mask|(1<<currN));
			find(currN+1,mask|AdjMat[currN]);
		}
	}

	public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        int TC=sc.nextInt();
        for (int tc=1;tc<=TC;tc++) {
        	N=sc.nextInt();
        	M=sc.nextInt();
        	AdjMat=new int [N];

        	Streets=new int [M][2];
        	for (int m=0;m<M;m++) {
        		int n1=sc.nextInt();
        		int n2=sc.nextInt();
        		
        		Streets[m][0]=n1;
        		Streets[m][1]=n2;

        		AdjMat[n1]|=1<<n2;
        		AdjMat[n2]|=1<<n1;
        	}

        	Sol=null;
        	find(0,0);

        	StringBuilder sb=new StringBuilder();
        	sb.append("Case #");
        	sb.append(tc);
        	sb.append(": ");
        	sb.append(Sol!=null?Sol.length:0);
        	sb.append('\n');

        	if (Sol!=null && Sol.length>0) {
            	for (int i=0;i<Sol.length;i++) {
            		sb.append(Sol[i]);
            		sb.append(' ');
            	}
            	sb.setLength(sb.length()-1);
        	}

        	System.out.println(sb);
        }
	}


}