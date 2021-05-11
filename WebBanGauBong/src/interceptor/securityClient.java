package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import web.entity.User;

public class securityClient extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null)
		{
			System.out.println("Bạn không có quyền");
			response.sendRedirect(request.getContextPath()+"/login.htm");
			
			return false;
		}
		
		System.out.println("Chấp nhận");
		return true;
	}

}
