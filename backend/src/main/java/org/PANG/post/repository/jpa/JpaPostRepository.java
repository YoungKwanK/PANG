package org.PANG.post.repository.jpa;

import org.PANG.post.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

}
