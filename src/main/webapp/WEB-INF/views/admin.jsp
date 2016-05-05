<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="success">
	Dear <strong>${user}</strong>, Welcome to Admin Page.
	<br/>
	Would you like to <a href="<c:url value='/appUser' />">Add Some Users</a> to keep yourself busy?
	<br/>
	<a href="<c:url value="/logout" />">Logout</a>
</div>
