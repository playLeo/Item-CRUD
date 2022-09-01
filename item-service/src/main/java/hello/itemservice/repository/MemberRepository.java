package hello.itemservice.repository;

import hello.itemservice.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext EntityManager em;

    public void memberSave(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

    public void update(Long id, Member member) {
        Member user = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setUserName(member.getUserName());
        user.setOrderList(member.getOrderList());
    }
}
