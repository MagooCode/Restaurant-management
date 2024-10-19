package az.springlesson.fooddelivery.msuserservice.dao.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
}
