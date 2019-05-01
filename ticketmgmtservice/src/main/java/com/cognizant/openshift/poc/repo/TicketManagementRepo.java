package com.cognizant.openshift.poc.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.openshift.poc.model.TicketDetails;

public interface TicketManagementRepo extends JpaRepository<TicketDetails, Integer> {
	
	public List<TicketDetails> findByCreateby(String createby);

}
