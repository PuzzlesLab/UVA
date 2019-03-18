import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	public static boolean match(char [] c1, char [] c2) {
		for (int i=0;i<c1.length;i++) if (c1[i]!=c2[i]) return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			char [] left=s.substring(0,6).toCharArray();
			char [] right=s.substring(6, s.length()).toCharArray();
			boolean found=false;
			char [] flipX=Arrays.copyOf(left,left.length);
			for (int x=0;x<4 && !found;x++) { //rotate 0,1,5,4
				char tempx=flipX[4]; flipX[4]=flipX[5]; flipX[5]=flipX[1]; flipX[1]=flipX[0]; flipX[0]=tempx;
				found=match(flipX,right);
				
				char [] flipY=Arrays.copyOf(flipX,flipX.length);
				for (int y=0;y<4 && !found;y++) { //rotate 1,3,4,2
					char tempy=flipY[2]; flipY[2]=flipY[4]; flipY[4]=flipY[3]; flipY[3]=flipY[1]; flipY[1]=tempy;
					found=match(flipY,right);
					
					char [] flipZ=Arrays.copyOf(flipY,flipY.length);
					for (int z=0;z<4 && !found;z++) { //move 0,3,5,2
						char tempz=flipZ[2]; flipZ[2]=flipZ[5]; flipZ[5]=flipZ[3]; flipZ[3]=flipZ[0]; flipZ[0]=tempz;
						found=match(flipZ,right);
					}
				}
			}
			System.out.println(found? "TRUE" : "FALSE");
		}
	}

}