package com.cognizant.openshift.poc.delegator;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.openshift.poc.model.TicketDetails;
import com.cognizant.openshift.poc.repo.TicketManagementRepo;


@Component
public class TicketManagementDelegator {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TicketManagementRepo repository;
	
	public TicketDetails createTicket(TicketDetails ticket) {
		LOGGER.info(" TicketManagementDelegator createTicket:: "+ticket);
		return repository.save(ticket);
	}
	
	public TicketDetails getTicketDetails(TicketDetails ticket) {
		LOGGER.info(" TicketManagementDelegator getTicketDetails Start:: "+ticket);
		TicketDetails ticketDetails=null;
		Optional<TicketDetails> ticketOpt=repository.findById(ticket.getTicketid());
		if(ticketOpt.isPresent()) {
			ticketDetails=ticketOpt.get();
			LOGGER.info(" TicketManagementDelegator ticketOpt.get() :: "+ticketOpt.get());
		}
		LOGGER.info(" TicketManagementDelegator getTicketDetails:: "+ticketDetails);
		return ticketDetails;
	}
	
	public List<TicketDetails> getTicketByCreatedBy(TicketDetails ticket) {
		List<TicketDetails> ticketList=null;
		if(null!=ticket && null!= ticket.getCreateby() && !"".equals(ticket.getCreateby())) {
			if("*".equals(ticket.getCreateby())) {
				ticketList=repository.findAll();
			}else {
				ticketList=repository.findByCreateby(ticket.getCreateby());
			}
		}
		LOGGER.info(" TicketManagementDelegator getTicketByCreatedBy:: "+ticketList);
		return ticketList;
	}
	
	public boolean deleteTicketById(TicketDetails ticket) {
		LOGGER.info(" TicketManagementDelegator deleteTicket Start:: "+ticket);
		if(null!=ticket && null!=ticket.getTicketid()) {
			repository.deleteById(ticket.getTicketid());
			return true;
		}
		LOGGER.info(" TicketManagementDelegator deleteTicket:: "+ticket);
		return false;
	}
}
