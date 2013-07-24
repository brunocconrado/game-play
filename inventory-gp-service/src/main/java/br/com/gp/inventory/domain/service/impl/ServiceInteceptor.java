package br.com.gp.inventory.domain.service.impl;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.xml.rpc.ServiceException;

import br.com.embracon.j4e.logging.Logger;
import br.com.gp.inventory.domain.service.exception.AssociationViolationException;

@Interceptor
public class ServiceInteceptor  {
	
	
	@AroundInvoke
    public Object serviceInterceptor(InvocationContext context) throws Exception {
		
		try {
			Logger.getLogger().info(String.format("Intercepting method %s ", context.getMethod().getName()));
			return context.proceed();
		} catch (Throwable e) {
			Logger.getLogger().error(String.format("Erro ao executar o servico %s no metodo %s", 
						context.getClass().getName(), context.getMethod().getName()));
			
			if(e.getCause() instanceof Exception) {
				//TODO: Mudar o exception que Ž capturado
				throw new AssociationViolationException(e);
			}
			
			throw new ServiceException(e);
		}
    }

}
