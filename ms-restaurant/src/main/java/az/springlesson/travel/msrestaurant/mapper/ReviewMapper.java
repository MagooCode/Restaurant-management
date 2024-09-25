package az.springlesson.travel.msrestaurant.mapper;

import az.springlesson.travel.msrestaurant.dao.request.ReviewRequest;
import az.springlesson.travel.msrestaurant.dao.response.ReviewResponse;
import az.springlesson.travel.msrestaurant.entity.ReviewEntity;

public enum ReviewMapper {
    REVIEW_MAPPER;

    public ReviewEntity mapToEntity(ReviewRequest reviewRequest,Long userId) {
        return ReviewEntity.builder()
                .reviewMessage(reviewRequest.getReviewMessage())
                .userId(userId)
                .build();
    }

    public ReviewResponse mapToResponse(ReviewEntity reviewEntity) {
        return ReviewResponse.builder()
                .reviewMessage(reviewEntity.getReviewMessage()).build();
    }
}
