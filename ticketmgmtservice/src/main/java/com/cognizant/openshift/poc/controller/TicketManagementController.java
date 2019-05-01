/**
 * 
 */
package com.cognizant.openshift.poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.openshift.poc.datatransformer.TicketManagementDataTransformer;
import com.cognizant.openshift.poc.delegator.TicketManagementDelegator;
import com.cognizant.openshift.poc.model.Result;
import com.cognizant.openshift.poc.model.TicketDetails;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/ticket")
public class TicketManagementController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private TicketManagementDelegator delegator; 
	
	/**
	 * Check Ping
	 * 
	 * @return String
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String hello() {
		LOGGER.info("TicketManagementController hello start...");
		return "Welcome to Ticket Management Service...";
	}
	
	@RequestMapping(value="/createTicket", method=RequestMethod.POST, headers="Accept=application/json", produces="application/json", consumes="application/json")
	public @ResponseBody ResponseEntity<Result> createTicket(@RequestBody TicketDetails ticket) {
		LOGGER.info("TicketManagementController createTicket start...");
		ticket=delegator.createTicket(ticket);
		Result result=TicketManagementDataTransformer.setResultForTicketDetails(ticket);
		LOGGER.info("TicketManagementController createTicket end...");
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateTicket", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> updateTicket(@RequestBody TicketDetails ticket) {
		LOGGER.info("TicketManagementController updateTicket start...");
		ticket=delegator.createTicket(ticket);
		Result result=TicketManagementDataTransformer.setResultForTicketDetails(ticket);
		LOGGER.info("TicketManagementController updateTicket end...");
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/queryTicket", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> queryTicket(@RequestBody TicketDetails ticket) {
		LOGGER.info("TicketManagementController queryTicket start...");
		List<TicketDetails> ticketList =delegator.getTicketByCreatedBy(ticket);
		//Result result=TicketManagementDataTransformer.setResultForTicketDetailsList(ticketList);
		Result result = (!CollectionUtils.isEmpty(ticketList)) ? (new Result(HttpStatus.OK.toString(), "SUCCESS", ticketList)):(new Result(HttpStatus.OK.toString(), "DATA NOT FOUND", null));
		LOGGER.info("TicketManagementController queryTicket end...");
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getTicketDetails", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> getTicketDetails(@RequestBody TicketDetails ticket) {
		LOGGER.info("TicketManagementController getTicketDetails start...");
		ticket=delegator.getTicketDetails(ticket);
		LOGGER.info("TicketManagementController getTicketDetails :: "+ticket);
		Result result=TicketManagementDataTransformer.setResultForTicketDetails(ticket);
		LOGGER.info("TicketManagementController getTicketDetails end...");
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteTicket", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> deleteTicket(@RequestBody TicketDetails ticket) {
		LOGGER.info("TicketManagementController deleteTicket start...");
		Result result=null;
		if(delegator.deleteTicketById(ticket)) {
			result=TicketManagementDataTransformer.setResultForTicketDetails(ticket);
		}
		LOGGER.info("TicketManagementController deleteTicket end...");
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

}
