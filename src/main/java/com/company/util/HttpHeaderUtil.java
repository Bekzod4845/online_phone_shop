package com.company.util;

import com.company.dto.JwtDTO;
import com.company.enums.ProfileRole;
import com.company.exp.NoPermissionException;

import javax.servlet.http.HttpServletRequest;

public class HttpHeaderUtil {
    public static String getId(HttpServletRequest request, ProfileRole requiredRole) {
        JwtDTO jwtDTO = (JwtDTO) request.getAttribute("jwtDTO");
        String id = jwtDTO.getId();
        if (requiredRole != null) {
            if (!requiredRole.equals(jwtDTO.getRole())) {
                throw new NoPermissionException("Not Access");
            }
        }
        return id;
    }

    public static String getId(HttpServletRequest request) {
        return getId(request, null);
    }
}
