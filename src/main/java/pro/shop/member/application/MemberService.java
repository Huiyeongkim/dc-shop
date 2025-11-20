package pro.shop.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.shop.common.ResponseEntity;
import pro.shop.member.domain.Member;
import pro.shop.member.domain.MemberRepository;
import pro.shop.member.application.dto.MemberCommand;
import pro.shop.member.application.dto.MemberInfo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<List<MemberInfo>> findAll(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        List<MemberInfo> members = page.stream().map(MemberInfo::from).toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), members, page.getTotalElements());
    }

    @Transactional
    public ResponseEntity<MemberInfo> create(MemberCommand request) {
        Member member = Member.create(request.email(), request.name(), request.password(), request.phone(), request.saltKey(), request.flag());
        memberRepository.save(member);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), MemberInfo.from(member), 1);
    }

    @Transactional
    public ResponseEntity<MemberInfo> update(UUID id, MemberCommand request) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow();
        findMember.updateInformation(request.email(), request.name(), request.password(), request.phone(), request.saltKey(), request.flag());
        return new ResponseEntity<>(HttpStatus.OK.value(), MemberInfo.from(findMember), 1);
    }

    @Transactional
    public ResponseEntity<?> delete(UUID id) {
        memberRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK.value(), null, 0);
    }
}
