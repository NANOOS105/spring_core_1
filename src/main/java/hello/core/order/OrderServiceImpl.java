package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
	
	//의존성 주입의 형태
	//인터페이스 타입으로 변수를 선언하고 구체적인 구현 클래스의 인스턴스로 초기화해서 코드의 유연성과 테스트의 용이성을 높임
	private final MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;
	
	//인터페이스에만 의존할 수 있게끔 수정 
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		super();
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	
	
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		
		//단일체계 원칙이 잘 설계됨 
		Member member  = memberRepository.findById(memberId);//회원정보조회
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId,itemName,itemPrice,discountPrice);
	}




}
