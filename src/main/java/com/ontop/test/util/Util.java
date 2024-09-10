package com.ontop.test.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Util {


	public static long getDifferencesDays(LocalDate beginDate, LocalDate endDate) {

		return ChronoUnit.DAYS.between(beginDate, endDate);
	}

	public static LocalDate getLongToLocalDate(Long lastAccess) {

		return Instant.ofEpochSecond(lastAccess)
				.atZone(ZoneId.systemDefault()).toLocalDate();

	}
	
	public static  Float calculateFeeAmount(Float amount, float fee) {
		  return(fee * amount) / 100;     
	}
}