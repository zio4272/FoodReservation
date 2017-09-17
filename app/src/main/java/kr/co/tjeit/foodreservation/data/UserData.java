package kr.co.tjeit.foodreservation.data;

import java.io.Serializable;

/**
 * Created by ziO on 2017-09-17.
 */

public class UserData implements Serializable {

    private int id; // 사용자 고유 아이디
    private String userId; // 사용자의 로그인 아이디
    private String password; // 사용자의 로그인 패스워드
    private int age; // 사용자의 나이
    private int gender; // 사용자의 성별 (남자는 0 여자는 1로 설정)
}
