package com.example.team_project.controller.core.review.post;

import com.example.team_project.domain.domain.review.base.domain.BaseReviewRepository;
import com.example.team_project.domain.domain.review.base.service.dto.ReviewDto;
import com.example.team_project.domain.domain.review.base.service.update.BaseReviewUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/review/update")
public class PostReviewUpdateController {

    private final BaseReviewUpdateService baseReviewUpdateService;
    private final BaseReviewRepository baseReviewRepository;

    @GetMapping("")
    public String get(Long baseReviewId,
                      Model model){

        baseReviewRepository.findById(baseReviewId).ifPresent(baseReview -> {
            model.addAttribute("baseReview",baseReview);
        });
        return "thymeleaf/review/update";
    }

    @PostMapping("")
    public String post(@RequestParam("baseReviewId") Long baseReviewId,
                       Long userId,
                       ReviewDto dto,
                       MultipartFile file){
        baseReviewUpdateService.update(baseReviewId,151L,dto,file);
        return "redirect:/post/read?postId="+dto.getKindsId();
    }
}
