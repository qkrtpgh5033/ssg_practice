package com.ll.exam.Repository;
import com.ll.exam.Post;
import com.ll.exam.Repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 테스트 인스턴스의 라이프 사이클을 설정할 때 사용한다
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {

    private PostRepository postRepository;
    @AfterAll  // 테스트가 모두 끝나면 동작
    public void afterEach() {
        postRepository.removeAll();
    }

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
        Post post = new Post("나폴레옹", "나에게 불가능이란 없다.");
        postRepository.savePost(post);

    }

    @Test
    public void 저장() {
        ArrayList<Post> list = postRepository.getList();

        Post post = new Post("이순신", "나의 죽음을 적들에게 알리지 마라.");
        postRepository.savePost(post);


        Post result = postRepository.findById(post.getId());
        System.out.println("post.getId() = " + post.getId());
        System.out.println("result.getId() = " + result.getId());
        System.out.println("post.getAuthor() = " + post.getAuthor());
        System.out.println("result.getAuthor() = " + result.getAuthor());
        Assertions.assertThat(post).isEqualTo(result);

        for (Post i : list) {
            System.out.println("?" + i);
        }


    }

    @Test
    public void 조회() {
        System.out.println("--- 조회 --- ");
        ArrayList<Post> list = postRepository.getList();
        for (Post i : list) {
            System.out.println("?" + i);
        }
        Post findPost = postRepository.findById(1L);

        assertEquals(1L, findPost.getId());
        assertEquals("나에게 불가능이란 없다.", findPost.getContent());
        assertEquals("나폴레옹", findPost.getAuthor());

    }


}