package io.scode.imageconvertservice.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ValidateTestDataCreator {

    public List<String> getEncodedStrings(List<String> originStringList) {
        return originStringList.stream()
                .map(s -> Base64.getUrlEncoder().encodeToString(s.getBytes()))
                .toList();
    }

    public List<String> getSafeStrings() {

        List<String> originStringList = new ArrayList<>();

        originStringList.add("<div></div><style></style>");
        originStringList.add("<div><span>hello world!</span></div><style></style>");

        originStringList.add("<div><h1>hello world!</h1><span>lorem!</span></div><style></style>");

        originStringList.add("<div><ul><li></li><li></li></ul></div><style></style>");

        originStringList.add("<style></style><div></div>");
        originStringList.add("<div></div>\n" +
                "<style> div { width:100px; height:100px; background-color:red; } </style>");
        originStringList.add("<div></div><style> div { width:100px; height:100px; background-color:red; } </style>");
        originStringList.add("<div> <div></div></div>      <style></style>");


        return originStringList;
    }

    public List<String> getUnSafeStrings() {

        List<String> originStringList = new ArrayList<>();

        originStringList.add("Test String");
        originStringList.add("<foo></foo><var></var>");
        originStringList.add("<span></span>\n" +
                "<style> span { width:100px; height:100px; background-color:red; } </style>");
        originStringList.add("<script></script><div> div { width:100px; height:100px; background-color:red; } </div>");
        originStringList.add("3.141592");
        originStringList.add("<div><style></dav><stole>");

        originStringList.add("<li><div><style></dav><stole></li>");

        originStringList.add("<html>" +
                "<div></div>" +
                "<li></li>" +
                "<style><style></html>");

        originStringList.add("<html>" +
                "<div></div>" +
                "<style><style><li></li></html>");

        return originStringList;
    }

}
