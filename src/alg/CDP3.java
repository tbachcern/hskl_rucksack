package alg;

import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;
import kpp.KPOverflowException;

public class CDP3 implements IAlgorithm {
	@Override
	public CKnapSack solve(CProblem problem) {
		// the max capacity of the KS
		int c = problem.getKnapSack().getMaxCapacity();
		// the elements
		CElement[] elems = problem.getElements();
		
		// the optimal solution z[d] with d:= capacity
		int[] z;
		// the latest new item r[d] with d:= the current capacity
		int[] r;
		// the optimal packed KS
		CKnapSack solution = new CKnapSack(c);
		int currentC = c;
		int currentN = elems.length;
		
		// in every iteration problem KP currentN (currentC) is solved by DP2
		do {
			z = new int[currentC + 1];
			r = new int[currentC + 1];
			
			for(int j = 1; j <= currentN; j++) {
				// the weigth and profit of current element
				int w = elems[j - 1].getWeight();
				int p = elems[j - 1].getProfit();
				
				// item j may be packed
				for(int d = currentC; d >= w; d--) {
					if(z[d - w] + p > z[d]) {
						z[d] = z[d - w] + p;
						r[d] = j;
					}
				}
			}
			
			// new item of the optimal solution
			int k = r[currentC];
			if(k == 0)
				break;
			try {
				solution.addElement(elems[k - 1]);
			}
			catch(KPOverflowException e) {
				break;
			}
			currentN = k - 1;
			currentC -= elems[k - 1].getWeight();
		} while( currentC > 0);
		
		return solution;
	}
}