## BST(Binary Search Tree)
 - 이진탐색 트리 -> 정렬된 트리
 - root node의 값보다 작은 값은 왼쪽 subtree에, 큰 값은 오른쪽 subtree에 있음(모든 subtree도 이 조건을 recursive하게 만족해야함)
 - 검색, 저장, 삭제: O(log n)
 - worst case: O(n)(한쪽으로 치우친 트리 = Linked List와 사실상 똑같음)
   - 자가 균형 이진 탐색 트리(Self-balancing BST)는 알고리즘으로 이진 트리의 균형이 잘 맞도록 유지하여 높이를 가능한 낮게 유지
     - AVL Tree와 Red-black tree
     - Java의 HashMap은 separate chaining 으로써 Linked List와 Red-black tree를 병행하여 저장함