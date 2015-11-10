package alg;

import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;
import kpp.KPOverflowException;

public class CExtGreedy_1_2 implements IAlgorithm {

	@Override
	public CKnapSack solve(CProblem problem) {
		// solve greedy
		CKnapSack greedy_solution = new CGreedy().solve(problem);
		
		for( CElement current_element : problem.getElements() ){
			
			if( (current_element.getProfit() > greedy_solution.getCurrentProfit()) &&
				(greedy_solution.getMaxCapacity() >= current_element.getWeight()) ){
				
				greedy_solution.reset();
				try {
					greedy_solution.addElement(current_element);
				} catch (KPOverflowException e) {
					e.printStackTrace();
				}
			}
			
		}

		return greedy_solution;
	
	}

}
