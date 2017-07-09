package com.baobaotao.web;

import com.baobaotao.UserService;
import com.baobaotao.domain.Dept;
import com.baobaotao.domain.User;
import com.baobaotao.domain.UserEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes(value={"user","user1"}, types={Dept.class})
public class UserController {

    @Autowired
    private UserService userService;

    /*************************************request映射规则使用例子*********************************/
    @RequestMapping(value = "/register", method = RequestMethod.GET, params = "!myParam")
    public String register(@ModelAttribute("user") User user) {
        return "user/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createUser(User user) {
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "userId")
    public String delete(@RequestParam("userId") String userId) {
        return "user/test1";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView show1(@RequestParam("userId") String userId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/showUser");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }

    @RequestMapping(value="/showUser/{userId}")
    public ModelAndView show2(@PathVariable("userId") String userId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/showUser");
        mav.addObject("user", userService.getUserById(userId));
        return mav;
    }

    @RequestMapping(value="/register/{id}")
    public String register2(@PathVariable("id") String id, @ModelAttribute("target") String target){
        return "user/register" + id;
    }

    /**********************************入参绑定使用的例子*************************************************/
    // ①请求参数按名称匹配的方式绑定到方法入参中，方法返回对应的字符串代表逻辑视图名
    @RequestMapping(value = "/handle1")
    public String handle1(@RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          @RequestParam(value = "realName", required = false) String realName) {
        System.out.println("into handle1, userName=" + userName + ", password=" + password);
        return "success";
    }

    // ②将Cooke值及报文头属性绑定到入参中、方法返回ModelAndView
    @RequestMapping(value = "/handle2")
    public ModelAndView handle2(@CookieValue("JSESSIONID") String sessionId,
                                @RequestHeader("Accept-Encoding") String encoding,
//                                @RequestHeader("Keep-Alive") long keepAlive,
                                @RequestHeader("Accept-Language") String accpetLanguage) {
        System.out.println("into handle2, sessionId=" + sessionId + ", acceptLanguage=" + accpetLanguage);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/showUser");
        mav.addObject("user", new User());
        return mav;
    }

    // ③请求参数按名称匹配的方式绑定到user的属性中
    @RequestMapping(value = "/handle3")
    public ModelAndView handle3(User user) {
        System.out.println("into handle3, user=" + user);
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/showUser");
        mav.addObject("user", user);
        return mav;
    }

    // ④直接将HTTP请求对象传递给处理方法、方法返回对应的字符串代表逻辑视图名
    @RequestMapping(value = "/handle4")
    public ModelAndView handle4(HttpServletRequest request) {
        System.out.println("into handle4, request=" + request);
        String userName = WebUtils.findParameterValue(request, "userName");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("userName", userName);
        return mav;
    }

    // 5. 直接将HTTP请求对象传递给处理方法、方法返回HttpServletResponse
    @RequestMapping(value = "/handle5")
    public void handle5(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        response.addCookie(new Cookie("userName", userName));
        System.out.println("into handle5, response=" + response);
    }

    // 6. 使用HttpSession
    @RequestMapping(value = "/handle6")
    public String handle6(HttpSession session) {
        System.out.println("into handle6, session=" + session);
        session.setAttribute("sessionId", 1234);
        return "success";
    }

    // 7. 使用Spring提供的WebRequest
    @RequestMapping(value = "/handle7")
    public String handle7(WebRequest request) {
        String userName = request.getParameter("userName");
        System.out.println("into handle7, userName=" + userName);
        return "success";
    }

    // 8. 使用IO,使用OutputStream返回输出
    @RequestMapping(value = "/handle8")
    public void handle8(OutputStream os) throws IOException {
        System.out.println("handle8: copy image to outputstream");
        Resource res = new ClassPathResource("/image.jpg");
        FileCopyUtils.copy(res.getInputStream(), os);
    }

    /*************************HttpMessageConverter*********************/

    //9 演示使用RequestBody和HttpMessageConverter绑定请求体到入参
    @RequestMapping(value = "/handle9")
    public String handle9(@RequestBody String body) {
        System.out.println("into handler9, body=" + body);
        return "success";
    }

    //10 演示使用ResponseBody和HttpMessageConverter处理返回值
    @RequestMapping(value = "/handle10/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] handle10(@PathVariable("imageId") String imageId) throws IOException {
        System.out.println("handle10: load image of " + imageId);
        Resource res = new ClassPathResource("/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
        return fileData;
    }

    //11. 演示使用HttpEntity和HttpMessageConverter绑定请求到入参
    @RequestMapping(value = "/handle11")
    public String handle11(HttpEntity<String> requestEntity) {
        long contentLen = requestEntity.getHeaders().getContentLength();
        System.out.println("handle11 body:" + requestEntity.getBody());
        System.out.println("handle11: headers" + requestEntity.getHeaders());
        return "success";
    }

    //12. 演示使用ResponseEntity和HttpMessageConverter处理返回值
    @RequestMapping(value = "/handle12/{imageId}")
    public ResponseEntity<byte[]> handle12(@PathVariable("imageId") String imageId) throws Throwable {
        Resource res = new ClassPathResource("/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileData, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    //13. 演示使用HttpMessageConverter处理xml和json消息處理
    @RequestMapping(value = "/handle13")
    public ResponseEntity<User> handle13(HttpEntity<User> requestEntity) {
        User user = requestEntity.getBody();
        user.setUserId("1000");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**********************************spring处理模型数据*************************************************/
    @RequestMapping(value = "/handle31")
    public ModelAndView handle31(User user) {
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }
    @RequestMapping(value = "/handle32")
    public String handle32(@ModelAttribute("user") User user) {
        user.setUserId("1000");
        return "/user/createSuccess";
    }
    @RequestMapping(value = "/handle33")
    public String handle33(@ModelAttribute("user") User user) {
        user.setUserName("tom");
        return "/user/showUser";
    }
    @RequestMapping(value = "/handle34")
    public String handle34(ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        user.setUserName("tom");
        modelMap.addAttribute("testAttr", "value1");
        return "/user/showUser";
    }
    @RequestMapping(value = "/handle71")
    public String handle71(@ModelAttribute("user") User user) {
        user.setUserName("John");
        return "redirect:handle72.html";
    }
    @RequestMapping(value = "/handle72")
    public String handle72(ModelMap modelMap, SessionStatus sessionStatus) {
        User user = (User) modelMap.get("user");
        if (user != null) {
            user.setUserName("Jetty");
            sessionStatus.setComplete();
        }
        return "/user/showUser";
    }

    @ModelAttribute("user1")
    public User getUser1() {
        User user = new User();
        user.setUserId("1001");
        user.setUserName("user1");
        return user;
    }
    @ModelAttribute("user")
    public User getUser() {
        User user = new User();
        user.setUserId("1002");
        user.setUserName("user");
        return user;
    }
    @ModelAttribute("dept")
    public Dept getDept() {
        Dept dept = new Dept();
        return dept;
    }

    /***********************************************数据转换和数据校验*********************************************/
    @RequestMapping(value = "/handle81")
    public String handle81(@RequestParam("user") User user, ModelMap modelMap) {
        modelMap.put("user", user);
        return "/user/showUser";
    }

//        @InitBinder
//    public void initBinder(WebDataBinder binder) {
//         binder.registerCustomEditor(User.class, new UserEditor());
//         binder.setValidator(new UserValidator());
//    }

    @RequestMapping(value = "/handle91")
    public String handle91(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap mm) {
        if (bindingResult.hasErrors()) {
            return "/user/register3";
        } else {
            return "/user/showUser";
        }
    }

    @RequestMapping(value = "/handle92")
    public String handle92(@ModelAttribute("user") User user, BindingResult bindingResult) {
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "userName",
                "required");
        if ("aaaa".equalsIgnoreCase(user.getUserName())) {
            bindingResult.rejectValue("userName", "reserved");
        }
        if (bindingResult.hasErrors()) {
            return "/user/register4";
        } else {
            return "/user/showUser";
        }
    }

    /***********************************************视图和视图解析器举例*********************************************/
    @RequestMapping(value = "/showUserList")
    public String showUserList(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "user/userList";
    }

    @RequestMapping(value = "/showUserListByFtl")
    public String showUserListInFtl(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListFtl1";
    }

    @RequestMapping(value = "/showUserListByXls")
    public String showUserListInExcel(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();

        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListExcel";
    }

    @RequestMapping(value = "/showUserListByPdf")
    public String showUserListInPdf(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();

        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListPdf";
    }

    @RequestMapping(value = "/showUserListByXml")
    public String showUserListInXml(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListXml";
    }

    @RequestMapping(value = "/showUserListByJson")
    public String showUserListInJson(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListJson";
    }

    @RequestMapping(value = "/showUserListByI18n")
    public String showUserListInI18n(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆1");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListi18n";
    }

    @RequestMapping(value = "/showUserListMix")
    public String showUserListMix(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆1");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListMix";
    }

//    @RequestMapping(value = "/uploadPage")
//    public String updatePage() {
//        return "uploadPage";
//    }
//
//    @RequestMapping(value = "/upload")
//    public String updateThumb(@RequestParam("name") String name,
//                              @RequestParam("file") MultipartFile file) throws Exception {
//        if (!file.isEmpty()) {
//            file.transferTo(new File("d:/temp/" + file.getOriginalFilename()));
//            return "redirect:success.html";
//        } else {
//            return "redirect:fail.html";
//        }
//    }
//
//    @RequestMapping(value = "/throwException")
//    public String throwException() {
//        if (2 > 1) {
//            throw new RuntimeException("ddd");
//        }
//        return "success";
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public String handleException(RuntimeException re, HttpServletRequest request) {
//        return "forward:/error.jsp";
//    }
//
//    @RequestMapping(value = "/notFound")
//    public String notFound() {
//        return "successdddd";
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String notFound(HttpServletRequest request) {
//        return "forward:/error.jsp";
//    }

}
