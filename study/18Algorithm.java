풀이 코드
그리디 섹션에 있는 문제이기 때문에 당연히 그리디로 풀어야하는건 알고있지만, 왜 이 문제가 그리디인가에 대해 생각을 했다.

미래를 따지지 않고 현재 선택할 수 있는 가장 좋은 것만을 선택하며 최적의 해를 뽑아내는 알고리즘이 그리디 알고리즘의 정의라고 알고

있는데, 그냥 이 문제에서는 간단한 아이디어를 통한 구현 문제라고 느껴졌다. 왜 굳이 그리디로 분류했는지는 의문이다 ,, ㅎㅎ

문제에서 지원자를 뽑는 기준으로, 

- 지원자 1명을 다른 모든 지원자와 비교해서 키와 몸무게 두 부분 중 자신보다 높은 키와 몸무게를 가진 지원자가 있다면 탈락이다.

- 즉, 자신이 다른 지원자보다 키 또는 몸무게가 높거나 무거우면 선발된다는 뜻.

 

강의에서는 compareTo() 메서드를 재정의하여 키를 기준으로 내림차순 정렬을 이용한 풀이를 선보였다.

그렇게 되면 키가 가장 큰 지원자를 기준으로, 다른 지원자들과 비교하면서 탐색을 시작하는데, 키는 이미 비교가 끝났으므로 비교 대상은

몸무게로 줄게 된다. 키가 가장 큰 지원자는 몸무게가 가볍든 무겁든 선발이 될 것이고, 이 지원자의 몸무게를 기준으로 다른 지원자들과

비교하면서 두 번째로 키 큰 사람이 무게는 더 나가는가 ? (키가 작으니 몸무게라도 무거워야 선발되기 때문), 세 번째로 키 큰 사람이 무게는

더 나가는가 ? 만약 무게가 더 나가는 사람이 있으면 그 사람의 몸무게를 기준으로 비교 .. 이런 식으로 쭉 돌려서 카운트를 올려주고

리턴하면 끝.

package study.section9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Member implements Comparable<Member> {

    int h;
    int w;

    public Member(int h, int w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public int compareTo(Member o) {
        return o.h - this.h;
    }
}

public class Section0901Review2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Member> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new Member(h, w));
        }

        int count = solution(n, list);
        System.out.println(count);
    }

    static int solution(int n, List<Member> list) {
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (Member m : list) {
            if (max < m.w) {
                max = m.w;
                count ++;
            }
        }

        return count;
    }
}

// 씨름선수로 뽑히는 최대 인원수를 구해라.
// 지원자 1명을 다른 지원자 모두와 비교해서 키와 몸무게 두 부분에서 자신보다 높은 키와 몸무게를 가진 지원자가 있다면 탈락한다.
// 기준 지원자를 찾고 완전 탐색을 실시한다.
// 아이디어) 키로 정렬을 해보자. 이렇게 하면 키를 따로 비교할 필요가 없어진다.
// 키와 몸무게 두 부분에서 모두 자신보다 우위에 있다면 탈락이기 때문에 키가 젤 큰 지원자는 무조건 선발된다.
// 키가 제일 큰 지원자 기준으로 2번째로 키가 큰 사람, 3번 째, 4번 쨰.. 순으로 몸무게를 비교하며 선발 인원을 증가하면 끝.
/*
* 183 65
* 181 60
* 180 70
* 172 67
* 170 72
* */
