package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

@WebServlet("/api/v1/issues")
public class IssueServlet extends HttpServlet
{
    // GET /issues
    // GET /issues?{createdBy}&{createdOn}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        IssueDao issueDao = new IssueDao();
        List<Issue> issues;
        Issue issue;

        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("createdBy") && req.getParameterMap().containsKey("createdOn"))
        {
            issue = issueDao.getIssue(Integer.parseInt(req.getParameter("createdBy")), LocalDateTime.parse(req.getParameter("createdOn")));
            mapper.writeValue(resp.getOutputStream(), issue);
        } else
        {
            issues = issueDao.getAllIssues();
            mapper.writeValue(resp.getOutputStream(), issues);
        }

    }

    // POST /issues
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        IssueDao issueDao = new IssueDao();
        Issue insertIssue;

        insertIssue = mapper.readValue(req.getInputStream(), Issue.class);

        if (!issueDao.insertIssue(insertIssue.getCreatedById(), insertIssue.getMessage()))
        {
            resp.getWriter().println("Unable to insert issue. Missing either a createdBy id or message");
        }
    }


    // Update issue, usually for resolving issues
    // Date format should be ISO format
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        IssueDao issueDao = new IssueDao();


        Issue updatedIssue;

        updatedIssue = mapper.readValue(req.getInputStream(), Issue.class);

        if (!issueDao.updateIssue(updatedIssue))
        {
            resp.getWriter().println("Something went wrong when updating the issue");
        }
    }
}
