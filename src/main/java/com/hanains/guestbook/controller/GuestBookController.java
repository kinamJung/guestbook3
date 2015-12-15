package com.hanains.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanains.guestbook.dao.GuestBookDAO;
import com.hanains.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired 
	private GuestBookDAO guestbookDao;
	
	
	@RequestMapping("/")
	public String index(Model model){
		
		List<GuestBookVo> list = guestbookDao.getList();
		
		model.addAttribute("list", list);
		System.out.println("[info]complete get list");
		return "/WEB-INF/views/index.jsp";
		
	}
	
	@RequestMapping(value="/deleteForm/{no}", method= RequestMethod.GET)
	public String deleteForm(@PathVariable("no") Long no, Model model){
		model.addAttribute("no", no);
		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "-1") Long no,
						 @RequestParam(value = "password", required = true, defaultValue = "") String password)
	{
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
				
		guestbookDao.delete(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo){
		
		guestbookDao.insert(vo);
		
		return "redirect:/";
		
	}
	
	
}