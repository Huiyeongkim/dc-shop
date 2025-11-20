package pro.shop.seller.application.dto;

public record SellerCommand(
        String companyName,
        String representativeName,
        String email,
        String phone,
        String businessNumber,
        String address,
        String status
) {
}
