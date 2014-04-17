package com.itucity.dsmp.common.exception;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itucity.dsmp.common.util.ObjUtils;
import com.itucity.dsmp.common.util.SequenceUtil;

/**
 * 异常拦截器
 */
@Component
public class ExceptionAroundAdvice {
	
	private static Logger log=LoggerFactory.getLogger(ExceptionAroundAdvice.class);
	
	private String getInvokeMethod(ProceedingJoinPoint jointPoint) {
		return jointPoint.getSignature().getDeclaringType().getSimpleName()+"."+jointPoint.getSignature().getName();
	}

	/**
	 * 
	 * @param jointPoint
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(ProceedingJoinPoint jointPoint) throws Throwable{
		Object retVal = null;
		long beginTime=System.currentTimeMillis();
		try {
			log.debug("[调用方法开始]"+this.getInvokeMethod(jointPoint));
			retVal = jointPoint.proceed();
			return retVal;
		} catch (org.hibernate.JDBCException e) {
			int errorCode=e.getErrorCode();
			String msg;
			if((errorCode>-21000&&errorCode<=-20000)||(errorCode>=20000&&errorCode<21000)) {
				//调存储过程错误
				msg=e.getMessage();
				msg=msg.substring(msg.indexOf(": "));//去掉错误前缀ORA-20999:
			} else {
				msg=errorSql;
			}
			msg+=this.getSequence();
			log.error(msg+"\nSQL="+e.getSQL()+"\n", e);
			throw new SystemRuntimeException(errorCode+"", msg, ObjUtils.getDebugMsg(e));
		} catch (SystemRuntimeException e) {
			String msg=e.getMessage();
			msg+=this.getSequence();
			log.error(msg, e);
			throw new SystemRuntimeException(e.getErrorCode(), msg, ObjUtils.getDebugMsg(e));
		}catch (Exception e) {
			String msg = errorSystem;
			msg+=this.getSequence();
			log.error(msg, e);
			throw new SystemRuntimeException("errorSystem", msg, ObjUtils.getDebugMsg(e));
		}finally{
			log.debug("[调用方法结束.耗时(s):"+(System.currentTimeMillis()-beginTime)/1000.0+"]"+this.getInvokeMethod(jointPoint));
		}
	}
	
	private String getSequence() {
		return "(错误序列号: "+serverId+String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", new Date())+SequenceUtil.getSequence()+")";
	}
	
	@Value("${serverId}")
	private String serverId;
	
	@Value("${errorNoResult}")
	private String errorNoResult;
	
	@Value("${errorSql}")
	private String errorSql;
	
	@Value("${errorSystem}")
	private String errorSystem;
}
