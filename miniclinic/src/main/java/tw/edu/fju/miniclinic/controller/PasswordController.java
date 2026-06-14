package tw.edu.fju.miniclinic.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tw.edu.fju.miniclinic.model.Doctor;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import tw.edu.fju.miniclinic.model.PasswordForm;

@Controller
public class PasswordController {

    @Autowired
    private DoctorRepository doctorRepo;

    @GetMapping("/password")
    public String showPasswordChangeForm(HttpSession session, Model model) {
        String loggedInDoctorName = (String) session.getAttribute("loggedInDoctorName");
        if (loggedInDoctorName == null) {
            // 應由 Interceptor 處理，但作為安全備份
            return "redirect:/login";
        }
        model.addAttribute("loggedInDoctorName", loggedInDoctorName);
        if (!model.containsAttribute("passwordForm")) {
            model.addAttribute("passwordForm", new PasswordForm());
        }
        return "password";
    }

    @PostMapping("/password")
    public String changePassword(
            @Valid @ModelAttribute("passwordForm") PasswordForm form,
            BindingResult result,
            HttpSession session,
            Model model) {

        String loggedInDoctorId = (String) session.getAttribute("loggedInDoctorId");
        if (loggedInDoctorId == null) {
            // 應由 Interceptor 處理，但作為安全備份
            return "redirect:/login";
        }

        // 將醫師姓名重新加入 Model，以便在錯誤時顯示
        model.addAttribute("loggedInDoctorName", session.getAttribute("loggedInDoctorName"));

        if (result.hasErrors()) {
            return "password"; // 返回表單並顯示驗證錯誤
        }

        Doctor doctor = doctorRepo.findById(loggedInDoctorId).orElse(null);
        if (doctor == null) {
            session.invalidate(); // 醫師不存在，使 Session 失效
            return "redirect:/login";
        }

        // 1. 檢查舊密碼
        if (!BCrypt.checkpw(form.getOldPassword(), doctor.getPasswordHash())) {
            model.addAttribute("errorMessage", "舊密碼錯誤");
            return "password";
        }

        // 2. 檢查新密碼與確認密碼是否一致
        if (!form.getNewPassword().equals(form.getConfirmNewPassword())) {
            model.addAttribute("errorMessage", "兩次密碼不相符");
            return "password";
        }

        // 全部驗證通過：更新密碼
        doctor.setPasswordHash(BCrypt.hashpw(form.getNewPassword(), BCrypt.gensalt()));
        doctorRepo.save(doctor);

        model.addAttribute("successMessage", "密碼已成功修改！");
        // 可以選擇重導向到儀表板，或留在當前頁面顯示成功訊息
        // return "redirect:/dashboard";
        return "password";
    }
}