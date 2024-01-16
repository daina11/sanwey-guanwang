package sw.common.sys;

import javax.persistence.AttributeConverter;
import java.util.Date;

/**
 * Converter - 日期
 * 数据库存储Unix时间戳（效率高、支持时区），Java中使用java.util.Date开发。
 * 存取实体时，本类实现Unix时间戳与java.util.Date的转换。
 * @author wwh
 * @version 1.0
 */
public final class DateConverter implements AttributeConverter<Date, Long> {

	@Override
	public Long convertToDatabaseColumn(Date attribute) {
		return attribute == null ? null : attribute.getTime();
	}

	@Override
	public Date convertToEntityAttribute(Long dbData) {
		return dbData == null ? null : new Date(dbData);
	}

}
