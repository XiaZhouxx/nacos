package com.xz.nacos.algorithm;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.*;

/**
 * @author xz
 * @since 2023/12/20 10:52
 */
public class AcTrieTree {
    TrieTree root = new TrieTree();
    public static void main(String[] args) {
        AcTrieTree tree = new AcTrieTree();
        tree.root.insert("avc");
        tree.root.insert("bae");
        tree.root.insert("crb");

        tree.build();
        System.out.println(tree.match("aasljoqecrbae"));
    }

    public Set<String> match(String str) {
        Set<String> ans = new HashSet<>();
        TrieTree cur = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 如果当前节点的子节点不包含当前字符, 则尝试找fail指针
            while (cur != null && !cur.children.containsKey(c)) {
                cur = cur.fail;
            }
            if (cur == null) {
                cur = root;
            } else {
                cur = cur.children.get(c);
                TrieTree tmp = cur;
                // 看匹配的当前节点的fail指针是否末尾节点
                while (tmp != null) {
                    // 如果匹配到末尾节点，则说明匹配到了一个模式串
                    if (tmp.end) {
                        ans.add(str.substring(i - tmp.length + 1, i + 1));
                    }
                    tmp = tmp.fail;
                }
            }
        }

        return ans;
    }

    public void build() {
        // 1. 遍历root节点的子节点, 将子节点的fail指向root
        ArrayDeque<TrieTree> que = new ArrayDeque<>();
        for (Character key : root.children.keySet()) {
            root.children.get(key).fail = root;
            que.add(root.children.get(key));
        }
        // bfs 逐层计算fail指针
        while (!que.isEmpty()) {
            TrieTree parent = que.poll();
            for (Character key : parent.children.keySet()) {
                // 找父节点的fail节点
                TrieTree fail = parent.fail;
                TrieTree child = parent.children.get(key);
                // 当fail 存在, 且fail的子节点不包含当前节点时, 那么尝试找fail的fail节点
                while (fail != null && !fail.children.containsKey(key)) {
                    fail = fail.fail;
                }
                if (fail == null) {
                    child.fail = root;
                } else {
                    child.fail = fail.children.get(key);
                }
                que.add(child);
            }
        }
    }
    @Data
    @ToString(exclude = {"children", "fail"})
    static class TrieTree {
        private Map<Character, TrieTree> children = new HashMap<>();

        private TrieTree fail;

        private boolean end;

        private int length;

        private int pass;
        public void insert(String word) {
            char[] chars = word.toCharArray();
            TrieTree cur = this;
            for (char c : chars) {
                TrieTree child = cur.children.get(c);
                if (child == null) {
                    child = new TrieTree();
                    cur.children.put(c, child);
                }
                cur = child;
            }
            cur.end = true;
            cur.length = word.length();
        }
    }
}
