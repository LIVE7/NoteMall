package com.project.notemall.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.SumVO;
import com.project.notemall.domain.UserVO;
import com.project.notemall.service.AdminService;
import com.project.notemall.service.OrderService;
import com.project.notemall.service.UserService;

import lombok.extern.log4j.Log4j;

@RequestMapping("/admin")
@Controller
@Log4j
public class AdminController {
	
	@Inject
	private AdminService adminSvc;
	
	@Inject
	private OrderService orderSvc;
	
	@Inject 
	private UserService userSvc;
	
	@Inject
	private CommonUtil util;

	/** ��ڷ� ����*/
	@RequestMapping("/mgr")
	public String showAdminMenu() {
		
		return "/admin/adminMenu";
	}
	/**���� �ֹ� ���*/
	@RequestMapping("/orderAdmin")
	public String orderListByMonth(Model m,
			@RequestParam(defaultValue="") String yy,
			@RequestParam(defaultValue="") String mm) {
		String month="";
		if(yy.isEmpty()||mm.isEmpty()) {
			//���� �⵵ �� ���ϱ�
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			month=sdf.format(d);
		}else {
			int m2=Integer.parseInt(mm);
			mm=(m2<10)?"0"+mm:mm;
			
			month=yy+"-"+mm;
		}
		//�ش�⵵ �ش�� �ֹ���� ��������
		List<OrderVO> olist=this.adminSvc.getOrderListByMonth(month);

		m.addAttribute("month",month);
		m.addAttribute("olist",olist);
		return "/admin/orderListByMonth";
	}
	
	/** �ֹ� ����*/
	@RequestMapping("/orderAdminMgr")
	public String orderAdminMgr(Model m,
			@RequestParam(defaultValue="") String onum,
			@RequestParam(defaultValue="0") int midx_fk) {
		
		if(onum.isEmpty()||midx_fk==0) {
			return util.addMsgBack(m,"�߸� ���� ����Դϴ�");
		}
		//1. �ֹ���ȣ�� �ֹ����� ��������
		List<OrderVO> olist=this.orderSvc.getOrderDesc(onum);
		
		//2. ȸ����ȣ�� �ֹ��� ���� ��������
		UserVO user=this.userSvc.getUserByIdx(midx_fk);
		
		m.addAttribute("orderList",olist);
		m.addAttribute("orderUser",user);
		
		return "/admin/orderAdminMgr";
		
	}
	
	/** �ֹ� ����*/
	@RequestMapping("/orderAdminMgrEnd")
	public String orderAdminManageEnd(Model m,
			@ModelAttribute("ovo") OrderVO ovo) {
		log.info("ovo=="+ovo);
		//onum, midx_fk, ( opaystate | odeliver  )
		if(ovo.getOnum()==null||ovo.getMidx_fk()==0) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�");
		}
		String colName="";// �÷����� ���� ���� (opaystate �Ǵ� odeliver)
		if(ovo.getOpaystate()==null) {
			colName="odeliver";
			this.adminSvc.manageOrder(ovo.getOnum(), colName, ovo.getOdeliver());
			//update orderDesc set odeliver='�����' where onum='aaa2019...'
		}else if(ovo.getOdeliver()==null) {
			colName="opaystate";
			this.adminSvc.manageOrder(ovo.getOnum(), colName, ovo.getOpaystate());
			//update orderDesc set opaystate='�����Ϸ�' where onum='aaa2019...' 
		}
		
		
		return "redirect:orderAdminMgr?onum="+ovo.getOnum()
							+"&midx_fk="+ovo.getMidx_fk();
	}
	
	/** �������*/
	@RequestMapping("/statistic")
	public String getSumYear(Model m, 
			@RequestParam(defaultValue="") String oyear) {
		log.info("oyear="+oyear);
		if(oyear.isEmpty()) {
			Calendar cal=Calendar.getInstance();
			int year=cal.get(Calendar.YEAR);
			oyear=String.valueOf(year);
		}
		
		//1. ������ �������
		List<SumVO> oyearSum=this.adminSvc.getSumYear();
		
		//2. �ش� �⵵ ���� �������
		List<SumVO> omonthSum=this.adminSvc.getSumMonth(oyear);
		
		//3.
		String chartStr="";
		for(SumVO svo:omonthSum) {
			chartStr+="['"+svo.getOmonth()+"', "+(svo.getOsum()/10000)+"], ";
		}
		log.info(chartStr);
		m.addAttribute("chartData", chartStr);
		m.addAttribute("oyear",oyear);
		m.addAttribute("oyearSum",oyearSum);
		m.addAttribute("omonthSum",omonthSum);
		
		return "/admin/totalSum";
	}
	

}












