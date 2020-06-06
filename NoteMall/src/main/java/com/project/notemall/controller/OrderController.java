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
	
	/**�ֹ� ����*/
	@PostMapping("/orderSheet")
	public String orderSheet(Model m,HttpSession ses, @RequestParam("opnum") int[] opnum,
			@RequestParam("oqty") int[] oqty) throws NotUserException {
		log.info("opnum="+opnum[0]+", oqty="+oqty[0]);
		System.out.println("opnum="+opnum[0]+", oqty="+oqty[0]);
		List<ProductVO> orderList=new ArrayList<>();
		//�ֹ��� ��ǰ ������ db���� ������ ArrayList�� �����ϰ� �̸� ���ǿ� ����
		if(opnum!=null && oqty!=null) {
			for(int i=0;i<opnum.length;i++) {
				int pnum=opnum[i];
				ProductVO prod=shopSvc.selectByPnum(pnum);
				//��ǰ�� ����(���� ��������)�� �ֹ��������� ����
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
	
	/** �ֹ� �Ϸ����*/
	@RequestMapping("/orderAdd")
	public String arderAdd(Model m, HttpSession ses, @ModelAttribute("ovo")OrderVO ovo) throws NotUserException {
		log.info("ovo===="+ovo);
		System.out.println("ovo===="+ovo);
		//-���ΰ�ħ�ϸ� �ڲ� �ֹ�ó�� �Ǳ� ������ redirect�� �̵�
		//-default�� forward �̵�
		
		//1. �ֹ��� ��ǰ���� �������� (���ǿ���)
		List<ProductVO> orderList=(List<ProductVO>)ses.getAttribute("orderList");
		System.out.println("orderList:"+orderList);
		
		//2. �ֹ��� ��ǰ������ ������ ������ ovo�� setting
		ovo.setOrderList(orderList);
		log.info("ovo2===="+ovo);
		System.out.println("ovo2===="+ovo);
		log.info("ovo.receiver="+ovo.getReceiver());
		System.out.println("ovo.receiver="+ovo.getReceiver());
		
		//3. ���� ����� ���� ���� ���°� ����
		//	ī����� (200) �� opaystate==> "�����Ϸ�"
		// 	�������Ա� (100) �̸� opaystate===> "�̰���"
		int payMethod=ovo.getPayMethod();
		if(payMethod==200) {//ī�����
			ovo.setOpaystate("�����Ϸ�");
		}else if(payMethod==100) {//������ �Ա�
			ovo.setOpaystate("�̰���");
		}
		
		ovo.setOdeliver("�̹��");
		//4. �ֹ������� DB�� insert
		String onum=this.orderSvc.orderInsert(ovo);
		log.info("onum==="+onum);
		System.out.println("onum==="+onum);
				
		//5. �ֹ���ȣ�� ���ǿ� ����
		ses.setAttribute("onum", onum);
		
		//6. ȸ�������� DB���� �ٽ� ������ ���ǿ� ����
		//		�ֹ��ϸ� ������ ���� ������ �����Ƿ�
		String userid=((UserVO)ses.getAttribute("loginUser")).getUserid();
		
		UserVO user=this.userSvc.getUser(userid);
		int mileage=user.getMileage();
		ses.setAttribute("mileage", mileage);
		
		//7. �ֹ�ó���� �Ϸ�Ǹ� => ��ٱ��Ͽ��� �ֹ���ǰ ���� ó��
		if(orderList!=null) {
			Map<String,Integer> map = new HashMap<>();
			map.put("midx_fk", ovo.getMidx_fk());
			int i=0;
			for(ProductVO prod:orderList) {
				map.put("pnum", prod.getPnum());
				this.shopSvc.delCartOrder(map);
			}
		}
		
		//8. �ֹ� �� ���� �����ִ� �������� �̵�
		//		���ΰ�ħ�� ���� �ֹ��� �߻��� �� �ֱ� ������ forward�� �ƴ϶�
		//		redirect�� �̵��ؾ� ��
		
		
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
			return util.addMsgBack(m,"�߸� ���� ����Դϴ�");
		}
		//�ֹ���ȣ�� �ֹ����� ���� ��������
		List<OrderVO> orderDesc=this.orderSvc.getOrderDesc(onum);
		log.info("orderDesc==="+orderDesc);
		m.addAttribute("orderDesc",orderDesc);
		m.addAttribute("onum",onum);
		
		return "shop/orderDesc";
	}
	
	/**�� �ֹ�����*/
	@RequestMapping("/orderInfo")
	public String orderInfo(Model m,HttpSession ses, 
			@RequestParam(value="idx", defaultValue="0") int idx) {
		log.info("idx==="+idx);

		if(idx==0) {
			return util.addMsgBack(m,"�߸� ���� ����Դϴ�");
		}
		//midx_fk�� �ֹ����� ���� ��������
		List<OrderVO> orderInfo=this.orderSvc.getUserOrderList(idx);
		log.info("orderInfo==="+orderInfo);
		m.addAttribute("orderInfo",orderInfo);
		
		return "shop/orderInfo";
	}
}
