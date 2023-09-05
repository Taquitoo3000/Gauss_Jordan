import java.util.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Ecuaciones{
	public static void main(String args[]){
		SistEcuacion ec1=new SistEcuacion(CapturaEntrada.capturarEntero("Grado del Sistema"));
		ec1.setCtes();
		ec1.imprimirMatriz();
		ec1.imprimirEc();
		ec1=GaussJordan.eliminarCoef(ec1);
		ec1=GaussJordan.resolverSist(ec1);
		ec1=GaussJordan.printSol(ec1);
	}
}

class CapturaEntrada{
	public static int capturarEntero(String msg){
		Scanner sc = new Scanner(System.in); 
		System.out.print(""+ msg+ ": "); 
		return (sc.nextInt());
	}
	public static float capturarFloat(String msg){
		Scanner sc = new Scanner(System.in); 
		System.out.print(""+ msg+ ": "); 
		return (sc.nextFloat());
	}
}

class SistEcuacion{
	int grado;
	float array[][];
	float solucion[];
	
	public SistEcuacion(int g){
		setGrado(g);
		array=new float[grado][grado+1];
		solucion=new float[grado];

	}
	public void setGrado(int g){
		grado=g;
	}
	public int getGrado(){
		return grado;
	}
	public void setCtes(){
		int k;
		for(int i=0; i<grado; i++){
			k=i+1;
			System.out.println("\nCoeficientes de Ecuacion "+k+":");
			for(int j=0; j<grado+1; j++){
				if (j==grado){
					array[i][j]=CapturaEntrada.capturarFloat("cte");
				}
				else{
					array[i][j]=CapturaEntrada.capturarFloat("c"+j);
				}
			}
		}
	}
	public void imprimirMatriz(){
		System.out.println("\nMatriz:");
		for(int i=0; i<grado; i++){
			for(int j=0; j<grado+1; j++){
				System.out.print(array[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public void imprimirEc(){
		String ecstring=" ";
		System.out.println("\nEcuaciones:");
		for(int i=0; i<grado; i++){
			for(int j=0; j<grado+1; j++){
				if (j==0){
					ecstring=ecstring.concat("("+Float.toString(array[i][j])+")x_"+j);
				}
				else if (j==grado){
					ecstring=ecstring.concat("="+Float.toString(array[i][j]));
				}
				else{
					ecstring=ecstring.concat("+("+Float.toString(array[i][j])+")x_"+j);
				}
			}
			System.out.println(ecstring);
			ecstring=" ";
		}
	}
}

class GaussJordan{
	public static SistEcuacion eliminarCoef(SistEcuacion ec){
		float razon;
		for (int i=0; i<ec.grado; i++){
			if (ec.array[i][i]==0.0){
				System.out.println("Error! division entre 0");
			}
			for (int j=0; j<ec.grado; j++){
				if(i!=j){
					razon=ec.array[j][i]/ec.array[i][i];
					for(int k=0; k<(ec.grado+1); k++){
						ec.array[j][k]=ec.array[j][k]-(razon*ec.array[i][k]);
					}
				}
			}
		}
		return ec;
	}
	public static SistEcuacion resolverSist(SistEcuacion ec){
		for (int i=0; i<ec.grado; i++){
			ec.solucion[i]=ec.array[i][ec.grado]/ec.array[i][i];
		}
		return ec;
	}
	public static SistEcuacion printSol(SistEcuacion ec){
		System.out.println("\nSolucion:");
		for (int i=0; i<ec.grado; i++){
			System.out.println("x"+i+": "+ec.solucion[i]);
		}
		return ec;
	}
}
