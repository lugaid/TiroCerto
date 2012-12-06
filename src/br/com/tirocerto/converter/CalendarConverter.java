package br.com.tirocerto.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(Calendar.class)
public class CalendarConverter implements Converter<Calendar> {

	@Override
	public Calendar convert(String value, Class<? extends Calendar> arg1, ResourceBundle arg2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(value));
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
