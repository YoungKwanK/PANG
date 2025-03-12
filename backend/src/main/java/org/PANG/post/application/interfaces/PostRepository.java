package org.PANG.post.application.interfaces;

import org.PANG.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
