package com.wolfhack.service.user.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class DomainAspect {

	@Pointcut("@annotation(com.wolfhack.service.user.logging.annotations.AOPLogging)")
	public void processAOPLogging() { };

	@Around("processAOPLogging()")
	public Object execDatabaseEntryPoint(ProceedingJoinPoint joinPoint) throws Throwable {
		final StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		Object retVal = joinPoint.proceed();
		stopWatch.stop();

		log.info(
			"Class {} endpoint {} executed in {}ms",
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			stopWatch.getTotalTimeMillis()
		);

		return retVal;
	}

	@AfterThrowing(pointcut = "processAOPLogging()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		log.error(
			"Exception in class {} endpoint {}. Cause: {}",
			joinPoint.getTarget().getClass().getSimpleName(),
			joinPoint.getSignature().getName(),
			exception.getCause() != null ? exception.getCause() : "NULL"
		);
	}

}
