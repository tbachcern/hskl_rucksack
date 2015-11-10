package alg;

import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;
import kpp.KPOverflowException;

public class CDP1 implements IAlgorithm {
	@Override
	public CKnapSack solve(CProblem problem) {
		// the max capacity of the KS
		int c = problem.getKnapSack().getMaxCapacity();
		// the elements
		CElement[] elems = problem.getElements();
		
		// the optimal solution z[j][d] with j:= the current element and d:= the current capacity
		int[][] z = new int[elems.length + 1][c + 1];
		
		for(int j = 1; j <= elems.length; j++) {
			// the weigth and profit of current element
			int w = elems[j - 1].getWeight();
			int p = elems[j - 1].getProfit();
			
			// item j is too large to be packed
			for(int d = 0; d < (w > c ? c : w); d++)
				z[j][d] = z[j - 1][d];
			// item j may be packed
			for(int d = w; d <= c; d++)
				if(z[j - 1][d - w] + p > z[j - 1][d])
					z[j][d] = z[j - 1][d - w] + p;
				else
					z[j][d] = z[j - 1][d];
		}
		
		// fill the KS with the optimal solution
		CKnapSack solution = new CKnapSack(c);
		for(int j = z.length - 1; j > 0; j--) {
			try {
				if(z[j][c] == z[j - 1][c - elems[j - 1].getWeight()] + elems[j - 1].getProfit()) {
					solution.addElement(elems[j - 1].copy());
					c -= elems[j - 1].getWeight();
				}
			}
			catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
			catch(KPOverflowException e) {
				break;
			}
		}
		
		return solution;
	}
}