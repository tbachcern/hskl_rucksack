package demo;

import alg.CExtGreedy_1_2;
import alg.CGreedy;
import alg.CRecursive;
import util.CResult;
import util.CUtils;
import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;

/*
 * 
 * 	Demo to show extended greedy algorithm (1/2 = k)
 * 
 */

public class demo4 {

	public static void main(String[] args) {

		CElement[] elements = { 
					new CElement(1, 2), 
					// element greedy would fail for e=2
					
					new CElement(10, 10),
					// element which will be chosen by greedy_1_2
					
					new CElement(12, 12)
					// impossible to pack
		
		};

		CProblem problem =  new CProblem(new CKnapSack(10), elements);
		// show the problem:
		System.out.println(problem.toString());

		CResult greedyResult = CUtils.getResult(new CGreedy(), problem);
		System.out.println(greedyResult.toString());

		CResult greedyExtResult = CUtils.getResult(new CExtGreedy_1_2(), problem);
		System.out.println(greedyExtResult.toString());
		
		CResult optimal = greedyResult.compareWithProfit(greedyExtResult);
		
		System.out.println("\nOptimal solution:" + optimal.toString());

	}
}
