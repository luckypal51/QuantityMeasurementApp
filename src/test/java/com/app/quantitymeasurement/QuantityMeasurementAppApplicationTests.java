package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.unit.VolumeUnit;

@SpringBootTest
class QuantityMeasurementAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void getTested() {
	   Quantity<VolumeUnit> v1 = new Quantity<>(2.0,VolumeUnit.LITRE);
	   Quantity<VolumeUnit> v2 = new Quantity<>(2.0,VolumeUnit.LITRE);
	   assertEquals(4.0,v1.add(v2).getValue());
	}
}
