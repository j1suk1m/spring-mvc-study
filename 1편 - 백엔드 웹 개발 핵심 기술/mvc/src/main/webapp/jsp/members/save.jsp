<%@ page import="study.servlet.domain.member.Member" %>
<%@ page import="study.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    // JSP가 서블릿으로 변환되기 때문에 request, response 사용 가능
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(name, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>name=<%=member.getName()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>