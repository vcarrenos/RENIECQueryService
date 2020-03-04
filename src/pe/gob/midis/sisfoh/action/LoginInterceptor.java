package pe.gob.midis.sisfoh.action;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5351616755186384224L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();

		Object user = session.get("user");

		if (user == null) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}
}
