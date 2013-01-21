package br.com.tirocerto.interceptor;

import org.hibernate.exception.ConstraintViolationException;  
  
import br.com.caelum.vraptor.InterceptionException;  
import br.com.caelum.vraptor.Intercepts;  
import br.com.caelum.vraptor.Result;  
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;  
import br.com.caelum.vraptor.interceptor.Interceptor;   
import br.com.caelum.vraptor.resource.ResourceMethod;  
import br.com.caelum.vraptor.util.hibernate.HibernateTransactionInterceptor;  
import br.com.caelum.vraptor.validator.Message;
  
  
@Intercepts 
@SuppressWarnings("unused")
public class ConstraintViolationInterceptor implements Interceptor {    
	private final Result result;
    private final Validator validator;
    
    public ConstraintViolationInterceptor(Result result, Validator validator) {    
        this.result = result;
        this.validator = validator;
    }    
    
    public boolean accepts(ResourceMethod method) {    
        return true;    
    }    
    
    public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {    
    	try {    
            stack.next(method,resourceInstance);    
        } catch (ConstraintViolationException e) {    
        	//validator.getErrors().add(e.get);
        }    
    }    
}   
