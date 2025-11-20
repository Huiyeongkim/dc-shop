package pro.shop.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pro.shop.common.ResponseEntity;
import pro.shop.member.application.dto.MemberInfo;
import pro.shop.member.presentation.dto.MemberRequest;
import pro.shop.member.application.MemberService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.v1}/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "회원 목록 조회",
            description = "public.member 테이블에 저장된 모든 회원을 조회한다."
    )
    @GetMapping
    public ResponseEntity<List<MemberInfo>> findAll(Pageable pageable) {
        return memberService.findAll(pageable);
    }

    @Operation(
            summary = "회원 등록",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 저장한다."
    )
    @PostMapping
    public ResponseEntity<MemberInfo> create(@RequestBody MemberRequest request) {
        return memberService.create(request.toCommand());
    }

    @Operation(
            summary = "회원 수정",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 수정한다."
    )
    @PutMapping("/{id}")
    public ResponseEntity<MemberInfo> update(@RequestBody MemberRequest request, @PathVariable UUID id) {
        return memberService.update(id, request.toCommand());
    }

    @Operation(
            summary = "회원 삭제",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 삭제한다."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return memberService.delete(id);
    }



}
