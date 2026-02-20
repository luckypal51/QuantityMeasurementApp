package testquantity;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import measure.FeetEquality;

public class TestFeetEquality {
	FeetEquality feet1;
	FeetEquality feet2;
    @BeforeEach
    public void setUp() {
    	feet1 = new FeetEquality(1);
    	feet2 = new FeetEquality(2);
    }
    
    @Test
    public void testEquals() {
    	assertTrue(feet1.equals(feet2));
    }
}
