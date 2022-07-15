package com.ll.exam.Controller;

import com.ll.exam.Post;
import com.ll.exam.Repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 테스트 인스턴스의 라이프 사이클을 설정할 때 사용한다
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostControllerTest {

    private PostRepository postRepository;
    @BeforeAll
    public void beforeAll() {
        postRepository = new PostRepository();
    }

    @BeforeEach
    public void beforeEach() {


        ArrayList<Post> list = postRepository.getList();
        for (Post i : list) {
            System.out.println("Post = " + i);
        }
        Post post = new Post("나에게 불가능이란 없다.", "나폴레옹");
        postRepository.savePost(post);


    }

    @Test
    public void 저장() {
        ArrayList<Post> list = postRepository.getList();
        Post post = new Post("나의 죽음을 적들에게 알리지 마라.", "이순신");
        postRepository.savePost(post);

        Post result = postRepository.findById(post.getId());
        System.out.println("post.getAuthor() = " + post.getAuthor());
        System.out.println("result.getAuthor() = " + result.getAuthor());
        Assertions.assertThat(post).isEqualTo(result);

    }

    @Test
    public void 조회() {

    }


}