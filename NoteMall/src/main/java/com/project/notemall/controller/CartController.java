package com.project.notemall.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.CartVO;
import com.project.notemall.domain.UserVO;
import com.project.notemall.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")//-Ŭ�������� mapping�� �޼� ����/ �α��� ������ ��� �����ϵ���
@Log4j
public class CartController {
	
	@Inject//-����
	private ShopService shopSvc;
	
	@Inject
	private CommonUtil util;
	
	/**��ٱ��� ���*/
	@RequestMapping("/cartAdd")
	public String addCart(Model m, @RequestParam(defaultValue="0")int pnum,
			@RequestParam(defaultValue="0")int oqty, HttpSession ses) {
		log.info("pnum="+pnum+", oqty="+oqty);
		UserVO loginUser=(UserVO)ses.getAttribute("loginUser");
		System.out.println("pnum="+pnum+", oqty="+oqty);
		System.out.println(loginUser);
		CartVO cvo=new CartVO();
		cvo.setPnum(pnum);
		cvo.setOqty(oqty);
		cvo.setIdx(loginUser.getIdx());//�α����� ȸ���� ��ȣ
		
		int n=shopSvc.addCart(cvo);
		
		return "redirect:cartList";
	}
		
	/**��ٱ��� ����Ʈ*/
	@RequestMapping("/cartList")
	public String listCart(Model m, HttpSession ses) {
		UserVO loginUser=(UserVO) ses.getAttribute("loginUser");
		int midx=loginUser.getIdx();
		
		//Ư��ȸ���� ��ٱ��� ��� ��������
		List<CartVO> cartList=shopSvc.selectCartView(midx);
		
		//��ٱ��� ���� �Ѿװ� �� ����Ʈ ��������
		CartVO cartTotal=shopSvc.getCartTotal(midx);
		
		m.addAttribute("cartList", cartList);
		m.addAttribute("cartSum", cartTotal);
		
		
		return "shop/cartList";
	}
	
	/**��ٱ����� ��ǰ ����*/
	@PostMapping("/cartDel")
	public String deleteCart(Model m, @RequestParam(defaultValue="0")int cartNum) {
		if(cartNum==0) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�.");
		}
		//��ٱ��� ��ȣ�� ��ǰ���� ����
		int n=shopSvc.delCart(cartNum);
		
		return "redirect:cartList";
	}
	
	@PostMapping("/cartEdit")
	public String editCart(Model m, @ModelAttribute("cart") CartVO cart) {
		log.info("cart===="+cart);
		
		//������ ������ �Ѿ�� ���
		if(cart.getOqty()<0) {
			return util.addMsgBack(m, "�߸��� ������ �Է��ϼ̽��ϴ�. (����)");
		}else if(cart.getOqty()==0) {
		//������ 0���� �Ѿ�� ��� --> ����ó��
			this.shopSvc.delCart(cart.getCartNum());
		}else {
		//������ ����� ��� ==> ����ó��
			this.shopSvc.editCart(cart);
		}
		return "redirect:cartList";
	}
}
