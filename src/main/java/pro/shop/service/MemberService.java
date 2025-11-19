package pro.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.shop.common.ResponseEntity;
import pro.shop.member.Member;
import pro.shop.member.MemberRepository;
import pro.shop.member.MemberRequest;
import pro.shop.member.MemberResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<List<MemberResponse>> findAll() {
        return new ResponseEntity<>(
                            HttpStatus.OK.value(),
                            memberRepository.findAll().stream()
                                .map(MemberResponse::new)
                                .collect(Collectors.toList()),
                            memberRepository.count()
                );
    }

    @Transactional
    public ResponseEntity<MemberResponse> create(MemberRequest request) {
        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        memberRepository.save(member);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), new MemberResponse(member), 1);
    }

    @Transactional
    public ResponseEntity<MemberResponse> update(UUID id, MemberRequest request) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow();
        findMember.update(request.email(), request.name(), request.password(), request.phone(), request.saltKey(), request.flag());
        return new ResponseEntity<>(HttpStatus.OK.value(), new MemberResponse(findMember), 1);
    }

    @Transactional
    public ResponseEntity<?> delete(UUID id) {
        memberRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK.value(), null, 0);
    }
}
