package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import web.entity.User;

public class security extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null)
		{
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
		User u=new User();
		u=(User)session.getAttribute("user");
		if(u.getUserRole().equals("client"))
		{
			response.sendRedirect(request.getContextPath()+"/client/404.htm");
			return false;
		}
		System.out.println("Ban la Quan tri vien!");
		return true;
	}

}
