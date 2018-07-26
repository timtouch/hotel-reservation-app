package com.revature.bean;

import java.time.LocalDateTime;

public class Issue
{
    private int createdById;
    private LocalDateTime createdOn;
    private String message;
    private int resolverId;
    private LocalDateTime resolvedOn;
    private boolean isResolved;

    public Issue()
    {
    }

    public Issue(int createdById, LocalDateTime createdOn, String message, int resolverId, LocalDateTime resolvedOn, boolean isResolved)
    {
        this.createdById = createdById;
        this.createdOn = createdOn;
        this.message = message;
        this.resolverId = resolverId;
        this.resolvedOn = resolvedOn;
        this.isResolved = isResolved;
    }

    public int getCreatedById()
    {
        return createdById;
    }

    public void setCreatedById(int createdById)
    {
        this.createdById = createdById;
    }

    public LocalDateTime getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn)
    {
        this.createdOn = createdOn;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getResolverId()
    {
        return resolverId;
    }

    public void setResolverId(int resolverId)
    {
        this.resolverId = resolverId;
    }

    public LocalDateTime getResolvedOn()
    {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDateTime resolvedOn)
    {
        this.resolvedOn = resolvedOn;
    }

    public boolean isResolved()
    {
        return isResolved;
    }

    public void setResolved(boolean resolved)
    {
        isResolved = resolved;
    }

    @Override
    public String toString()
    {
        return "Issue{" +
                "createdById=" + createdById +
                ", createdOn=" + createdOn +
                ", message='" + message + '\'' +
                ", resolverId=" + resolverId +
                ", resolvedOn=" + resolvedOn +
                ", isResolved=" + isResolved +
                '}';
    }
}
