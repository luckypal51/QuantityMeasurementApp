package measure.service;

import java.util.List;
import java.util.Objects;

import measure.entity.QuantityDTO;
import measure.model.Quantity;
import measure.model.QuantityMeasurementEntity;
import measure.model.QuantityModel;
import measure.reposistory.*;
import measure.unit.IMeasurable;
import measure.unit.LengthUnit;
import measure.unit.Temperature;
import measure.unit.VolumneUnit;
import measure.unit.WeightUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService{

	private IQuantityMeasurementRepository repository;
	
	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}
	
	private enum Operation{
		COMPARISION,CONVERSION,ARITHMETIC;
	}
	@Override
	public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		 QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		 QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		 
		 Quantity<IMeasurable> q3 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		 Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q2.getValue(),q2.getUnit());
		 
		 boolean result = q3.compare(q4);
		 repository.save(new QuantityMeasurementEntity(thisQuantityDTO,thatQuantityDTO,Operation.COMPARISION.toString(),result?"EQUAL":"NOT EQUAL"));
		 return result;
	}

	@Override
	public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		
		Quantity<IMeasurable> q3 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		q4 = q3.convertTo(q4);
		
		QuantityDTO q5 = new QuantityDTO(q4.getValue(), q1.getUnit().getUnitName(),q1.getUnit().getClass().getSimpleName());
		repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO, Operation.CONVERSION.toString(),q5));
		
		return q5;
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		
		QuantityModel<?> q1 =getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		
		Quantity<IMeasurable> q3 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		
		q4 = q3.add(q4);
		
		QuantityDTO q5 =  new QuantityDTO(q4.getValue(),q3.getUnit().getUnitName(),q3.getUnit().getClass().getSimpleName());
	    repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO, Operation.ARITHMETIC.toString(),q5));
		
	    return q5;
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetQuantityDTO) {
		QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		QuantityModel<?> q3 = getQuantityInstance(targetQuantityDTO);
		
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		Quantity<IMeasurable> q5 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		
		q5 = q4.add(q5,q3.getUnit());
		QuantityDTO q6 = new QuantityDTO(q5.getValue(),q5.getUnit().getUnitName(),q5.getUnit().getClass().getSimpleName());
		
		repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO,Operation.ARITHMETIC.toString(),q6));
		
		return q6;
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		
		Quantity<IMeasurable> q3 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		
		q4=q3.subtract(q4);
		
		QuantityDTO q5 = new QuantityDTO(q4.getValue(),q4.getUnit().getUnitName(),q4.getUnit().getClass().getSimpleName());
		repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO, Operation.ARITHMETIC.toString(),q5));
		
		return q5;
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetQuantityDTO) {
		QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		QuantityModel<?> q3 = getQuantityInstance(targetQuantityDTO);
		
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		Quantity<IMeasurable> q5 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		q5 = q4.subtract(q5,q3.getUnit());
		
		QuantityDTO q6 = new QuantityDTO(q5.getValue(),q5.getUnit().getUnitName(),q5.getUnit().getClass().getSimpleName());
		
		repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO,Operation.ARITHMETIC.toString(),q6));
		return q6;
	}

	@Override
	public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		
		Quantity<IMeasurable> q3 = new Quantity<IMeasurable>(q1.getValue(), q1.getUnit());
		Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q2.getValue(),q2.getUnit());
		
		q3 = q3.division(q4);
		QuantityDTO q5 = new QuantityDTO(q3.getValue(),q3.getUnit().getUnitName(),q3.getUnit().getClass().getSimpleName());
		
		repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO, Operation.ARITHMETIC.toString(),q5));
		return q5;
	}

	@Override
	public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetQuantityDTO) {
		 QuantityModel<?> q1 = getQuantityInstance(thisQuantityDTO);
		 QuantityModel<?> q2 = getQuantityInstance(thatQuantityDTO);
		 QuantityModel<?> q3 = getQuantityInstance(targetQuantityDTO);
		 
		 Quantity<IMeasurable> q4 = new Quantity<IMeasurable>(q1.getValue(),q1.getUnit());
		 Quantity<IMeasurable> q5 = new Quantity<IMeasurable>(q2.getValue(), q2.getUnit());
		 
		 q4 = q4.division(q5,q3.getUnit());
		 
		 QuantityDTO q6 = new QuantityDTO(q4.getValue(),q4.getUnit().getUnitName(),q4.getUnit().getClass().getSimpleName());
		 
		 repository.save(new QuantityMeasurementEntity(thisQuantityDTO, thatQuantityDTO,Operation.ARITHMETIC.toString(),q6));
		 
		 return q6;
	}
    private QuantityModel<?> getQuantityInstance(QuantityDTO dto){
    	switch(dto.getMeasurementType()) {
    	case "VOLUME":
    		return new QuantityModel<>(dto.getValue(),VolumneUnit.valueOf(dto.getUnit()));
    	case "WEIGHT":
    		return new QuantityModel<>(dto.getValue(),WeightUnit.valueOf(dto.getUnit()));
    	case "LENGTH":
    		return new QuantityModel<>(dto.getValue(),LengthUnit.valueOf(dto.getUnit()));
    	case "TEMPERATURE":
    		return new QuantityModel<>(dto.getValue(),Temperature.valueOf(dto.getUnit()));
    	default:
    		throw new IllegalArgumentException("The Unit Does Not Exists");
    	}
    }
    
    public static void main(String[] args) {
	  QuantityMeasurementServiceImpl imp = new  QuantityMeasurementServiceImpl(new QuantityMeasurementCacheRepository());
	  System.out.println("add "+imp.add(new QuantityDTO(24,"INCHES","LENGTH"), new QuantityDTO(2,"FEET","LENGTH")));
	  System.out.println("conversion "+imp.convert(new QuantityDTO(0.0,"FEET","LENGTH"),new QuantityDTO(24,"INCHES","LENGTH")));
	  
	}
}
