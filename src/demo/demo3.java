package demo;

import kpp.CProblem;
import util.CResult;
import util.CUtils;
import alg.CDP1;
import alg.CDP2;
import alg.CDP3;
import alg.CGreedy;

public class demo3 {
	public static void main(String[] args) throws InterruptedException {
//		CElement[] elements = {new CElement(2, 6),
//				new CElement(3, 5),
//				new CElement(6, 8),
//				new CElement(7, 9),
//				new CElement(5, 6),
//				new CElement(9, 7),
//				new CElement(4, 3)};
//		CProblem test = new CProblem(new CKnapSack(9), elements);
		
		while(true) {
			CProblem test = new CProblem(20, 60, System.currentTimeMillis());
			// show the problem:
			System.out.println(test.toString());
			
			CResult dp1 = CUtils.getResult(new CDP1(), test);
			System.out.println(dp1.getTime() + ":\t" + dp1.getSolution().getCurrentProfit());
			CResult dp2 = CUtils.getResult(new CDP2(), test);
			System.out.println(dp2.getTime() + ":\t" + dp2.getSolution().getCurrentProfit());
			CResult dp3 = CUtils.getResult(new CDP3(), test);
			System.out.println(dp3.getTime() + ":\t" + dp3.getSolution().getCurrentProfit());
			
			System.out.println("#################");
			Thread.sleep(500);
		}
	}
}