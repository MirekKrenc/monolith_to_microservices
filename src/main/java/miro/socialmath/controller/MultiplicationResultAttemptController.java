package miro.socialmath.controller;

import miro.socialmath.domain.MultiplicationResultAttempt;
import miro.socialmath.service.MultiplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

    private final MultiplicationService multiplicationService;

    public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    //POST
    @PostMapping
    public ResponseEntity<ResultResponse> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        return ResponseEntity.ok(new ResultResponse(multiplicationService.checkAttempt(multiplicationResultAttempt)));
    }


    static final class ResultResponse {
        private final boolean correct;

        ResultResponse(boolean correct) {
            this.correct = correct;
        }

        public ResultResponse() {
            this(true);
        }

        public boolean isCorrect() {
            return correct;
        }
    }
}
