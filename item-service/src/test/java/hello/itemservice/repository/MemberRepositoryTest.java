package hello.itemservice.repository;

import hello.itemservice.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void save() {
        //given
        Member member = new Member();
        member.setUserName("userA");

        //when
        memberRepository.memberSave(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findById(member.getId()));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findAll() {

        //given
        Member member1 = new Member();
        member1.setUserName("userB");
        Member member2 = new Member();
        member2.setUserName("userC");

        //when
        memberRepository.memberSave(member1);
        memberRepository.memberSave(member2);

        //then
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void delete() {
        //given
        Member member = new Member();
        member.setUserName("userC");
        memberRepository.memberSave(member);

        //when
        memberRepository.deleteById(member.getId());
        Member userA = memberRepository.findById(member.getId());
        //then
        Assertions.assertThat(userA).isNotInstanceOf(Member.class);
    }
}