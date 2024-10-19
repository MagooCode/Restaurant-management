package az.msrestaurantgateway.filter;

import az.msrestaurantgateway.exception.UnauthorizedException;
import az.msrestaurantgateway.util.PathMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private final PathMatcher pathMatcher;

    @Autowired
    public RouteValidator(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;

    }

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register/user",
            "/api/v1/auth/register/admin"
    );

    private final Map<String,List<String>> routeRole = Map.of(
            "ADMIN", List.of("/**"),
            "USER",List.of("/api/v1/user/**", "/api/v1/order/**", "/api/v1/orderItem/**")
    );

    public void validateRoute(ServerWebExchange exchange,String role) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> endpoints = routeRole.get(role);
        List<Boolean> isMatched = new ArrayList<>();
        for(String endpoint : endpoints) {
            isMatched.add(pathMatcher.match(request.getURI().getPath(), endpoint));
        }

        if(!isMatched.contains(true)) {
            throw new UnauthorizedException("Unauthorized");
        }
    }

    public Predicate<ServerHttpRequest> isSecured =
            request-> openApiEndpoints.
                    stream()
                    .noneMatch(uri-> request.getURI().getPath().contains(uri));
}
