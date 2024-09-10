package com.ontop.test.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	 private int statusCode;
	  private Date timestamp;
	  private String message;
	  private String description;
}
