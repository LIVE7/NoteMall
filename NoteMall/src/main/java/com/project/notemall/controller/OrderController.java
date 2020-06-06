package com.project.notemall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.NotUserException;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.UserVO;
import com.project.notemall.service.OrderService;
import com.project.notemall.service.ShopService;
import com.project.notemall.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class OrderController {
	
	@Autowired
	private ShopService shopSvc;
	
	@Autowired
	private OrderService orderSvc;
	
	@Autowired
	private UserService userSvc;
	
	@Inject
	private CommonUtil util;
	
	/**주문 과정*/
	@PostMapping("/orderSheet")
	public String orderSheet(Model m,HttpSession ses, @RequestParam("opnum") int[] opnum,
			@RequestParam("oqty") int[] oqty) throws NotUserException {
		log.info("opnum="+opnum[0]+", oqty="+oqty[0]);
		System.out.println("opnum="+opnum[0]+", oqty="+oqty[0]);
		List<ProductVO> orderList=new ArrayList<>();
		//주문한 상품 정보를 db에서 가져와 ArrayList에 저장하고 이를 세션에 저장
		if(opnum!=null && oqty!=null) {
			for(int i=0;i<opnum.length;i++) {
				int pnum=opnum[i];
				ProductVO prod=shopSvc.selectByPnum(pnum);
				//상품의 수량(원래 보유수량)을 주문수량으로 변경
				prod.setPqty(oqty[i]);
				orderList.add(prod);
				System.out.println("orderList:"+orderList);
				System.out.println("prod:"+prod);
			}
		}
		
		String userid=((UserVO)ses.getAttribute("loginUser")).getUserid();
		
		UserVO user=this.userSvc.getUser(userid);
		int mileage=user.getMileage();
		ses.setAttribute("mileage", mileage);
		
		m.addAttribute("orderList",orderList);
		ses.setAttribute("orderList", orderList);
		
		return "shop/orderSheet";
	}
	
	/** 주문 완료과정*/
	@RequestMapping("/orderAdd")
	public String arderAdd(Model m, HttpSession ses, @ModelAttribute("ovo")OrderVO ovo) throws NotUserException {
		log.info("ovo===="+ovo);
		System.out.println("ovo===="+ovo);
		//-새로고침하면 자꾸 주문처리 되기 때문에 redirect로 이동
		//-default는 forward 이동
		
		//1. 주문한 상품정보 가져오기 (세션에서)
		List<ProductVO> orderList=(List<ProductVO>)ses.getAttribute("orderList");
		System.out.println("orderList:"+orderList);
		
		//2. 주문한 상품정보와 수령자 정보를 ovo에 setting
		ovo.setOrderList(orderList);
		log.info("ovo2===="+ovo);
		System.out.println("ovo2===="+ovo);
		log.info("ovo.receiver="+ovo.getReceiver());
		System.out.println("ovo.receiver="+ovo.getReceiver());
		
		//3. 결제 방법에 따라 결제 상태값 설정
		//	카드결제 (200) 면 opaystate==> "결제완료"
		// 	무통장입금 (100) 이면 opaystate===> "미결제"
		int payMethod=ovo.getPayMethod();
		if(payMethod==200) {//카드결제
			ovo.setOpaystate("결제완료");
		}else if(payMethod==100) {//무통장 입금
			ovo.setOpaystate("미결제");
		}
		
		ovo.setOdeliver("미배송");
		//4. 주문정보를 DB에 insert
		String onum=this.orderSvc.orderInsert(ovo);
		log.info("onum==="+onum);
		System.out.println("onum==="+onum);
				
		//5. 주문번호를 세션에 저장
		ses.setAttribute("onum", onum);
		
		//6. 회원정보를 DB에서 다시 가져와 세션에 저장
		//		주문하면 적립금 등의 변동이 있으므로
		String userid=((UserVO)ses.getAttribute("loginUser")).getUserid();
		
		UserVO user=this.userSvc.getUser(userid);
		int mileage=user.getMileage();
		ses.setAttribute("mileage", mileage);
		
		//7. 주문처리가 완료되면 => 장바구니에서 주문상품 삭제 처리
		if(orderList!=null) {
			Map<String,Integer> map = new HashMap<>();
			map.put("midx_fk", ovo.getMidx_fk());
			int i=0;
			for(ProductVO prod:orderList) {
				map.put("pnum", prod.getPnum());
				this.shopSvc.delCartOrder(map);
			}
		}
		
		//8. 주문 상세 내역 보여주는 페이지로 이동
		//		새로고침시 이중 주문이 발생할 수 있기 때문에 forward가 아니라
		//		redirect로 이동해야 함
		
		
		return "redirect:orderDetail?onum="+onum;
	}
	
	@RequestMapping("/orderDetail")
	public String orderDetail(Model m,HttpSession ses, 
			@RequestParam(defaultValue="") String onum) {
		log.info("onum==="+onum);
		if(onum.isEmpty()) {
			onum=(String)ses.getAttribute("onum");
		}
		if(onum==null) {
			return util.addMsgBack(m,"잘못 들어온 경로입니다");
		}
		//주문번호로 주문내역 정보 가져오기
		List<OrderVO> orderDesc=this.orderSvc.getOrderDesc(onum);
		log.info("orderDesc==="+orderDesc);
		m.addAttribute("orderDesc",orderDesc);
		m.addAttribute("onum",onum);
		
		return "shop/orderDesc";
	}
	
	/**상세 주문내역*/
	@RequestMapping("/orderInfo")
	public String orderInfo(Model m,HttpSession ses, 
			@RequestParam(value="idx", defaultValue="0") int idx) {
		log.info("idx==="+idx);

		if(idx==0) {
			return util.addMsgBack(m,"잘못 들어온 경로입니다");
		}
		//midx_fk로 주문내역 정보 가져오기
		List<OrderVO> orderInfo=this.orderSvc.getUserOrderList(idx);
		log.info("orderInfo==="+orderInfo);
		m.addAttribute("orderInfo",orderInfo);
		
		return "shop/orderInfo";
	}
}
