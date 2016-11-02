package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Demo; 

@Component 
@Transactional 
public class DemoToStringConverter implements Converter<Demo, String>{ 

	@Override 
	public String convert(Demo demo){ 
		String result; 
		if(demo == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(demo.getId()); 
		} 
		return result; 
	} 

} 
