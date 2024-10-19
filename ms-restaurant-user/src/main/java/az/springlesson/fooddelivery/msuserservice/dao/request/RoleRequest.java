package az.springlesson.fooddelivery.msuserservice.dao.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoleRequest {
    private String name;
    private String description;
}
