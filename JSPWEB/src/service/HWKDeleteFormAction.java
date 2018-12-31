package service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 여기서 현재 로그인되어있는 회원의 아이디 패스워드값을 가져와서 들고있게 하자
// 다른 회원정보를 입력해서 다른회원이 삭제되면 안되니까

public class HWKDeleteFormAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
		}catch(Exception e) {	System.out.println(e.getMessage());	}
		return "deleteForm.jsp";
	}
}