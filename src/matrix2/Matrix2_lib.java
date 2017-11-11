package matrix2;

import java.util.Arrays;

public class Matrix2_lib {
	//private double a[][],b[][];

	//public Matrix_lib(double[][] a, double[][] b) {
		//super();
		//this.a = a;
		//this.b = b;
	//}
	
	public double getinnerProduct(double a[],double b[]) { //内積
	double answer = 0;
	for(int i = 0;i < a.length;i++) {
		answer += a[i]*b[i];
	}
	return answer;
	
	}
	
	public double [][] getProduct(double a[][],double b[][]) {  //行列積
		
		double answer [][] = new double [a.length][b[0].length];
	    double t[][] = this.getTranspose(b);
		for(int i = 0;i < a.length;i++) {
			for(int j = 0;j < b[0].length; j++) {
				answer[i][j] = this.getinnerProduct(a[i],t[j]);
			}
		}
			
		
		return answer;
	}
	public double [][] getTranspose (double a [][]){  //転置行列
		double t[][] = new double [a[0].length][a.length];
			for(int i = 0;i < a[0].length;i++) {
				for(int j = 0; j < a.length;j++) {
				t[i][j] = a[j][i];
				}
			}
		return t;
	}
	
	public double getCofactor(double [][]a) {
		double cofactor = 0;
		if(a.length == 2 ){
			cofactor = a[0][0]*a[1][1] - a[0][1]*a[1][0];			
		}
		else {
			double tmp[][] = new double[a.length - 1][a[0].length - 1];
			//1列について展開
			int index = 0;
			for(int i = 0; i < a.length; i++) {
				//余因子行列作成
				int p = 0,q = 0;
				for(int j = 0; j < a.length; j++) {
					if(i == j) continue;
					for(int k = 0; k < a[0].length; k++) {
						if(index == k) continue;
						tmp[p][q++] = a[j][k];
					}
					p++;
					q = 0;
				}
				System.out.println("tmp["+i+"] = " + Arrays.deepToString(tmp)); //確認のため表示する用
				cofactor += a[i][index]*Math.pow(-1,(i + 1) + (index + 1))*this.getCofactor(tmp); 
			}
		}
			
		return cofactor;
	}
	
	public double [][] getInverse(double[][]a){
		double answer [][] = new double[a.length][a[0].length];
		double tmp[][] = new double[a.length - 1][a[0].length - 1];
		double determinant = this.getCofactor(a);
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				int p = 0,q = 0;
				for(int k = 0; k < a.length; k++) {
					if(i == k) continue;
					for(int s = 0; s < a[0].length; s++) {
						if(j == s) continue;
						tmp[p][q] = a[k][s];
						q++;
					}
					p++;
					q = 0;
				}
				answer[i][j] = Math.pow(-1, i + 1 + j + 1) * this.getCofactor(tmp) / determinant;
			}
		}
		answer = this.getTranspose(answer);
		return answer;
	}
	

}
