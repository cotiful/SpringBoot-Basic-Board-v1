package site.metacoding.board.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

// interFace 로 안 만들고 클래스로 !
@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // 아래 클래스로 직접 쿼리를 짤 수 있다.
    private final EntityManager em;

}
