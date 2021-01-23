package hello.hellospring.service;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

//컴포넌트 스캔말고 직접 자바 코드로 스프링에 등록
//다만 Controller는 안됨
@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    //이렇게 직접 스프링 빈을 통해 수정하면 장점
    //지금 memberRepository가 MemoryMemberRepository로 돼어있음. 인터페이스를 통한
    //후에 어떤 DB를 쓰랴고 DBmemberRepository 바꿀때 코드 수정없이 여기서 return new DBmemberRepository로 바꿔주기만 하면 됨
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
