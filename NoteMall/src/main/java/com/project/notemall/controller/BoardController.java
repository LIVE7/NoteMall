package com.project.notemall.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.BoardVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.service.BoardService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/board")
@Controller
public class BoardController {

	@Inject
	private CommonUtil util;
	
	@Inject
	private BoardService boardSvc;
	
	/** 게시판 작성폼*/
	@GetMapping("/input")
	public String boardForm() {
		return "board/boardWrite";
	}
	
	/**게시판 작성*/
	@PostMapping("/input")
	public String boardInsert(Model m, 
			HttpSession ses,
			@RequestParam("mfilename") MultipartFile mfilename,
			@RequestParam("oldfile") String oldfile,
			@RequestParam(defaultValue="")String mode,
			@ModelAttribute("board") BoardVO board) {
		log.info("mode==="+mode);//1.글쓰기 mode ="write", 2.답글쓰기 mode="rewrite"
								//3. 글편집 mode="edit"
		log.info("board==="+board);
		log.info("mfilename=="+mfilename);
		ServletContext app=ses.getServletContext();
		//업로드할 디렉토리의 절대경로 (/Upload)
		String upDir=app.getRealPath("/upload");
		log.info("upDir==="+upDir);
		//파일이 첨부되었다면
		if(!mfilename.isEmpty()) {
			//1. 첨부파일명과 파일크기 알아내기
			String fname=mfilename.getOriginalFilename();//원본 파일명
			long fsize=mfilename.getSize();//파일크기
			UUID uuid=UUID.randomUUID();
			String upfname=uuid.toString()+"_"+fname;
			log.info("fname: "+fname); //고객에게 보여주기 용(원본파일명)
			log.info("upfname: :"+upfname);//DB에 물리적 파일명 저장
			
			board.setFilename(upfname);//물리적 파일명
			board.setOriginFilename(fname);//원본 파일명
			board.setFilesize(fsize);//파일크기 
			
			//2. 업로드 처리 => transferTo() 
			try {
			mfilename.transferTo(new File(upDir,upfname));
			}catch(IOException e) {
				log.error(e.getMessage());
			}
			
		}//if-------
		
		//새로 첨부한 파일이 있다면, 기존에 업로드 했던 파일을 삭제 처리
				if(mfilename!=null) {
					//hidden으로 가지고 있는 기존 파일명을 가져와 삭제 처리
					System.out.println("oldfile="+oldfile);
					if(oldfile!=null || !oldfile.trim().isEmpty()) {
						//삭제처리
						File delFile = new File(upDir+File.separator+oldfile);
						if(delFile.exists()) {
							boolean b=delFile.delete();
							System.out.println("파일 삭제 여부: "+b);
						}
					}
				}
		
		int n=0;
		String str="";
		if(mode.equals("write")) {//부모 글쓰기라면
			n=this.boardSvc.insertBoard(board);
			str="글 작성";
		}else if(mode.equals("rewrite")) {//답변 글쓰기라면
			n=this.boardSvc.rewriteBoard(board);
			str="답글 작성";
		}else if(mode.equals("edit")) {//글수정이라면
			n=this.boardSvc.updateBoard(board);
			str="글 수정";
		}

		String msg=(n>0)? " 완료":" 실패";
		String loc=(n>0)?"list":"javascript:history.back()";
		return util.addMsgLoc(m, str+msg, loc);
	}
	
	/**게시판 목록*/
	@RequestMapping("/list")
	public String boardList(Model m, 
			HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("paging==="+paging);
		//1. 총게시글 수 가져오기
		int totalCount=this.boardSvc.getTotalCount(paging);
		
		//페이징 연산하는 메소드 호출
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);//페이징 블럭 단위 설정
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2==="+paging);
		
		//Map<String,Integer> map=new HashMap<>();
		//2. 게시목록 가져오기
		List<BoardVO> bList=this.boardSvc.selectBoardAll(paging);
		
		log.info("bList===" + bList);
		
		//3. 페이지 네비게이션 문자열 가져오기
		String myctx=req.getContextPath();
		String loc="board/list";
		String navi=paging.getPageNavi(myctx, loc);
		m.addAttribute("navi",navi);
		
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("boardList",bList);
		m.addAttribute("paging",paging);
		return "board/boardList";
	}//---------------------------------
	
	/**게시글 확인*/
	@RequestMapping("/view")
	public String boardView(Model m, @RequestParam(defaultValue="0")int idx) {
		if(idx==0) {
			return "redirect:list";
		}
		this.boardSvc.updateReadnum(idx);//조회수 증가
		
		BoardVO board=this.boardSvc.selectBoardByIdx(idx);//해당 글 가져오기
		
		m.addAttribute("board",board);
		
		return "board/boardView";
	}//---------------------------------
	
	/**답변글 달기 폼 보여주기*/
	@PostMapping("/rewrite")
	public String rewriteForm(Model m,@RequestParam(defaultValue="0") int idx,
			@RequestParam(defaultValue="")String subject) {
		if(idx==0||subject.isEmpty()) {
			return "redirect:list";
		}
		m.addAttribute("idx",idx);
		m.addAttribute("subject",subject);
		return "board/boardRewrite";		
	}
	
	/**삭제, 수정 패스워드 입력*/
	@PostMapping("/boardPasswd")
	public String boardPasswd(Model m,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(mode);
		if(idx==0||mode.isEmpty()) {
			return util.addMsgBack(m, "잘못 들어온 경로입니다.");
		}
		int md = Integer.parseInt(mode);
		String title="";
		if (md==1) {
			title="글 편집";
		}else if (md==2) {
			title="글 삭제";
		}
		m.addAttribute("idx",idx);
		m.addAttribute("mode",mode);
		m.addAttribute("title",title);
		return "board/boardPwd";
	}
	
	/**게시 글 삭제*/
	@PostMapping("/deleteBoard")
	public String deleteBoard(Model m,HttpSession ses,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="pwd", defaultValue="")String pwd, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(pwd);
		System.out.println(mode);
		if(idx==0||pwd.isEmpty()||mode.isEmpty()) {
			return util.addMsgBack(m, "잘못 들어온 경로입니다.");
		}
		BoardVO dbBoard=this.boardSvc.selectBoardByIdx(idx);//해당 글 가져오기
		if(!pwd.equals(dbBoard.getPwd())) {//-비밀번호가 일치하지 않다면 (항상 부정적인것부터 먼저 처리)
			return util.addMsgBack(m, "비밀번호가 일치하지 않습니다.");
		}
		long fsize=dbBoard.getFilesize();
		System.out.println(fsize);
		if(fsize>0) {//첨부파일이 있는 경우
			String filename=dbBoard.getFilename();
			System.out.println(filename);
			ServletContext app=ses.getServletContext();
			String upDir=app.getRealPath("/upload");//-upload
			System.out.println(upDir);
			File delFile=new File(upDir+File.separator+filename);//-파일의 절대 경로
			System.out.println(delFile.getAbsolutePath());
			if(delFile.exists()) {//-파일이 존재하는가?
				 boolean b=delFile.delete();//파일 삭제 처리
				 System.out.println("삭제 처리 여부: "+b);
				}
		}//if--------------------
		
		int n = this.boardSvc.deleteBoard(idx);
		String str=(n>0)? "삭제를 완료했습니다.":"삭제를 실패했습니다.";
		String loc=(n>0)? "list":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		
		return "message";
	}
	
	/** 게시 글 수정*/
	@PostMapping("/updateBoard")
	public String updateBoard(Model m,HttpSession ses,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="pwd", defaultValue="")String pwd, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(pwd);
		System.out.println(mode);
		if(idx==0||pwd.isEmpty()||mode.isEmpty()) {
			return util.addMsgBack(m, "잘못 들어온 경로입니다.");
		}
		BoardVO dbBoard=this.boardSvc.selectBoardByIdx(idx);//해당 글 가져오기
		if(!pwd.equals(dbBoard.getPwd())) {//-비밀번호가 일치하지 않다면 (항상 부정적인것부터 먼저 처리)
			return util.addMsgBack(m, "비밀번호가 일치하지 않습니다.");
		}
		
		BoardVO board=this.boardSvc.selectBoardByIdx(idx);//해당 글 가져오기
		
		m.addAttribute("board",board);
		
		return "board/boardEdit";
	}
	
	
}

