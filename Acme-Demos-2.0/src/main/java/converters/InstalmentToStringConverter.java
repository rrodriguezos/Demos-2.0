package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Instalment;

@Component 
@Transactional 
public class InstalmentToStringConverter implements Converter<Instalment, String>{ 

	@Override 
	public String convert(Instalment instalment){ 
		String result; 
		if(instalment == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(instalment.getId()); 
		} 
		return result; 
	} 

} 
