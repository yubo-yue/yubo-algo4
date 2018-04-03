package amazon.list;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import yubo.algo.common.StdOut;

import java.util.Objects;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For exmaple:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4;
 * Return 1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 <= m <= n <= length of this list.
 * <p>
 * Notice:
 * # Initial question
 * Reverse a single linked list?
 * # further question 1
 * Reverse a single linked list, from position m to n. Do it in-place and in one-pass.
 * # further questions 2
 * Try other solution, can you solve it by recursive method?
 */
public final class ReverseLinkedList {

    @AllArgsConstructor
    @Setter
    private static class Node {
        public int val;
        public Node next;
    }

    @AllArgsConstructor
    private static class Head {
        public int length;
        public Node next;
    }

    public static void reverse(@NonNull final Head head, int m, int n) {
        Validate.isTrue(m <= n);
        Validate.isTrue(m >= 1);
        Validate.isTrue(n <= head.length);

        Node cur = head.next, prev = null, first = null, previousFirst = null;
        int cnt = 0;

        while (!Objects.isNull(cur)) {
            cnt++;
            if (cnt == m) {
                first = cur;
                previousFirst = prev;
            }

            if (cnt > m) {
                Node next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            } else {
                prev = cur;
                cur = cur.next;
            }

            if (cnt == n) {
                if (!Objects.nonNull(previousFirst))
                    head.next = prev;
                else
                    previousFirst.next = prev;

                first.next = cur;
                break;
            }
        }
    }

    private static void print(Head head) {
        Node p = head.next;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }

    private static Head newHead(int length) {
        return new Head(length, null);
    }

    private static Node newNode(int val) {
        return new Node(val, null);
    }

    private static Head addNode(@NonNull final Head h, final Node node) {
        Node p = h.next;
        while (Objects.nonNull(p) && Objects.nonNull(p.next)) {
            p = p.next;
        }

        if (!Objects.nonNull(p)) {
            h.next = node;
        } else {
            p.next = node;
        }

        return h;
    }


    public static void main(String[] args) {
        Head h = newHead(5);
        addNode(h, newNode(1));
        addNode(h, newNode(2));
        addNode(h, newNode(3));
        addNode(h, newNode(4));
        addNode(h, newNode(5));

        int m = 3, n = 5;
        StdOut.printf("Before reverse %d %d \n", m, n);
        print(h);

        reverse(h, m, n);

        StdOut.printf("After reverse %d %d \n", m, n);
        print(h);

    }

}
