package org.PANG.post.repository.entity;

import jakarta.persistence.AttributeConverter;
import org.PANG.post.domain.PostState;

public class PostStateConverter implements AttributeConverter<PostState, String> {

    @Override
    public String convertToDatabaseColumn(PostState postState) {
        return postState.name();
    }

    @Override
    public PostState convertToEntityAttribute(String s) {
        return PostState.valueOf(s);
    }
}
