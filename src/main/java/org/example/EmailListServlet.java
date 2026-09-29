package org.example;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.business.User;
import org.example.data.UserDB;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        String url = "/index.jsp";

        if (action.equals("join")) {
            url = "/index.jsp";
        } else if (action.equals("add")) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            User user = new User(email, firstName, lastName);
            request.setAttribute("user", user);

            boolean isDuplicate = UserDB.emailExists(email);

            if (isDuplicate) {
                request.setAttribute("message", "This email address already exists. Please enter another email address.");
                url = "/index.jsp";
            } else {
                // TODO: Thêm hàm UserDB.insert(user) vào Database tại đây ở bước học tiếp theo

                // --- BẮT ĐẦU PHẦN GỬI EMAIL ---
                String to = email; // Gửi đến email mà người dùng vừa nhập
                String from = "phatttran956@gmail.com"; // THAY BẰNG EMAIL CỦA EM
                String subject = "Gmail của bạn đã bị kiểm soát";
                String body = "Chào " + firstName + " " + lastName + ",\n\n" +
                        "Malware sẽ được gửi vô Gmail của bạn để lấy dữ liệu trong vòng 1 tiếng.\n\n" +
                        "Leu Leu";
                try {
                    org.example.util.MailUtil.sendMail(to, from, subject, body, false);
                } catch (Exception e) {
                    System.out.println("Lỗi khi gửi email: " + e.getMessage());
                }
                // --- KẾT THÚC PHẦN GỬI EMAIL ---

                request.setAttribute("message", "");
                url = "/thanks.jsp";
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}