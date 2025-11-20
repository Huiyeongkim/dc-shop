package pro.shop.member.presentation.dto;

import pro.shop.member.application.dto.MemberCommand;


public record MemberRequest(
        String email,
        String name,
        String password,
        String phone,
        String saltKey,
        String flag
) {

    public MemberCommand toCommand() {
        return new MemberCommand(email, name, password, phone, saltKey, flag);
    }

}
