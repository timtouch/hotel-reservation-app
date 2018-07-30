package com.revature.dao;

import com.revature.model.Issue;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IssueDao
{
    private static String getAllIssuesForHotelQuery = "SELECT * FROM ISSUE";
    private static String getAllUnresolvedIssuesQuery = "SELECT * FROM ISSUE WHERE IS_RESOLVED = 0";
    private static String getIssueQuery = "SELECT * FROM ISSUE WHERE CREATED_BY = ? AND CREATED_ON = ?";
    private static String insertIssueCallable = "{CALL INSERT_ISSUE(?,?)}"; //(CREATED_BY ID, MESSAGE)
    private static String updateIssueCallable = "update issue set resolved_by = ?, RESOLVED_ON = ?, is_resolved = ? where created_by = ? and created_on = ?"; // ( RESOLVED_BY, RESOLVED_ON, IS_RESOLVED, CREATED_BY, CREATED_ON)

    @SuppressWarnings("Duplicates")
    public List<Issue> getAllIssues(){
        Issue issue;
        List<Issue> issues = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllIssuesForHotelQuery))
        {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                issue = mapIssueTableToObject(rs);

                issues.add(issue);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return issues;
    }

    @SuppressWarnings("Duplicates")
    public List<Issue> getAllUnresolvedIssues(){
        Issue issue;
        List<Issue> issues = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllUnresolvedIssuesQuery))
        {
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                issue = mapIssueTableToObject(rs);

                issues.add(issue);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return issues;
    }

    public Issue getIssue(int createdBy, LocalDateTime createdOn){
        Issue issue = null;

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getIssueQuery))
        {
            ps.setInt(1, createdBy);
            ps.setTimestamp(2, Timestamp.valueOf(createdOn));

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                issue = mapIssueTableToObject(rs);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return issue;
    }

    public boolean insertIssue(int createdBy, String message){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertIssueCallable))
        {
            cs.setInt(1, createdBy);
            cs.setString(2, message);

            cs.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateIssue(Issue issue){
        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(updateIssueCallable))
        {

            ps.setInt(1, issue.getResolverId());
            ps.setTimestamp(2, Timestamp.valueOf(issue.getResolvedOn()));
            ps.setInt(3, issue.isResolved() ? 1 : 0);
            ps.setInt(4, issue.getCreatedById());
            ps.setTimestamp(5, Timestamp.valueOf(issue.getCreatedOn()));

            ps.executeUpdate();

            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Issue mapIssueTableToObject(ResultSet rs){
        Issue issue = null;
        try{
            int createdBy = rs.getInt("CREATED_BY");
            LocalDateTime createdOn = rs.getTimestamp("CREATED_ON").toLocalDateTime();
            String message = rs.getString("MESSAGE");
            int resolvedBy = rs.getInt("RESOLVED_BY");
            LocalDateTime resolvedOn = rs.getTimestamp("RESOLVED_ON") != null ? rs.getTimestamp("RESOLVED_ON").toLocalDateTime() : null;
            // b/c oracle doesn't use boolean, it is represented in the db as a number that should either be 1 (true) or 0 (false)
            boolean isResolved = rs.getInt("IS_RESOLVED") == 1;

            issue = new Issue(createdBy, createdOn, message, resolvedBy, resolvedOn, isResolved);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return issue;
    }
}
