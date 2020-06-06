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
@RequestMapping("/user")//-클래스에도 mapping을 달수 있음/ 로그인 유저만 사용 가능하도록
@Log4j
public class CartController {
	
	@Inject//-주입
	private ShopService shopSvc;
	
	@Inject
	private CommonUtil util;
	
	/**장바구니 담기*/
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
		cvo.setIdx(loginUser.getIdx());//로그인한 회원의 번호
		
		int n=shopSvc.addCart(cvo);
		
		return "redirect:cartList";
	}
		
	/**장바구니 리스트*/
	@RequestMapping("/cartList")
	public String listCart(Model m, HttpSession ses) {
		UserVO loginUser=(UserVO) ses.getAttribute("loginUser");
		int midx=loginUser.getIdx();
		
		//특정회원의 장바구니 목록 가져오기
		List<CartVO> cartList=shopSvc.selectCartView(midx);
		
		//장바구니 누적 총액과 총 포인트 가져오기
		CartVO cartTotal=shopSvc.getCartTotal(midx);
		
		m.addAttribute("cartList", cartList);
		m.addAttribute("cartSum", cartTotal);
		
		
		return "shop/cartList";
	}
	
	/**장바구니의 상품 삭제*/
	@PostMapping("/cartDel")
	public String deleteCart(Model m, @RequestParam(defaultValue="0")int cartNum) {
		if(cartNum==0) {
			return util.addMsgBack(m, "잘못 들어온 경로입니다.");
		}
		//장바구니 번호로 상품정보 삭제
		int n=shopSvc.delCart(cartNum);
		
		return "redirect:cartList";
	}
	
	@PostMapping("/cartEdit")
	public String editCart(Model m, @ModelAttribute("cart") CartVO cart) {
		log.info("cart===="+cart);
		
		//수량이 음수로 넘어올 경우
		if(cart.getOqty()<0) {
			return util.addMsgBack(m, "잘못된 수량을 입력하셨습니다. (음수)");
		}else if(cart.getOqty()==0) {
		//수량이 0으로 넘어올 경우 --> 삭제처리
			this.shopSvc.delCart(cart.getCartNum());
		}else {
		//수량이 양수일 경우 ==> 수정처리
			this.shopSvc.editCart(cart);
		}
		return "redirect:cartList";
	}
}
