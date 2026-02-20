package testquantity;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import measure.FeetEquality;

public class TestFeetEquality {
	FeetEquality feet1;
	FeetEquality feet2;
    @Test
    public void testEquals() {
    	feet1 = new FeetEquality(1);
    	feet2 = new FeetEquality(1);
    	assertTrue(feet1.equals(feet2));
    }
    
    @Test
    public void testDifferent() {
    	feet1 = new FeetEquality(1);
    	feet2 = new FeetEquality(2);
    	assertTrue(!feet1.equals(feet2));
    }
    
    @Test
    public void testNull() {
    	feet1 = new FeetEquality(1);
    	feet2 = null;
    	assertTrue(!feet1.equals(feet2));
    }
    
    @Test
    public void testSameRefferencet() {
    	feet1 = new FeetEquality(1);
    	
    	assertTrue(feet1.equals(feet1));
    }
    
    @Test
    public void testEmptyVariable() {
    	feet1 = new FeetEquality(1);
    	feet2 = new FeetEquality();
    	assertTrue(!feet1.equals(feet2));
    }
    
}
