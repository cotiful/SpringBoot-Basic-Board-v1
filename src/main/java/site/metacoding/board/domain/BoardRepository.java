package site.metacoding.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPa가 제공해주는 메서드 
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
