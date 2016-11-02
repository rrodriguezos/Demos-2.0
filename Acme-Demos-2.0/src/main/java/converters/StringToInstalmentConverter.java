package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.InstalmentRepository;
import domain.Instalment;

@Component 
@Transactional
public class StringToInstalmentConverter implements Converter<String, Instalment>{ 

	@Autowired 
	InstalmentRepository instalmentRepository; 

	@Override 
	public Instalment convert(String text){ 
		Instalment result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = instalmentRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
