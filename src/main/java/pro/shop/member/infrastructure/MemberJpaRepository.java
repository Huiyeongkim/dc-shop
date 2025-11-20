package pro.shop.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.shop.member.domain.Member;

import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<Member, UUID> {
}
