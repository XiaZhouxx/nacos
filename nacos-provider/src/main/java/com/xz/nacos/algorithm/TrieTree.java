package com.xz.nacos.algorithm;

import org.apache.commons.lang.StringUtils;

/**
 *  前缀树, 查字符前缀
 */
public class TrieTree {
    private TreeNode root;

    public TrieTree() {
        root = new TreeNode();
    }

    /**
     *  前缀树是一个多叉树, 存在某个字符则经过数 + 1, 最后停在某个字符则 end + 1.
     *  在查询某个前缀时, 例如 ab , 能够通过root 逐层 a -> b 最终找到b, b的 pass 数则是 包含ab前缀数量
     *             root
     *          a        b
     *       b             c
     *    c    s             d
     *           a              e
     *
     *   number = 10
     */
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.add("abc");
        tree.add("abcd");
        tree.add("absk");
        tree.add("absb");
        tree.add("bcde");
        System.out.println(tree.search("abc"));
        System.out.println(tree.search("abs"));
        System.out.println(tree.search("ab"));
        System.out.println(tree.delete("absk"));
        System.out.println(tree.search("a"));
        System.out.println(tree.search("b"));
    }

    /**
     * 往前缀树中新增字符串
     * @param word
     */
    public void add(String word) {
        if (StringUtils.isEmpty(word)) {
            return ;
        }
        // 根节点的通过数加1, 可以理解成以当前前缀下有多少字符串
        char[] c = word.toCharArray();
        root.pass++;
        TreeNode[] next = root.children;
        TreeNode last = root;
        for (char value : c) {
            int idx = value - 'a';
            last = next[idx];
            if (last == null) {
                last = new TreeNode();
                next[idx] = last;
            }
            last.pass++;
            next = last.children;
        }
        last.end ++;
    }

    /**
     * 查询以word作前缀的字符串数量
     * @param word 字符前缀
     * @return 以 word 作前缀的字符串数量
     */
    public int search(String word) {
        if (StringUtils.isEmpty(word)) {
            return 0;
        }
        char[] c = word.toCharArray();
        TreeNode node = root;
        for (char value : c) {
            int idx = value - 'a';
            TreeNode next = node.children[idx];
            if (next == null) {
                return 0;
            }
            node = next;
        }
        return node.pass;
    }

    public boolean delete(String word) {
        if (search(word) != 0) {
            TreeNode node = root;
            node.pass --;
            char[] cr = word.toCharArray();
            for (char c : cr) {
                int idx = c - 'a';
                TreeNode next = node.children[idx];
                next.pass--;
                if (next.pass == 0) {
                    node.children[idx] = null;
                    return true;
                }
                node = next;
            }
            node.end --;
            return true;
        }
        return false;
    }

    static class TreeNode {
        /**
         * 记录通过该数的数量(可用作统计)
         */
        private int pass;

        /**
         * 记录在该字符结束的数量
         */
        private int end;

        /**
         * 前缀树 的children, 这里简单用例只存储小写字符
         * 只采用了 26长度的数组, 字符类型多的还是采用hash表
         *
         *
         * Map<Character, TreeNode> children;
         */
        private TreeNode[] children;

        public TreeNode() {
            children = new TreeNode[26];
        }
    }
}
