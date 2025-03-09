풀이 코드
우선순위 큐를 연습할 수 있는 문제로 PriorityQueue 를 사용해서 오름차순 출력을 뽑아내는 문제이다.

절댓값이 같은 경우에서 정렬의 재정의가 필요한데, PriorityQueue 의 인자로 Comparator 를 사용할 수 있으므로 활용하면 된다.

 

먼저 Math.abs() 메서드를 통해 절댓값을 구하고, 오름차순에 알맞게 정의를 해준다.

abs1 이 abs2 보다 큰 경우 양수 즉, 자리바뀜이 일어나고, 음수일 때는 자리바뀜이 일어나지 않게 오름차순에 맞는 리턴을 셋팅하면 된다.

그럼 abs1 == abs2 (두 수의 절댓값이 같은 경우) 는 어떻게 정렬하면 될까 ?

문제 출력 예시로는 -1 과 1 이 출력될 때 -1 이 먼저 출력되고 그 뒤에 1이 출력되는 오름차순 형태인 것을 확인할 수 있다.

그렇다면 똑같이 o1 이 o2 보다 큰 경우 양수를 리턴하게 하면 두 수에서 오름차순이 정의되므로 아래 코드와 같이 정렬하면 끝.

 

풀이 중에 어떤 식으로 구현할지 주석으로 작성했다.

package backjoonsliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.PriorityQueue;

public class Boj11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 절대값을 정렬하기 위한 재정렬 부분
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            // 두 수의 절대값이 다른 경우 오름차순
            if (abs1 > abs2) {
                return 1;
            } else if (abs1 < abs2) {
                return -1;

            // 두 수의 절대값이 같은 경우 해당 두 수끼리 오름차순
            } else {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) {
                queue.offer(x);
            } else if (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
             else {
                 System.out.println(0 + " ");
             }
        }
    }
}

/* 절댓값 힙 문제
* 1. 배열에 정수 x(x != 0) 을 넣는다.
* 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 해당 값을 배열에서 제거한다.
* 3. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고 그 값을 배열에서 제거한다.
*
* x 가 0이 아니라면 배열에 x 값을 추가
* x 가 0이라면 배열에서 절댓값이 가장 작은 값을 출력, 해당 값을 제가
* */

/* input
* 8
* 1
* -1
* 0
* 0
* 2
* -2
* 0
* 0
* */

/* output
 * -1
 * 1
 * -2
 * 2
 * */
