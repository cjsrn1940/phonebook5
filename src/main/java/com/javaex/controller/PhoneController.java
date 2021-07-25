package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;
	// 생성자
	// 메소드-gs
	// 메소드-일반
	
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[PhoneController.list]");
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		/************ @Autowired PhoneDao phoneDao; 로 new 해주기 있기 때문 **************/ 
		
		//Dao의 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();
		//System.out.println(personList);
		
		//model담기 (택배박스에 담기) --> ds전달된다 --> request의 attribute영역에 넣는다
		model.addAttribute("personList", personList);
	    
		//view
	    return "/WEB-INF/views/list.jsp";
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");

		return "/WEB-INF/views/writeForm.jsp";
	}

	
	/*
	// 쓰기
	// 파라미터가 있을때도 있고 없을때도 있는 경우
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam(value = "company", required = false, defaultValue = "-1") String company) {
		System.out.println("[PhoneController.write]");
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		return "";
	}
	*/

	// @ModelAttribute 일때
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.write]");

		// @ModelAttribute -> new ParsonVo 
		// 				   ->e 기본생성자 + setter

		System.out.println(personVo);
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		
		//Dao의 personInsert() 이용해서 데이터 저장
		int count = phoneDao.personInsert(personVo);
	
		//view --> 리다이렉트
		return "redirect:/list";
	}

	/*
	//파라미터를 한개씩 받을때
	  
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name, 
						@RequestParam("hp") String hp, 
						@RequestParam("company") String company) {
		System.out.println("[PhoneController.write]"); 
		
		System.out.println(name);
		System.out.println(hp); 
		System.out.println(company);
	 
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
	 
		return""; 
	}
	*/
	
	//삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("[PhoneController.delete]");
		
		System.out.println(personId);
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		
		//Dao의 personInsert() 이용해서 데이터 저장
		//phoneDao.personDelete(personId);
		
		return "redirect:/list";

	}
	
	//수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		System.out.println("[PhoneController.updateForm]");
		
		//PhoneDao phoneDao = new PhoneDao();	
		//PersonVo personVo = phoneDao.getPerson(personId);
		
		//model.addAttribute("pVo", personVo);

		return "/WEB-INF/views/updateForm.jsp";
	}
	
	//수정
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@RequestParam("id") int personId,
						 @RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		System.out.println("[PhoneController.update]");
		
		PersonVo personVo = new PersonVo(personId, name, hp, company);
		//PhoneDao phoneDao = new PhoneDao();	
		//phoneDao.personUpdate(personVo);

		return "redirect:/list";
	}
	
	
	/*********************************************************************************/
	//PathVariable 테스트
	@RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("PathVariable [read]");
		
		//localhost:8088/phonebook5/board/read/1
		//localhost:8088/phonebook5/board/read/2
		//localhost:8088/phonebook5/board/read/100
		
		System.out.println(boardNo);
		
		return "";
	}

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp"; // DispatcherServlet이야 /WEB-INF/views/test.jsp 이거 포워드해라.
	}

}
