package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.WhitePaperRepository;

import domain.WhitePaper;

@Component 
@Transactional 
public class StringToWhitePaperConverter implements Converter<String, WhitePaper>{ 

	@Autowired 
	WhitePaperRepository whitePaperRepository; 

	@Override 
	public WhitePaper convert(String text){ 
		WhitePaper result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = whitePaperRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
