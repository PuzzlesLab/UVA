import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static class Bumper {
		int value, cost;
		public Bumper (int value, int cost) {
			this.value=value;
			this.cost=cost;
		}
	}
	public static void main (String [] args) throws Exception {
		int [][] delta= {{1,0},{0,1},{-1,0},{0,-1}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int M=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		int wallCost=Integer.parseInt(br.readLine());
		int B=Integer.parseInt(br.readLine());
		
		Bumper [][] bumpers=new Bumper[M][N];
		for (int b=0;b<B;b++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			Bumper bp=new Bumper(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			bumpers[x][y]=bp;
		}

		String s;
		int totalValue=0;
		while ((s=br.readLine())!=null) {
			st=new StringTokenizer(s);
			int ballX=Integer.parseInt(st.nextToken())-1;
			int ballY=Integer.parseInt(st.nextToken())-1;
			int direction=Integer.parseInt(st.nextToken());
			int remainingCost=Integer.parseInt(st.nextToken());
			int ballValue=0;
			
			while (--remainingCost>0) {
				int nextx=ballX+delta[direction][0];
				int nexty=ballY+delta[direction][1];
				if (nextx<=0 || nextx>=M-1 || nexty<=0 || nexty>=N-1) {
					remainingCost-=wallCost;
					direction=(direction+3)%4;
				} else if (bumpers[nextx][nexty]!=null) {
					remainingCost-=bumpers[nextx][nexty].cost;
					ballValue+=bumpers[nextx][nexty].value;
					direction=(direction+3)%4;
				} else {
					ballX=nextx;
					ballY=nexty;
				}
			}
			System.out.println(ballValue);
			totalValue+=ballValue;
		}
		System.out.println(totalValue);
	}

}