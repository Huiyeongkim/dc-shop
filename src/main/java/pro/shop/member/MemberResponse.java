package pro.shop.member;


public record MemberResponse(
        String email,
        String name,
        String phone,
        String flag
) {

    public MemberResponse(Member member) {
        this(member.getEmail(), member.getName(), member.getPhone(), member.getFlag());
    }
}
