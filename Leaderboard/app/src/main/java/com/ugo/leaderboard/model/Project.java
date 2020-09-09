package com.ugo.leaderboard.model;

public class Project {
    private String mFirstname;
    private String mLastname;
    private String mEmail;
    private String mGithubUrl;
    private static final Project instance = new Project();
    public static Project getInstance() {
        return instance;
    }
    /* public Project(String fname, String lname, String email, String gitlink) {
        setFirstname(fname);
        setLastname(lname);
        setEmail(email);
        setGithubUrl(gitlink);
    } */

    private Project() {}

    public String getFirstname() {
        return mFirstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getGithubUrl() {
        return mGithubUrl;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setGithubUrl(String githubUrl) {
        mGithubUrl = githubUrl;
    }
}
