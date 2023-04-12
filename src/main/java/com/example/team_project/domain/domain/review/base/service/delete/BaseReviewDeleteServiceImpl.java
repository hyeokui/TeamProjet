package com.example.team_project.domain.domain.review.base.service.delete;

import com.example.team_project.domain.domain.review.base.domain.BaseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseReviewDeleteServiceImpl implements BaseReviewDeleteService {
    private final BaseReviewRepository baseReviewRepository;

    /**
     *  베이스 리뷰의 고유번호,유저의 고유번호,비밀번호를 받아옴
     *  베이스리뷰의 고유번호로 베이스리뷰를 가져옴
     *  베이스리뷰의 저장된 유저와 ,가져온 유저의 고유번호가 같을때
     *  delete()함수를 실행해 존재여부를 delete 로 변경
     */
    @Override
    public void delete(Long baseReviewId, Long userId) {

       baseReviewRepository.findById(baseReviewId).ifPresent(baseReview -> {
            if(baseReview.getUser().getId().equals(userId)) {
                baseReview.delete();
            }
        });
    }
}
