package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Bank;

@Component 
@Transactional 
public class BankToStringConverter implements Converter<Bank, String> {

	@Override 
	public String convert(Bank bank){ 
		String result; 
		if(bank == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(bank.getId()); 
		} 
		return result; 
	} 

}
