<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<br/>
<br/>
<h1>Dear <strong>${user}</strong>, You are not authorized to access this page.</h1>
<br/>
<a href="<c:url value="/" />">Go to home</a>  
<sec:authorize access="hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')">
          OR  <a href="<c:url value="/logout" />">Logout</a>
   </sec:authorize>
