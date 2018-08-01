package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.IssueDao;
import com.revature.model.Issue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/api/issues")
public class IssueServlet extends HttpServlet
{
    // GET /issues
    // GET /issues?{createdBy}&{createdOn}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        IssueDao issueDao = new IssueDao();
        List<Issue> issues;
        Issue issue;

        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("createdBy") && req.getParameterMap().containsKey("createdOn")){
            issue = issueDao.getIssue(Integer.parseInt(req.getParameter("createdBy")), LocalDateTime.parse(req.getParameter("createdOn")));
            mapper.writeValue(resp.getOutputStream(), issue);
        } else {
            issues = issueDao.getAllIssues();
            mapper.writeValue(resp.getOutputStream(), issues);
        }

    }

    // POST /issues
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        IssueDao issueDao = new IssueDao();
        Issue insertIssue;

        insertIssue = mapper.readValue(req.getInputStream(), Issue.class);

        if(!issueDao.insertIssue(insertIssue.getCreatedById(), insertIssue.getMessage())){
            resp.getWriter().println("Unable to insert issue. Missing either a createdBy id or message");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }
}
