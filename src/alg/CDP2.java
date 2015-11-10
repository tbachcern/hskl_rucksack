package alg;

import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;
import kpp.KPOverflowException;

public class CDP2 implements IAlgorithm {
	@Override
	public CKnapSack solve(CProblem problem) {
		// the max capacity of the KS
		int c = problem.getKnapSack().getMaxCapacity();
		// the elements
		CElement[] elems = problem.getElements();
		
		// the pointer, which keeps track of wether the element is packed or not
		int[][] A = new int[elems.length + 1][c + 1];
		// the optimal solution z[d] with d:= capacity
		int[] z = new int[c + 1];
		
		for(int j = 1; j <= elems.length; j++) {
			// the weigth and profit of current element
			int w = elems[j - 1].getWeight();
			int p = elems[j - 1].getProfit();
			
			// item j may be packed
			for(int d = z.length - 1; d >= w; d--)
				if(z[d - w] + p > z[d]) {
					z[d] = z[d - w] + p;
					A[j][d] = 1;
				}
		}
		
		// fill the KS with the optimal solution
		CKnapSack solution = new CKnapSack(c);
		for(int j = A.length - 1; j > 0; j--) {
			if(A[j][c] == 1)
				try {
					solution.addElement(elems[j - 1]);
					c -= elems[j - 1].getWeight();
				}
				catch(KPOverflowException e) {
					break;
				}
		}
		
		return solution;
	}
}