package com.project.notemall.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.notemall.dao.OrderDao;
import com.project.notemall.dao.UserDao;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.ReceiverVO;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	/*트랜잭션 처리가 필요한 메소드에 @Transactional어노테이션을 붙여준다.
	 * (트랜잭션이란? 여러 작업 단위들이 모두 성공하던지, 아님 하나라도 실패하면
	 *    모두 취소처리되는 것)
	 *    @Transactional 을 주면 해당 메소드나 서비스계층에서 함께 할 작업 단위들 중
	 *    하나라도 실패하면 rollback이 된다. 모두 성공해야 commit됨
	 *    propagation=Propagation.REQUIRED
	 *    : 기존 트랜잭션이 있는 상태면 기 존 것을 따라가고, 없다면 새로운 트랜잭션을
	 *    실행하도록 하는 옵션.
	 * =====transaction-context.xml===============================
	 * <!-- 트랜잭션 빈을 자동으로 등록하도록 설정 -->
		<tx:annotation-driven/>
	
		<!-- transactionManager설정================================================== -->
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<property name="dataSource" ref="jndiDataSource"/>
			<!-- <property name="dataSource" ref="dataSource"/> --><!-- =jndiDataSource가 안될때 -->
		</bean>
	 *============================================================    
	 *
	 *==== WEB-INF/spring/appServlet/servlet-context.xml==========
	 *	<tx:annotation-driven/> <!-- 추가하기! -->
	 *============================================================
	 *    
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String orderInsert(OrderVO ovo) {
		// 주문개요, 주문상품, 수령자 정보를 insert하고
		// 주문번호를 반환할 예정=>트랜잭션 처리 필요
		//1. 주문번호 생성
		String onum=makeOnum();
		System.out.println("onum?:"+onum);
		ovo.setOnum(onum);//주문번호를 ovo에 설정
		System.out.println("ovo?"+ovo);
		
		//2. 주문개요 정보 insert
		int n1=this.orderDao.orderDescInsert(ovo);
		System.out.println(n1);
		
		
		//3. 주문 상품정보 insert (상품은 여러 개)
		List<ProductVO> orderList=ovo.getOrderList();
		if(orderList!=null) {
			for(ProductVO prod:orderList) {
				//order_product테이블에 insert
				prod.setOnum(onum);
				System.out.println(prod);
				int n2=this.orderDao.orderProductInsert(prod);
				System.out.println(n2);
			}
		}
		
		//4. 수령자 정보 insert
		ReceiverVO rcv=ovo.getReceiver();
		System.out.println(rcv);
		rcv.setOnum(onum);
		int n3=this.orderDao.receiverInsert(rcv);
		System.out.println(n3);
		
		
		//5. 사용포인트(opointuse) 금액이 있다면 회원의 마일리지에서 차감
		if(ovo.getOpointuse()>0) {					
			userDao.updateMileageDown(ovo);
		}
		
		//6. 결제방식이 카드라면 마일리지에 구매 포인트 적립
		if(ovo.getPayMethod()==200) {
			userDao.updateMileageUp(ovo);
		}
		
		return onum;
	}
	public String makeOnum() {
		//랜덤하게 알파벳 대문자 3개 + 주문한 "년월일시분초"
		char ch1=(char)(Math.random()*26+'A');
		char ch2=(char)(Math.random()*26+'A');
		char ch3=(char)(Math.random()*26+'A');
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String str=sdf.format(d);
		String onum=""+ch1+ch2+ch3+str;
		return onum;
	}

	@Override
	public List<OrderVO> getOrderDesc(String onum) {
		return this.orderDao.getOrderDesc(onum);
	}

	@Override
	public List<OrderVO> getUserOrderList(int idx) {
		return this.orderDao.getUserOrderList(idx);
	}

}
