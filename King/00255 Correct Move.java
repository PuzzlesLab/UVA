import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int ki=Integer.parseInt(st.nextToken());
			int [] king={ki/8,ki%8};
			int qi=Integer.parseInt(st.nextToken());
			int [] queen={qi/8,qi%8};
			int mi=Integer.parseInt(st.nextToken());
			int [] move={mi/8,mi%8};
			
			if (ki==qi) System.out.println("Illegal state");
			else if ((move[0]!=queen[0] && move[1]!=queen[1]) || ki==mi || qi==mi ) System.out.println("Illegal move");
			else {
				int [][] flag=new int [8][8];
				for (int i=-1;i<=1;i++) {
					if (king[0]+i>=0 && king[0]+i<8) flag[king[0]+i][king[1]]+=1;
					if (king[1]+i>=0 && king[1]+i<8) flag[king[0]][king[1]+i]+=1;
				}
				flag[king[0]][king[1]]-=1;
				
				int dx=0, dy=0;
				if (move[0]<=queen[0] && move[1]==queen[1]) dx=-1;
				else if (move[0]>=queen[0] && move[1]==queen[1]) dx=1;
				
				if (move[1]<=queen[1] && move[0]==queen[0]) dy=-1;
				else if (move[1]>=queen[1] && move[0]==queen[0]) dy=1;
				
				int currX=queen[0], currY=queen[1];
				while (currX>=0 && currX<8 && currY>=0 && currY<8) {
					if (currX==king[0] && currY==king[1]) break;
					flag[currX][currY]+=2;
					currX+=dx;
					currY+=dy;
				}
				
				if (flag[move[0]][move[1]]==3) System.out.println("Move not allowed");
				else if (flag[move[0]][move[1]]!=2) System.out.println("Illegal move");
				else {
					queen[0]=move[0];
					queen[1]=move[1];
					flag=new int [8][8];
					for (int i=0;i<8;i++) {
						flag[queen[0]][i]=2;
						flag[i][queen[1]]=2;
					}
					
					boolean hasOne=false;
					for (int i=-1;i<=1 && !hasOne;i++) if (i!=0) {
						if (king[0]+i>=0 && king[0]+i<8 && flag[king[0]+i][king[1]]==0) hasOne |= true;
						if (king[1]+i>=0 && king[1]+i<8 && flag[king[0]][king[1]+i]==0) hasOne |= true;
					}
					if (hasOne) System.out.println("Continue");
					else System.out.println("Stop");
				}
			}
		}
	}

}
