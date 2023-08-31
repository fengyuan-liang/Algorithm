package com.fx.trie;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/24 14:31
 */
public class TrieTest {

    @Test
    public void TestAdd() {
       Trie<Integer> trie = new Trie<>();

       trie.add("cat", 1);
       trie.add("dog", 2);
       trie.add("catt", 3);
       trie.add("dog2", 4);
       trie.add("你好", 5);

        assertEquals(5, trie.size());
        assertEquals(Integer.valueOf(5), trie.get("你好"));
        assertTrue(trie.starsWith("do"));
        assertTrue(trie.starsWith("dog"));
        assertTrue(trie.starsWith("ca"));
        assertTrue(trie.starsWith("catt"));
        assertFalse(trie.starsWith("cat:t"));
    }
}
