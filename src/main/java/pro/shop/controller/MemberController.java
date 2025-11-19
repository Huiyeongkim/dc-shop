package pro.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.shop.member.Member;
import pro.shop.member.MemberRepository;
import pro.shop.member.MemberRequest;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1}/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @Operation(
            summary = "회원 목록 조회",
            description = "public.member 테이블에 저장된 모든 회원을 조회한다."
    )
    @GetMapping
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Operation(
            summary = "회원 등록",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 저장한다."
    )
    @PostMapping
    public Member create(@RequestBody MemberRequest request) {
        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        return memberRepository.save(member);
    }

    @Operation(
            summary = "회원 수정",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 수정한다."
    )
    @PutMapping("/{id}")
    public Member update(@RequestBody MemberRequest request, @PathVariable UUID id) {
        Member member = new Member(
                id,
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        return memberRepository.save(member);
    }

    @Operation(
            summary = "회원 삭제",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 삭제한다."
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        memberRepository.deleteById(UUID.fromString(id));
    }



}
