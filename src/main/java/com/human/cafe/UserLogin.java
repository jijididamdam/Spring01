package com.human.cafe;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLogin {
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session,
						@RequestParam("id") String id,
						@RequestParam("pass") String pass) {
		
		/*로그인 절차
		 * 1. 파라미터 받는다 2. 쿼리실행후 결과값에 따라 판다   ~ 서비스단이 필요
		 * 현재는 테스트용으로 한다.
		*/
		if(id.equals("ttt")) {   // 쿼리 실행 후 아이디와 패스워드가 정상적인 경우 리턴 받는 것으로 수정
			//로그인 성공인 경우   >> 세션처리
			
			if(session.getAttribute("login")!=null) {
				session.removeAttribute("login");   // 기존 세션 지우기 > 일종의 초기화 ~ 한 번 더 확인하는 것
			}
			
			
			session.setAttribute("login", id);   // 세션처리
			session.setAttribute("grade", "vip");  // 처리하고 싶은 변수를 선택하여 등록, 객체도 가능, 리스트도 가능
			
			
			
		}else {
			//로그인 실패인 경우
			
		}
		// 여기까지가 테스트 용 ~ 나중에 디비 연동 시켜야함
		
		System.out.println("로그인 공사중");
		return "home";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate(); // 세션을 무력화
		
		return "home";
	}
}
