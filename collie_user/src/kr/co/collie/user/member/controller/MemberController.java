package kr.co.collie.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.collie.user.member.domain.LoginDomain;
import kr.co.collie.user.member.service.MemberService;
import kr.co.collie.user.member.vo.FindIdVO;
import kr.co.collie.user.member.vo.JoinVO;
import kr.co.collie.user.member.vo.LoginVO;

@Controller
public class MemberController {
	
	@RequestMapping(value = "/login_form.do",method = GET)
	public String loginForm() {
		
		return "member/login_form";//이걸 리턴시키면 WEB-INF/views/login_frm.jsp로 이동한다는 의미에요!
		
	}
	
	@RequestMapping(value = "/login_process.do",method = POST)
	public String login(LoginVO loginVO, Model model) {
	
		MemberService mems=new MemberService();
		LoginDomain loginDomain=mems.login(loginVO);
		model.addAttribute("login_info",loginDomain);
		
		return "login_result";
		 
	}//login
	
	@RequestMapping(value="/join_clause.do", method = GET)
	public String joinForm() {
		
		
		return "member/join_clause";
	}//joinForm
	
	@RequestMapping(value="/join_form.do", method = GET)
	public String joinClause() {
		
		
		return "member/join_frm";
	}//joinForm
	
	@RequestMapping(value="/join_process.do", method= POST)
	public String join(JoinVO jVO, HttpServletRequest request) {
		MemberService ms = new MemberService();
		
		jVO.setId(request.getParameter("id"));
		jVO.setPass(request.getParameter("pass"));
		jVO.setName(request.getParameter("name"));
		jVO.setEmail(request.getParameter("email"));
		jVO.setPhone(request.getParameter("phone"));
		jVO.setZipcode(request.getParameter("zipcode"));
		jVO.setAddr(request.getParameter("addr"));
		jVO.setAddr_detail(request.getParameter("addr_detail"));
		jVO.setIp(request.getRemoteAddr());
		
		ms.join(jVO);
		
		return "member/join";
	}//join
	
	@RequestMapping(value="/id_chk_ajax.do", method=GET)
	@ResponseBody
	public String dupIdAjax(String id) {
		
		MemberService ms = new MemberService();
		
		return ms.dupIdCheck(id);
	}//dupIdAjax
	
	@RequestMapping(value="/email_chk_ajax.do", method=GET)
	@ResponseBody
	public String dupEmailAjax(String email) {
		
		MemberService ms = new MemberService();
		
		return ms.dupEmailCheck(email);
	}//dupEmailAjax
	
	@RequestMapping(value = "/find/idForm.do",method = GET) 
	public String findIdForm() {
		
		  
		return "find/idForm";//이걸 리턴시키면 WEB-INF/views/login_frm.jsp로 이동한다는 의미에요!
		
	}
	
	@PostMapping(value = "/find_process.do")
	public String findId(FindIdVO fidVO,Model model) {
		MemberService ms = new MemberService();
		model.addAttribute("user_id",ms.findId(fidVO));
		return "find/id";
	}
	

}//class