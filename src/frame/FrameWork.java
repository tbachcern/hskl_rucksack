package frame;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;

import util.CResult;
import util.CUtils;
import kpp.CProblem;
import alg.CDP2;
import alg.CGreedy;
import alg.CRecursive;
import alg.IAlgorithm;

public class FrameWork {

	private static final int MAX_N = 25;//21;
	
	private static final int MAX_RESULTS = 100000;//10_000;
	
	public static void main(String[] args){
		
		IAlgorithm[] algos = {//new CRecursive(),
				new CGreedy(),
				new CDP2()};
		
		createNT( algos , "testfile.txt" );
		
	}
	
	public static void createNT(IAlgorithm[] algorithms, String filepath){

		
		double[][] t_avg = new double[algorithms.length][MAX_N];
		
		for( int currentN = 0; currentN < MAX_N ; currentN++ ){
			for( int currentResult = 0; currentResult < MAX_RESULTS; currentResult++ ){

				
				// create problem
				CProblem currentProblem = new CProblem(currentN, 60);

				for( int currentAlgorithm = 0; currentAlgorithm < algorithms.length; currentAlgorithm++){
					
					CResult Result = CUtils.getResult(algorithms[currentAlgorithm], currentProblem);
					t_avg[currentAlgorithm][currentN] += (double)Result.getTime()/(MAX_RESULTS * 1000); 
					
				}
		
			}
		}

		try{
			PrintStream ps = new PrintStream(new File(filepath));
			
			for(int i = 0; i < MAX_N; i++){
				
				ps.print(i + " ");
				
				for(int j = 0; j < algorithms.length ; j++){
					
					ps.printf("%.2f " ,t_avg[j][i]);
				}
				ps.println();
			}
				
			ps.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

	