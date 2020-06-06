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
	
	/** �Խ��� �ۼ���*/
	@GetMapping("/input")
	public String boardForm() {
		return "board/boardWrite";
	}
	
	/**�Խ��� �ۼ�*/
	@PostMapping("/input")
	public String boardInsert(Model m, 
			HttpSession ses,
			@RequestParam("mfilename") MultipartFile mfilename,
			@RequestParam("oldfile") String oldfile,
			@RequestParam(defaultValue="")String mode,
			@ModelAttribute("board") BoardVO board) {
		log.info("mode==="+mode);//1.�۾��� mode ="write", 2.��۾��� mode="rewrite"
								//3. ������ mode="edit"
		log.info("board==="+board);
		log.info("mfilename=="+mfilename);
		ServletContext app=ses.getServletContext();
		//���ε��� ���丮�� ������ (/Upload)
		String upDir=app.getRealPath("/upload");
		log.info("upDir==="+upDir);
		//������ ÷�εǾ��ٸ�
		if(!mfilename.isEmpty()) {
			//1. ÷�����ϸ�� ����ũ�� �˾Ƴ���
			String fname=mfilename.getOriginalFilename();//���� ���ϸ�
			long fsize=mfilename.getSize();//����ũ��
			UUID uuid=UUID.randomUUID();
			String upfname=uuid.toString()+"_"+fname;
			log.info("fname: "+fname); //������ �����ֱ� ��(�������ϸ�)
			log.info("upfname: :"+upfname);//DB�� ������ ���ϸ� ����
			
			board.setFilename(upfname);//������ ���ϸ�
			board.setOriginFilename(fname);//���� ���ϸ�
			board.setFilesize(fsize);//����ũ�� 
			
			//2. ���ε� ó�� => transferTo() 
			try {
			mfilename.transferTo(new File(upDir,upfname));
			}catch(IOException e) {
				log.error(e.getMessage());
			}
			
		}//if-------
		
		//���� ÷���� ������ �ִٸ�, ������ ���ε� �ߴ� ������ ���� ó��
				if(mfilename!=null) {
					//hidden���� ������ �ִ� ���� ���ϸ��� ������ ���� ó��
					System.out.println("oldfile="+oldfile);
					if(oldfile!=null || !oldfile.trim().isEmpty()) {
						//����ó��
						File delFile = new File(upDir+File.separator+oldfile);
						if(delFile.exists()) {
							boolean b=delFile.delete();
							System.out.println("���� ���� ����: "+b);
						}
					}
				}
		
		int n=0;
		String str="";
		if(mode.equals("write")) {//�θ� �۾�����
			n=this.boardSvc.insertBoard(board);
			str="�� �ۼ�";
		}else if(mode.equals("rewrite")) {//�亯 �۾�����
			n=this.boardSvc.rewriteBoard(board);
			str="��� �ۼ�";
		}else if(mode.equals("edit")) {//�ۼ����̶��
			n=this.boardSvc.updateBoard(board);
			str="�� ����";
		}

		String msg=(n>0)? " �Ϸ�":" ����";
		String loc=(n>0)?"list":"javascript:history.back()";
		return util.addMsgLoc(m, str+msg, loc);
	}
	
	/**�Խ��� ���*/
	@RequestMapping("/list")
	public String boardList(Model m, 
			HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("paging==="+paging);
		//1. �ѰԽñ� �� ��������
		int totalCount=this.boardSvc.getTotalCount(paging);
		
		//����¡ �����ϴ� �޼ҵ� ȣ��
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);//����¡ �� ���� ����
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2==="+paging);
		
		//Map<String,Integer> map=new HashMap<>();
		//2. �Խø�� ��������
		List<BoardVO> bList=this.boardSvc.selectBoardAll(paging);
		
		log.info("bList===" + bList);
		
		//3. ������ �׺���̼� ���ڿ� ��������
		String myctx=req.getContextPath();
		String loc="board/list";
		String navi=paging.getPageNavi(myctx, loc);
		m.addAttribute("navi",navi);
		
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("boardList",bList);
		m.addAttribute("paging",paging);
		return "board/boardList";
	}//---------------------------------
	
	/**�Խñ� Ȯ��*/
	@RequestMapping("/view")
	public String boardView(Model m, @RequestParam(defaultValue="0")int idx) {
		if(idx==0) {
			return "redirect:list";
		}
		this.boardSvc.updateReadnum(idx);//��ȸ�� ����
		
		BoardVO board=this.boardSvc.selectBoardByIdx(idx);//�ش� �� ��������
		
		m.addAttribute("board",board);
		
		return "board/boardView";
	}//---------------------------------
	
	/**�亯�� �ޱ� �� �����ֱ�*/
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
	
	/**����, ���� �н����� �Է�*/
	@PostMapping("/boardPasswd")
	public String boardPasswd(Model m,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(mode);
		if(idx==0||mode.isEmpty()) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�.");
		}
		int md = Integer.parseInt(mode);
		String title="";
		if (md==1) {
			title="�� ����";
		}else if (md==2) {
			title="�� ����";
		}
		m.addAttribute("idx",idx);
		m.addAttribute("mode",mode);
		m.addAttribute("title",title);
		return "board/boardPwd";
	}
	
	/**�Խ� �� ����*/
	@PostMapping("/deleteBoard")
	public String deleteBoard(Model m,HttpSession ses,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="pwd", defaultValue="")String pwd, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(pwd);
		System.out.println(mode);
		if(idx==0||pwd.isEmpty()||mode.isEmpty()) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�.");
		}
		BoardVO dbBoard=this.boardSvc.selectBoardByIdx(idx);//�ش� �� ��������
		if(!pwd.equals(dbBoard.getPwd())) {//-��й�ȣ�� ��ġ���� �ʴٸ� (�׻� �������ΰͺ��� ���� ó��)
			return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		long fsize=dbBoard.getFilesize();
		System.out.println(fsize);
		if(fsize>0) {//÷�������� �ִ� ���
			String filename=dbBoard.getFilename();
			System.out.println(filename);
			ServletContext app=ses.getServletContext();
			String upDir=app.getRealPath("/upload");//-upload
			System.out.println(upDir);
			File delFile=new File(upDir+File.separator+filename);//-������ ���� ���
			System.out.println(delFile.getAbsolutePath());
			if(delFile.exists()) {//-������ �����ϴ°�?
				 boolean b=delFile.delete();//���� ���� ó��
				 System.out.println("���� ó�� ����: "+b);
				}
		}//if--------------------
		
		int n = this.boardSvc.deleteBoard(idx);
		String str=(n>0)? "������ �Ϸ��߽��ϴ�.":"������ �����߽��ϴ�.";
		String loc=(n>0)? "list":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		
		return "message";
	}
	
	/** �Խ� �� ����*/
	@PostMapping("/updateBoard")
	public String updateBoard(Model m,HttpSession ses,@RequestParam(value="idx", defaultValue="0")int idx, @RequestParam(value="pwd", defaultValue="")String pwd, @RequestParam(value="mode", defaultValue="")String mode) {
		System.out.println(idx);
		System.out.println(pwd);
		System.out.println(mode);
		if(idx==0||pwd.isEmpty()||mode.isEmpty()) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�.");
		}
		BoardVO dbBoard=this.boardSvc.selectBoardByIdx(idx);//�ش� �� ��������
		if(!pwd.equals(dbBoard.getPwd())) {//-��й�ȣ�� ��ġ���� �ʴٸ� (�׻� �������ΰͺ��� ���� ó��)
			return util.addMsgBack(m, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		BoardVO board=this.boardSvc.selectBoardByIdx(idx);//�ش� �� ��������
		
		m.addAttribute("board",board);
		
		return "board/boardEdit";
	}
	
	
}

