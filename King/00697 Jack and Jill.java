import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		double BUCKET_DOWN_RATE = 32.2*12; // Convert feet to inch

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double up=Double.parseDouble(st.nextToken());
			if (!st.hasMoreTokens() && up<1) break;
			double wellDiameter=Double.parseDouble(st.nextToken());
			double waterLevel=Double.parseDouble(st.nextToken());
			double bucketVol=Double.parseDouble(st.nextToken());
			double pullRate=Double.parseDouble(st.nextToken());
			double down=Double.parseDouble(st.nextToken());
			double volNeeded=Double.parseDouble(st.nextToken());

			StringBuilder sb=new StringBuilder();
			if (TC>1) sb.append('\n');
			sb.append("Scenario ");
			sb.append(TC++);
			sb.append(":\n");
			sb.append(String.format("     up hill         %10.2f sec\n",up));
			sb.append(String.format("     well diameter   %10.2f in\n",wellDiameter));
			sb.append(String.format("     water level     %10.2f in\n",waterLevel));
			sb.append(String.format("     bucket volume   %10.2f cu ft\n",bucketVol));
			sb.append(String.format("     bucket ascent rate%8.2f in/sec\n",pullRate));
			sb.append(String.format("     down hill       %10.2f sec\n",down));
			sb.append(String.format("     required volume %10.2f cu ft\n",volNeeded));

			bucketVol*=(12*12*12); // Convert cubic feet to cubic inch
			volNeeded*=(12*12*12); // Convert cubic feet to cubic inch
			double wellRadius=wellDiameter/2;
			double ans=0.0;
			double carriedVol=0.0;
			while (carriedVol+0.0000001<volNeeded) { // WA if not correcting precision here!
				double waterLevelAdd=bucketVol/(Math.PI*wellRadius*wellRadius);

				/*
				 * - Goes up
				 * - Drop bucket -> s=ut+0.5at^2, u=0, s=0.5at^2, t=sqrt(2s/a)
				 * - Lift bucket
				 * - Goes down
				 */
				ans+=up+Math.sqrt(2*waterLevel/BUCKET_DOWN_RATE)+waterLevel/pullRate+down;
				waterLevel+=waterLevelAdd; // Update water level
				carriedVol+=bucketVol;
			}

			sb.append(String.format("     TIME REQUIRED   %10.2f sec",ans));
			System.out.println(sb.toString());
		}
	}

}
