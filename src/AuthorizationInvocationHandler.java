import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Madhura 
 * This class is a handler class where all calls
 * to the proxy will be forwarded to the handler which implements the general
 * InvocationHandler Interface.
 * 
 *  
 */

public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {

	private static final long serialVersionUID = 1L;
	private Object objectImpl;
 
	public AuthorizationInvocationHandler(Object impl) {
		this.objectImpl = impl;
	}
 
	/**
	 * All calls to the proxy passes through the invoke method which checks for the annotation
	 * If the annotation is present and the value holds true it executes the method.
	 * else it throws a custom exception.
	 */
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.isAnnotationPresent(RequiresRole.class)) {
			RequiresRole test = method.getAnnotation(RequiresRole.class);
			SessionInterface session = (SessionInterface) args[0];

			if (session.getRole().equals(test.value())) {
				return method.invoke(objectImpl, args);
			} else {
				// Throws a custom exception
				throw new AuthorizationException(method.getName());
			}
		} else {
			return method.invoke(objectImpl, args);
		}   
	}
}

