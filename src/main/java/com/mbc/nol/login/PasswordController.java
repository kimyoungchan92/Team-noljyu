package com.mbc.nol.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbc.nol.user.UserDTO;
import com.mbc.nol.user.UserService;

@Controller
public class PasswordController {

    @Autowired SqlSession sqlSession;

    // ID + �ݷ����� �Է� �� 
    @RequestMapping(value = "passwordfind")
    public String passwordFindPage() {
        return "passwordFind";  
    }

    // ID+�ݷ����� ���� �� �缳�� ��
    @RequestMapping(value = "passwordfindProcess")
    public String passwordFindProcess(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String petName = request.getParameter("petName");

        UserService us= sqlSession.getMapper(UserService.class);
        UserDTO user= us.getuser(id);

        if (user != null && user.getPetName().equals(petName)) {
            model.addAttribute("id", id);
            return "passwordReset";
        } else {
            model.addAttribute("msg", "�Է��Ͻ� ����(ID �Ǵ� �ݷ����� �̸�)�� ��ġ���� �ʽ��ϴ�.");
            return "passwordFind";
        }
    }

    // �� ��й�ȣ �Է� �� DB ������Ʈ 
    @RequestMapping(value = "passwordresetProcess")
    public String passwordResetProcess(HttpServletRequest request, HttpSession session, Model model) {
        String id = request.getParameter("id");
        String newPassword = request.getParameter("newPassword");

       if (newPassword == null || newPassword.trim().isEmpty()) {
            model.addAttribute("msg", "�� ��й�ȣ�� �Է��ϼ���.");
            model.addAttribute("id", id);
            return "passwordReset";
        }

        PasswordEncoder pe = new BCryptPasswordEncoder();
        String encodedPw   = pe.encode(newPassword);

        UserService us     = sqlSession.getMapper(UserService.class);
            // ��й�ȣ�� ����
            us.modify3(
                id, encodedPw
            );
            session.setAttribute("msg", "��й�ȣ�� ���������� �缳���Ǿ����ϴ�. �ٽ� �α����ϼ���.");
            return "redirect:/";
        }
    }

