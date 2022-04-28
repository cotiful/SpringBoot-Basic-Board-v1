package site.metacoding.board.web;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;
import site.metacoding.board.domain.Board;
import site.metacoding.board.domain.BoardRepository;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    // data 를 리턴하는 함수이다. 해당하는 함수는 reponseEntity 가 붙어서 Json을 리턴한다.
    // ViewResolver가 발동하지 않는다. @ ViewResovler 란 @controller 가 붙어 있을떄 작동한다
    @DeleteMapping("/api/board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer id) {
        boardRepository.deleteById(id);
        return new ResponseEntity<>("삭제잘됨", HttpStatus.OK);
    }

    @GetMapping({ "/", "/board" })
    public String boardList(Model model) {
        model.addAttribute("boards", boardRepository.findAll());
        return "boardList";
    }

    @GetMapping("/board/{id}")
    public String boardList(@PathVariable Integer id, Model model) {
        Optional<Board> boardOp = boardRepository.findById(id);
        if (boardOp.isPresent()) {
            model.addAttribute("board", boardOp.get());
        } else {
            return "error";
        }
        return "boardDetail";
    }

    @GetMapping("/board/writeForm")
    public String boardWirteForm() {
        return "boardWriteForm";
    }

    @GetMapping("/board/updateForm")
    public String boardUpdateForm() {
        return "boardUpdateForm";
    }

    @PostMapping("/board")
    public String boardWrite(Board board) {
        // title, content, nickname 받기
        boardRepository.save(board);
        return "redirect:/";
    }
}
