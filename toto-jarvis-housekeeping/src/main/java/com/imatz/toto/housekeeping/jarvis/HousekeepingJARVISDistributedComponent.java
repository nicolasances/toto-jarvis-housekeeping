package com.imatz.toto.housekeeping.jarvis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.imatz.toto.jarvis.base.JARVISDistributedComponent;
import com.imatz.toto.jarvis.base.alert.JARVISAEIExecutorFactory;

/**
 * Implementation of the {@link JARVISDistributedComponent} interface, providing
 * the JARVIS distributed logic of the housekeeping App.
 * 
 * @author C308961
 *
 */
@Service
@Scope("singleton")
public class HousekeepingJARVISDistributedComponent implements JARVISDistributedComponent {
	
	@Value("${housekeeping.app.code}")
	private String appCode_;
	
	@Autowired
	private HousekeepingJARVISActionExecutorFactory factory_;

	public String getAppCode() {
		return appCode_;
	}

	@Override
	public JARVISAEIExecutorFactory getAEIExecutorFactory() {
		return factory_;
	}

}
