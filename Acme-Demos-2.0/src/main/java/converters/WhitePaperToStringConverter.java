package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.WhitePaper;

@Component 
@Transactional 
public class WhitePaperToStringConverter implements Converter<WhitePaper, String> {

	@Override 
	public String convert(WhitePaper whitePaper){ 
		String result; 
		if(whitePaper == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(whitePaper.getId()); 
		} 
		return result; 
	} 

}
