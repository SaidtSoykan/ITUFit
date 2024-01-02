package groupeighteen.itufit.webapi;

import org.springframework.web.bind.annotation.RestController;

import groupeighteen.itufit.application.services.comment.CommentAddRequest;
import groupeighteen.itufit.application.services.comment.CommentService;
import groupeighteen.itufit.application.shared.response.IResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping(value = "addComment", produces = "application/json")
    public IResponse add(@RequestBody CommentAddRequest commentAddRequest) {
        return commentService.add(commentAddRequest);
    }
    
}
