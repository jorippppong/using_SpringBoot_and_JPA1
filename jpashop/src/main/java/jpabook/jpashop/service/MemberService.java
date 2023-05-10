package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //spring이 제공하는 annotation 사용하자
@RequiredArgsConstructor
public class MemberService {

    //0-1. injection 방법 (필드) : 수정 불가능 하다는 단점이 있다.
    /*
    @Autowired
    private MemberRepository memberRepository;
    */

    //0-2. injection 방법 (setter 사용) : 누가 바꿀 수 있다.
    /*
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */

    //0-3. injection 방법 (생성자 사용) : 중간에 수정 불가능, test case 작성시 직접 주입 해야한다.
    private final MemberRepository memberRepository;  //final : compile 시점에 확인 가능

    /*
    //@Autowired : 생성자가 하나인 경우에는 Spring이 알아서 Autowired 해준다.
    @RequiredArgsConstructor로 인해 생략된 코드
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //1. 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //1. 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //2. 회원 조회
    //2-1. 회원 전체 조회
    //읽기에는 "readOnly = true"로 하면 JPA 조회하는 곳에서 성능을 최적화 (읽기전용 transaction)
    //쓰기에 "readOnly = true"하면 값 변경이 안되기 때문에 쓰기에서는 사용 ㄴㄴ
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //2-2. 회원 한 건만 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
