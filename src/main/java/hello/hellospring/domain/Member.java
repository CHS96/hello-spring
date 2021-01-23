package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는거, ID는 기본 키
    private Long id;

//    @Column(name = "username") //컬럼명 매칭
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
