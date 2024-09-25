package az.springlesson.travel.msrestaurant.mapper;

import az.springlesson.travel.msrestaurant.dao.response.DetailsResponse;
import az.springlesson.travel.msrestaurant.entity.Details;

public enum DetailsMapper {
    DETAILS_MAPPER;

    public DetailsResponse mapToResponse(Details details){
        return DetailsResponse.builder()
                .description(details.getDescription())
                .build();
    }
}
