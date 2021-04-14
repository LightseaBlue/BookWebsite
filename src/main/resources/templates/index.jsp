<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.getRequestDispatcher("main.action?op=showAll").forward(request, response);
%>