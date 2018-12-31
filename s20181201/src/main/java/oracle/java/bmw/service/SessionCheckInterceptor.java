package oracle.java.bmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("interceptor pre handle start");
		try {
			if(request.getSession().getAttribute("sessionId") == null) {
				System.out.println("sessionId가 null입니다.");
				response.sendRedirect(request.getContextPath()+"/LoginView.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("posthandle start");
//		super.postHandle(request, response, handler, modelAndView);
//	}

}
