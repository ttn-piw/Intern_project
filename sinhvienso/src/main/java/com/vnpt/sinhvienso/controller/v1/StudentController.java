package com.vnpt.sinhvienso.controller.v1;

import com.vnpt.sinhvienso.document.Student;
import com.vnpt.sinhvienso.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    UserService userService;

    Logger logger  = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("")
    public List<Student> getStudents(){
        return userService.getUserService();
    }
//    @GetMapping("")
//    public ResponseEntity<Object> getUsers(HttpServletRequest httpServletRequest,
//                                           @RequestParam(value = "key", required = true) String key,
//                                           Pageable pageable){
//        Boolean isAuth = false;
//
//        String requestPath = httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI() +
//                                                                            (httpServletRequest.getQueryString() != null
//                                                                            ? "?" + httpServletRequest.getQueryString()
//                                                                            : "");
//        logger.info(requestPath);
//        List<StudentReponse> page = studentService.getStudentsService(pageable);
//       try {
//            return (isAuth) ? ResponseEntity.ok(page)
//                            : ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Token has validated");
//       } catch (Exception ex){
//           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL: "+ ex.getMessage());
//       }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById (HttpServletRequest request, @PathVariable(value = "id", required = true) ObjectId id){
        String requestPath = request.getMethod() + " " + request.getRequestURI() + (request.getQueryString() != null
                                                                                    ? "?" + request.getQueryString()
                                                                                    : "");
        logger.info("STUDENT"+ requestPath);

        Boolean isAuth = true;
        try {
            if (isAuth)
                return ResponseEntity.ok().body(userService.getUserById(id));
            else
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("FAIL: Token has validated");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not exist"+ exception.getMessage());
        }
    }
}
