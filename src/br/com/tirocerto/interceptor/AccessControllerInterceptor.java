package br.com.tirocerto.interceptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.bronx.vraptor.restrictrex.exception.DestinationException;
import br.com.bronx.vraptor.restrictrex.exception.RestrictionAnnotationException;
import br.com.bronx.vraptor.restrictrex.restriction.RestrictionChecker;
import br.com.bronx.vraptor.restrictrex.restriction.RestrictionResult;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.Results;
import br.com.tirocerto.session.AssociateLogged;

/** 
* This interceptor controls the access to the resources, based on their 
* restrictions annotations.<br> 
* The restrictions might be method restrictions or resource restrictions, 
* according to where the annotations had been placed. 
*  
* @author Diego Maia da Silva a.k.a. Bronx 
*/  
@Intercepts
public class AccessControllerInterceptor implements Interceptor {   
    private Result result;  
    private AssociateLogged associateLogged;
    private RestrictionChecker restrictionChecker;
    
	private Log logger = LogFactory.getLog(AccessControllerInterceptor.class);
      
    public AccessControllerInterceptor(AssociateLogged associateLogged, Result result, RestrictionChecker restrictionChecker){  
        this.result = result;   
        this.associateLogged = associateLogged; 
        this.restrictionChecker = restrictionChecker;
    }  
      
    @Override  
    public boolean accepts(ResourceMethod method) {  
        return this.restrictionChecker.hasRestriction(method.getMethod());  
    }  
  
    /* (non-Javadoc)
     * @see br.com.caelum.vraptor.interceptor.Interceptor#intercept(br.com.caelum.vraptor.core.InterceptorStack, br.com.caelum.vraptor.resource.ResourceMethod, java.lang.Object)
     */
    public void intercept(InterceptorStack stack, ResourceMethod resourceMethod,  
            Object resourceInstance) throws InterceptionException {  
        RestrictionResult restrictionResult;  
        /* 
        this.restrictionChecker.setDefaultAccessDeniedPage("/access/denied/page"); 
        Nao precisa mais utilizar esses metodos, mas se quiser, vc pode continuar utilizando-os.  
        Ao inves disso, utilize as annotations @LoginPage e @AccessDeniedPage. 
        this.restrictionChecker.setDefaultLoginPage("/login/page"); 
        */  
        try {  
        	restrictionResult = this.restrictionChecker.checkRestrictions(resourceMethod.getMethod(), this.associateLogged.getAssociateProfile());   
        } catch (RestrictionAnnotationException exception) {  
			logger.debug("Erro AccessControllerInterceptor.intercept - Exception: RestrictionAnnotationException - Data: " + new Date(), exception);
            throw new InterceptionException(exception);  
        } catch (DestinationException exception) {  
			logger.debug("Erro AccessControllerInterceptor.intercept - Exception: DestinationException - Data: " + new Date(), exception);
            throw new InterceptionException(exception);  
        }  
        if (restrictionResult.isRestricted()){  
            if (restrictionResult.isHttp403()) {  
                this.result.use(Results.http()).sendError(403);  
            } else {  
                this.result.use(Results.page()).redirectTo(restrictionResult.getDestination());  
            }  
        } else {  
            stack.next(resourceMethod, resourceInstance);  
        }  
    }  
}
