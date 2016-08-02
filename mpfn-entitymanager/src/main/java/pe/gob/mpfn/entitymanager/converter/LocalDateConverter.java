package pe.gob.mpfn.entitymanager.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;



/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class LocalDateConverter.
 *
 * @author OSIS
 * @version 1.0 , 11/04/2016
 */


@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public Date convertToDatabaseColumn(LocalDate date) {
		// TODO Auto-generated method stub
		return Date.from(date.atStartOfDay().atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toInstant());
	}

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public LocalDate convertToEntityAttribute(Date value) {
		// TODO Auto-generated method stub
		return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toLocalDate();
	}

}
