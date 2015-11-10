package alg;

import kpp.CElement;
import kpp.CKnapSack;
import kpp.CProblem;
import kpp.KPOverflowException;

public class CExtGreedy_3_4 implements IAlgorithm {

	@Override
	public CKnapSack solve(CProblem problem) {
		// solve greedy
		CKnapSack greedy_solution = new CGreedy().solve(problem);
		
		CElement[] elements = problem.getElements();
		
		CElement[] pairedElements = new CElement[elements.length * (elements.length - 1)];

		
		int index = 0;
		for( int i = 0; i < elements.length; i++ ){
			
			for( int j = 0; j < elements.length; j++){
				
				if( i == j) continue;
				
				CElement newElement = new CElement(	elements[i].getWeight() + elements[j].getWeight(), 
													elements[i].getProfit() + elements[j].getProfit() );
				
				pairedElements[index++] = newElement;
			}
		}
		
		for( CElement current_element : pairedElements ){
			
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
