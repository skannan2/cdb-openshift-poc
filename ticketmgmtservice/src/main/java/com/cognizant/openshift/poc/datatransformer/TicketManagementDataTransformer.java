package com.cognizant.openshift.poc.datatransformer;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import com.cognizant.openshift.poc.model.Result;
import com.cognizant.openshift.poc.model.TicketDetails;

public class TicketManagementDataTransformer {
	public static <T> Result setResultForTicketDetails(T obj) {
		return Optional.ofNullable(obj).flatMap(tcktDetails -> {
			Result result = new Result(HttpStatus.OK.toString(), "SUCCESS", tcktDetails);
			return Optional.of(result);
		}).orElse(new Result(HttpStatus.OK.toString(), "DATA NOT FOUND", null));
	}

	public static Result setResultForTicketDetailsList(List<TicketDetails> ticketList) {
		return (!CollectionUtils.isEmpty(ticketList)) ? (new Result(HttpStatus.OK.toString(), "SUCCESS", ticketList))
				: (new Result(HttpStatus.OK.toString(), "DATA NOT FOUND", null));
	}
}
