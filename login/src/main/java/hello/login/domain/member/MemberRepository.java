package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepository {

    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        Member savedMember = store.put(sequence, member);
        return savedMember;
    }

    public Member findById(Long id) {
        Member member = store.get(id);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> members = findAll();
//        for (Member member : members) {
//            if (member.getLoginId().equals(loginId)) {
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }

}
