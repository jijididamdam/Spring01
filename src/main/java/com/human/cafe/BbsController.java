package com.human.cafe;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.human.cusService.IF_cusService;
import com.human.service.IF_boardService;
import com.human.util.FileDataUtil;
import com.human.vo.BoardVO;
import com.human.vo.PageVO;
import com.human.vo.cusVO;

@Controller
public class BbsController {
	@Inject
	private IF_boardService bsrv;
	@Inject
	private IF_cusService csrv;
	@Inject
	private FileDataUtil fileDataUtil;
	
	/*
	@RequestMapping(value = "/viewForm", method = RequestMethod.GET)
	public String test1(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		//model.addAttribute("sss", "hi" );
		
		return "wrForm";
	}
	*/
	@RequestMapping(value = "/wrWrite", method = RequestMethod.GET)
	public String wrWrite(Locale locale, Model model)  {
		return "bbs/wrForm";
	}
	@RequestMapping(value = "/wrAction", method = RequestMethod.POST)
	public String WrAction(Locale locale, Model model, BoardVO bvo, MultipartFile[] file) throws Exception {
		//객체로 받을 때는 파라미터이름과 객체의 변수의 이름이 일치하고 getter, setter가 있어야 한다.  > 자동매핑
		System.out.println(bvo.getName()+"--디버깅용도");
		//filedatautil 객체의 fileupload메서드를 호출 ... 매개변수로 file을 넘긴다   <지정한 폴더로 첨부파일 복사>
		String[] fileNames = fileDataUtil.fileUpload(file);
		//넘겨 받은 파일명을 boardVO files 변수에 저장한다.
		bvo.setFiles(fileNames);
		// DB작업
		bsrv.insertOne(bvo);
		/*System.out.println(bvo.getPass()+"--디버깅용도");
		System.out.println(bvo.getEmail()+"--디버깅용도");
		System.out.println(bvo.getTitle()+"--디버깅용도");
		System.out.println(bvo.getContent()+"--디버깅용도");*/
		//System.out.println(bvo.getEmail());
		//System.out.println(file.getOriginalFilename() + "---첨부파일 디버깅");

		//String[] fileNames = fileDataUtil.fileUpload(file);
		//System.out.println(fileNames[0]+" 이 디비에 저장될 파일명 "+fileNames[1]);
		//넘겨 받은 파일명을 boardVO files 변수에 저장한다.
		// DB작업
		//bsrv.insertOne(bvo);
		
		//return "wrForm";
		return "home";
		//return "redirect:/bbsList";
	}
	
	@RequestMapping(value = "/bbsList", method = RequestMethod.GET)
	public String WrAction1(Locale locale, Model model, @ModelAttribute("pageVO") PageVO pageVO) throws Exception {
		//객체로 받을 때는 파라미터이름과 객체의 변수의 이름이 일치하고 getter, setter가 있어야 한다.  > 자동매핑
		
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		System.out.println("현재 페이지 정보 : "+pageVO.getPage()  );
		
		// page 계산하기 위해서 첫번째 페이지정보(클라이언트가 넘겨준다), 두번째는 전체페이지수, 세번째는 페이지당 그리고 페이지그룹당 수
		
		int totalpageCnt = bsrv.countBoard(); //select count(*) from bb;
		System.out.println(totalpageCnt+"건 등록됨");
		pageVO.setTotalCount(totalpageCnt);
		
		List<BoardVO> list = bsrv.selectAll(pageVO);
		System.out.println(list.size()+"----디버깅 용");
		model.addAttribute("blist",list);
		model.addAttribute("pageVO",pageVO); // 페이지 객체를 view에게 넘김
		
		return "bbs/bbsList";
	}
	
	@RequestMapping(value = "/cusSave", method = RequestMethod.GET)
	public String WrAction2(Locale locale, Model model)  {
		
		
		return "cus";
	}
	
	@RequestMapping(value = "/putCus", method = RequestMethod.GET)
	public String WrAction3(Locale locale, Model model, cusVO cvo) throws Exception {
			
		csrv.put(cvo);
		return "home";
	}
	
	@RequestMapping(value = "/bbsView", method = RequestMethod.GET)
	public String BBsView(Locale locale, Model model,@RequestParam("vno") String vno) throws Exception  {
		//DB 작업
		BoardVO tempvo = bsrv.selectOne(vno);
		
		model.addAttribute("boardVO", tempvo);
		
		List<String> attachList = bsrv.selectAttach(vno);
		model.addAttribute("attachList",attachList);
		
		return "bbs/bbsview";
	}
	
	@RequestMapping(value = "/bbsMod", method = RequestMethod.GET)
	public String BBsMod(Locale locale, Model model,@RequestParam("vno") String vno) throws Exception  {
		BoardVO tempvo = bsrv.selectOne(vno);
		model.addAttribute("boardVO", tempvo);
		return "bbs/bbsmod";
	}
	
	@RequestMapping(value = "/bbsModAction", method = RequestMethod.POST)
	public String BBsMod(Locale locale, Model model,BoardVO bvo) throws Exception  {
		bsrv.updateBoard(bvo);
		return "redirect:/bbsView?vno="+bvo.getNum();
	}
}
