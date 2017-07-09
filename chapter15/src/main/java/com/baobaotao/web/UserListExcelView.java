package com.baobaotao.web;

import com.baobaotao.domain.User;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class UserListExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "inline; filename=" + new String("用户列表.xls".getBytes(), "iso8859-1"));
        List<User> userList = (List<User>) model.get("userList");
        Sheet sheet = workbook.createSheet("users");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("帐号");
        header.createCell(1).setCellValue("姓名");
        header.createCell(2).setCellValue("生日");

        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getUserName());
            row.createCell(1).setCellValue(user.getRealName());
            String createDate = DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd");
            row.createCell(2).setCellValue(createDate);
        }
    }
}
