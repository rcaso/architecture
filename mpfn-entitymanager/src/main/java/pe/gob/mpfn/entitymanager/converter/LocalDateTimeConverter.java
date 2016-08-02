package pe.gob.mpfn.entitymanager.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class LocalDateTimeConverter.
 *
 * @author OSIS
 * @version 1.0 , 11/04/2016
 */

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
	 */
	@Override
	public Date convertToDatabaseColumn(LocalDateTime date) {
		// TODO Auto-generated method stub
		return Date.from(date.atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toInstant());
	}

	/* (non-Javadoc)
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Date value) {
		// TODO Auto-generated method stub
		return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toLocalDateTime();
	}

}
