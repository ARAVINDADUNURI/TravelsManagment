package com.aravi.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aravi.model.BusDetails;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
    	log.info("Travels Management Application started successfully and is ready to accept requests.");
    }
    
    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void requestRecived(JoinPoint joinPoint) {
    	log.info("Request Recived : {}, ", joinPoint.getSignature().getName());
    }

    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entered Into method : {}", joinPoint.getSignature().getName());
    }
    
    @AfterReturning("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logAfterMethodCompleted(JoinPoint joinPoint) {
    	log.info("Method Completed Sucessfully : {}", joinPoint.getSignature().getName());
    }
    
    @AfterReturning("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logRecoredSavedSucessfully(JoinPoint joinPoint) {
    	log.info("Recored Saved sucessfully : {}", joinPoint.getSignature().getName());
    }
   
    @After("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
    	log.info("Returning From Method Method Finished : {}", joinPoint.getSignature().getName());
    }
    
    
  /*  @After("execution(* com.aravi.controller.BusDetailsController.updateBusDetails(..))")
    public void logAfterUpdated(JoinPoint joinPoint) {
    	log.info("Bus Details Updated Sucessfully");
    }*/
    
    @After("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logexecutionCompleted(JoinPoint executionTime) {
    	log.info("Execution completed in {} ms.", executionTime);
    }
    
//    Debug Levels
    
    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logMethodArguments(JoinPoint joinPoint) {

        log.debug("Method arguments: {}", Arrays.toString(joinPoint.getArgs()));

    }
    @AfterReturning("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logFetchingDataFromDatabase(JoinPoint joinPoint) {
    	log.debug("Fetching Bus Details From Database");
    }
    
    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logValidatingRequest(JoinPoint joinPoint) {
    	  log.debug("Validating request data.");
    }
    
    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logSavingTheData(JoinPoint joinPoint) {
    	 log.debug("Saving bus details into database.");
    }
    
    
    @AfterReturning(
    	    pointcut = "execution(* com.aravi.controller.BusDetailsController.getAllBusDetails(..))",
    	    returning = "busList"
    	)
    public void logAfterDataRetrived(JoinPoint joinPoint, List<BusDetails> busList) {
    log.debug("Retrieved {} bus records from database.", busList.size());
    }
    
    @After("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logAfterCompletion(JoinPoint joinPoint) {
    	
    	 log.debug("Database operation completed successfully.");
    	
    }
    
    
//   Warn Logger Levels
    @AfterThrowing("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logDataNotFound(JoinPoint joinPoint) {
    	log.warn("No Data Found For the request Resource");
    }
   
    @AfterThrowing("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logInvalidInput(JoinPoint joinPoint) {
    	log.warn("Invalid Input Recived");
    }
    
    @Before("execution(* com.aravi.controller.BusDetailsController.*(..))")
    public void logDepricatedAPIusage(JoinPoint joinPoint) {
    	log.warn("Depricated Endpoint Invoked");
    }
    
//    Error Levels
    
    @AfterThrowing(
    		pointcut = "execution(* com.aravi.serviceimpl.*.*(..))",
    		throwing = "exception"
    		)
    public void handleException(Exception exception) {
    	log.error("Encountered Exception : {}", exception.getMessage());
    }
    
  /*  @AfterThrowing(
    		pointcut = "execution(* com.aravi.serviceimpl.*.*(..))",
    		throwing = "exception"
    		)
    public void handleDataBaseException(Exception exception) {
    	log.error("Encountered Exception : {}", exception.getMessage());
    	
    }*/
    
    
    
    
}

