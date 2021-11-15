package ar.com.learsoft.rest.ws.model;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;
import java.time.format.*;
import java.util.Map;

import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import ar.com.learsoft.rest.ws.util.Definitions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetweenDaysQuery {
	@Past(message="La 'fecha desde' tiene que ser una fecha pasada")
	@DateTimeFormat(pattern = Definitions.DATE_FORMAT)
	private LocalDate firstDate;
	@PastOrPresent(message="La 'fecha hasta' no puede ser futurista. Tiene que ser actual o pasada.")
	@DateTimeFormat(pattern = Definitions.DATE_FORMAT)
	private LocalDate secondDate;
	
	private Logger logger = LogManager.getLogger(BetweenDaysQuery.class);
	
	private LocalDate getFormattedDate(Map<String, String> findByDateQueryParams, String dateLabel, DateTimeFormatter formatter) {
		String date= findByDateQueryParams.get(dateLabel);
		return LocalDate.parse(date, formatter);
	}
	public BetweenDaysQuery(Map<String, String> findByDateQueryParams) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Definitions.DATE_FORMAT);
        this.firstDate= this.getFormattedDate(findByDateQueryParams, Definitions.FIRSTDATE_LABEL, formatter);
		this.secondDate= this.getFormattedDate(findByDateQueryParams, Definitions.SECONDDATE_LABEL, formatter);
		logger.info("Buscando entre fechas: "+ this.firstDate+"\t"+this.secondDate);
	}
	
	public long getFirstDateEpoch() {
		return this.firstDate.toEpochDay();
	}
	public long getSecondDateEpoch() {
		LocalDate today= LocalDate.now();
		if (this.secondDate.equals(today)) {
			Instant instant = Instant.now();
			return instant.toEpochMilli();
		}
		return this.secondDate.toEpochDay();
	}
	

}
