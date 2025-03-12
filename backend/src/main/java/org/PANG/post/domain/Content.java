package org.PANG.post.domain;

import lombok.Getter;


@Getter
public class Content {

    private String title;
    private String contentText;

    public Content(String title, String contentText) {
        this.title = title;
        this.contentText = contentText;
    }

    public void updateContent(String title, String contentText) {
        this.title = title;
        this.contentText = contentText;
    }

    //checkText


}
