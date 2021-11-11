package ar.com.learsoft.rest.ws.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByDate {
	@NotNull
	@Min (value = 2021110900)
	private Long firstDate;
	@NotNull
	private Long secondDate;


}