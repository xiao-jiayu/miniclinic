package tw.edu.fju.miniclinic.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 不自動創建 Session

        if (session != null && session.getAttribute("loggedInDoctorId") != null) {
            // 已登入，放行
            return true;
        }

        // 未登入
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"請先登入\"}");
            return false;
        } else {
            response.sendRedirect("/login"); // 重導向到登入頁
            return false;
        }
    }
}