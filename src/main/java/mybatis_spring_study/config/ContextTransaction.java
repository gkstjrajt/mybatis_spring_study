package mybatis_spring_study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableAspectJAutoProxy
@Configuration
public class ContextTransaction {

	@Autowired
	PlatformTransactionManager transactionManager;
	
//	@Bean
//	public TransactionInterceptor transactionAdvice() {
//		
//	}
	
	
}
