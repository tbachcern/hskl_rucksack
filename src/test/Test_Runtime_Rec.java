package test;

import org.junit.Test;

import alg.CRecursive;
import util.CUtils;
import kpp.CProblem;

public class Test_Runtime_Rec {

	
	@Test
	public void testRuntime(){
		
		long time = CUtils.measureTime(new CRecursive(), new CProblem(25, 30));
		
		System.out.println(time);
		
		
	}
}
