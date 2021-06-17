package com.phillips.noanalyticsdev.util;

import javax.persistence.Embeddable;

@Embeddable
public class OnPage {
    private String pagetitle;
    private String pathname;

    public OnPage() {
    }

    public OnPage(String pagetitle, String pathname) {
        this.pagetitle = pagetitle;
        this.pathname = pathname;
    }

    public String getPagetitle() {
        return pagetitle;
    }

    public void setPagetitle(String pagetitle) {
        this.pagetitle = pagetitle;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
