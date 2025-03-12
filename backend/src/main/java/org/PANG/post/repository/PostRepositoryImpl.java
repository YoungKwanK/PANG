package org.PANG.post.repository;

import lombok.RequiredArgsConstructor;
import org.PANG.post.application.interfaces.PostRepository;
import org.PANG.post.domain.Post;
import org.PANG.post.repository.entity.PostEntity;
import org.PANG.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity(post);
        entity = jpaPostRepository.save(entity);
        return entity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity entity = jpaPostRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return entity.toPost();
    }
}
