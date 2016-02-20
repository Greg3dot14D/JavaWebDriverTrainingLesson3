package ru.greg3d.asserts;

import org.testng.SkipException;

public abstract class Assert extends org.testng.Assert {
	public static void ignore(String arg0){
		throw new SkipException(arg0);
	}
	
	public static void ignoreTrue(boolean condition, String arg0){
		try{
			org.testng.Assert.assertTrue(condition, arg0);
		}catch(AssertionError e){
			throw new SkipException(e.getMessage());
		}
	}
	
	public static void ignoreFalse(boolean condition, String arg0){
		try{
			org.testng.Assert.assertFalse(condition, arg0);
		}catch(AssertionError e){
			throw new SkipException(e.getMessage());
		}
	}
	
	public static void ignoreEquals(double actual, double expected, String arg0){
		try{
			org.testng.Assert.assertEquals(actual , expected, arg0);
		}catch(AssertionError e){
			throw new SkipException(e.getMessage());
		}
	}
	
	public static void ignoreNotEquals(double actual, double expected, String arg0){
		try{
			org.testng.Assert.assertNotEquals(actual, expected, arg0);
		}catch(AssertionError e){
			throw new SkipException(e.getMessage());
		}
	}
}
