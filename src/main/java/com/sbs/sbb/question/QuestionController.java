package com.sbs.sbb.question;

import com.sbs.sbb.question.Question;
import com.sbs.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question q = this.questionService.getQuestion(id);

        model.addAttribute("question", q);

        return "question_detail";
    }

    @GetMapping("/create")
    public String create() {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        String subject = questionForm.getSubject();
        String content = questionForm.getSubject();

        Question q = this.questionService.create(subject, content);

        return "redirect:/question/list";
    }
}