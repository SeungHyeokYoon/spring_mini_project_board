package com.project2.board.service;

import com.project2.board.exception.post.PostNotFoundException;
import com.project2.board.model.Post;
import com.project2.board.model.PostPatchRequestBody;
import com.project2.board.model.PostPostRequestBody;
import com.project2.board.model.entity.PostEntity;
import com.project2.board.repository.PostEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostEntityRepository postEntityRepository;

    public List<Post> getPosts() {
        var postEntities = postEntityRepository.findAll();

        return postEntities.stream().map(Post::from).toList();
    }

    public Post getPostByPostId(Long postId) {
        var postEntity = postEntityRepository
                .findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException(postId)
                );

        return Post.from(postEntity);
    }

    public Post createPost(PostPostRequestBody postPostRequestBody) {
        var postEntity = new PostEntity();
        postEntity.setBody(postPostRequestBody.body());
        var savedPostEntity = postEntityRepository.save(postEntity);
        return Post.from(savedPostEntity);
    }

    public Post updatePost(Long postId, PostPatchRequestBody postPatchRequestBody) {
        var postEntity = postEntityRepository
                .findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException(postId)
                );

        postEntity.setBody(postPatchRequestBody.body());
        var updatedPostEntity = postEntityRepository.save(postEntity);
        return Post.from(updatedPostEntity);
    }

    public void deletePost(Long postId) {
        var postEntity = postEntityRepository
                .findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException(postId)
                );

        postEntityRepository.delete(postEntity);
    }
}
