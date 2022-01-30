<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="<c:url value="/js/fiber_page_change_colors.js"/>"></script>
    <style><%@include file="/css/styles.css"%></style>
    <title>Fibers</title>
</head>
<body>
    <h5 class="page_title">Fibers</h5>
    <div class="container">
        <c:forEach items="${fibers}" var="fiber">
            <div class="item">
                <p>
                    ${fiber.creationDateToString()}
                    #${fiber.getId()}
                    <a href="<c:url value="/fiber?fiber_id=${fiber.getId()}"/>" target="_self">
                        <br>${fiber.getSection()}
                    </a>
                </p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
