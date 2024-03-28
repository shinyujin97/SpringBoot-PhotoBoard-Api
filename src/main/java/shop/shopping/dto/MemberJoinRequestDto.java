package shop.shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
회원가입 post dto
 */
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Getter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
public class MemberJoinRequestDto {

    private String username;

    private String password;

    private String nickname;

    private String gender;

}
