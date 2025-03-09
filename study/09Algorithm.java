문제 설명
동빈이는 N개의 원소로 구성되어 있고 배열의 원소가 모두 자연수인 두 배열 A와 B를 가지고 있다.

동빈이는 최대 K번의 바꿔치기 연산을 수행할 수 있는데, 바꿔치기 연산이란 배열 A에 있는 원소 하나와 배열 B에 있는 원소 하나를 골라서 두 원소를 서로 바꾸는 것을 말한다.

동빈이의 최종 목표는 배열 A의 모든 원소의 합이 최대가 되도록 하는 것이며, 여러분은 동빈이를 도와야 한다.

N, K, 그리고 배열 A,B 의 정보가 주어졌을 때, 최대 K번의 바꿔치기 연산을 수행하여 만들 수 있는 배열 A의 모든 원소의 합의 최댓값을 출력하는 프로그램을 작성하시오.

예를 들어 N = 5, K = 3이고 배열 A = [1,2,5,4,3] , 배열 B = [5,5,6,6,5] 라고 했을 때 다음과 같이 세 번의 연산을 수행할 수 있다.

 

1) 배열 A의 원소 1과 배열 B의 원소 6을 바꾸기.

2) 배열 A의 원소 2와 배열 B의 원소 6을 바꾸기.

3) 배열 A의 원소 3과 배열 B의 원소 5를 바꾸기.

 

세 번의 연산 이후 배열 A와 배열 B의 상태는 다음과 같이 구성될 것이다.

배열 A = [6,6,5,4,5], 배열 B = [3,5,1,2,5]

이 때 배열 A의 모든 원소의 합은 26이 되며, 이보다 더 합을 크게 만들 수는 없다. 따라서 26을 출력한다.

 

 

입력 조건
첫 번째 줄에 N, K가 공백으로 구분되어 입력된다. (1 <= N <= 100,000, 0 <= K <= N)

두 번째 줄에 배열 A의 원소들이 공백으로 구분되어 입력된다. 모든 원소는 10,000,000 보다 작은 자연수이다.

세 번째 줄에 배열 B의 원소들이 공백으로 구분되어 입력된다. 모든 원소는 10,000,000 보다 작은 자연수이다.

 

 

출력 조건
최대 K 번의 바꿔치기 연산을 수행하여 만들 수 있는 배열 A의 모든 원소의 합의 최댓값을 출력한다.

 

 

풀이 코드
아이디어는 간단한데 자바에서 제공하는 배열 정렬 메서드를 사용해서 배열 A는 오름차순, 배열 B는 내림차순으로 정렬한다.

배열 A에 최대값이 들어가야하므로 A, B 배열을 비교한 뒤 A 배열의 원소가 B 배열의 원소보다 작을 때만 B 배열의 최대값으로 원소를 바꿀 수 있도록 분기가 필요하다. 이후 배열 A의 모든 원소 값을 더한 sum 을 출력하면 풀이 완료.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두_배열의_원소_교체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arrA = new int[n];
        Integer[] arrB = new Integer[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        // A 배열은 오름차순, B 배열은 내림차순으로 정렬한다.
        // A = [1,2,3,4,5], B = [6,6,5,5,5]
        Arrays.sort(arrA);
        Arrays.sort(arrB, Collections.reverseOrder());

        // K 만큼 반복하면서 i번째 원소를 서로 교환한다.
        for (int i = 0; i < k; i++) {
            if (arrA[i] < arrB[i]) {
                int tmp = arrA[i];
                arrA[i] = arrB[i];
                arrB[i] = tmp;
            }
        }

        int sum = 0;
        for (int i : arrA) {
            sum += i;
        }

        System.out.println(sum);
    }
}
