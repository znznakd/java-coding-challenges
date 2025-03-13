두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.예를 들어 A가 B에게 선물을 5번 줬고, B가 A에게 선물을 3번 줬다면 다음 달엔 A가 B에게 선물을 하나 받습니다.두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다. B가 친구들에게 준 선물이 3개고 받은 선물이 2개라면 B의 선물 지수는 1입니다. 만약 A와 B가 선물을 주고받은 적이 없거나 정확히 같은 수로 선물을 주고받았다면, 다음 달엔 B가 A에게 선물을 하나 받습니다.만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.

위에서 설명한 규칙대로 다음 달에 선물을 주고받을 때, 당신은 선물을 가장 많이 받을 친구가 받을 선물의 수를 알고 싶습니다.

친구들의 이름을 담은 1차원 문자열 배열 friends 이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열 gifts가 매개변수로 주어집니다. 이때, 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 return 하도록 solution 함수를 완성


import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"}; //친구이름
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        int[] friends_rate = new int[friends.length];
        int[] awards = new int[friends.length];
        for (int i = 0; i < awards.length; i++) { // 0으로 초기화
            awards[i] = 0;
        }
        // 개인별 friends_rate 지수 정하기
        for (int i = 0; i < friends.length; i++) {
            int give = 0;
            int take = 0;
            for (int j = 0; j < gifts.length; j++) {
                if (gifts[j].contains(friends[i] + " ")) {
                    give += 1;
                }
                if (gifts[j].contains(" " + friends[i])) {
                    take -= 1;
                }
            }
            friends_rate[i] = give + take;
        }

        // 다음 달 받을 각 개인별 선물 수, 같을 경우 friends_rate으로 비교
        for (int i = 0; i < friends.length - 1; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                int num1 = 0; // i 준선물수
                int num2 = 0; // j 준선물수
                int sum1 = 0; // i 받아야할 선물 수
                int sum2 = 0; // j 받아야할 선물 수

                for (int k = 0; k < gifts.length; k++) {
                    if (gifts[k].equals(friends[i] + " " + friends[j])) {
                        num1 += 1;
                    }
                    if (gifts[k].equals(friends[j] + " " + friends[i])) {
                        num2 += 1;
                    }
                }
                //주고 받은 갯수 비교
                if (num1 > num2) {
                    awards[i] += 1;
                }
                if (num1 < num2) {
                    awards[j] += 1;
                }
                if (num1 == num2) {
                    //선물 지수 비교
                    if (friends_rate[i] > friends_rate[j]) {
                        awards[i] += 1;
                    }
                    if (friends_rate[i] < friends_rate[j]) {
                        awards[j] += 1;
                    }
                }
            }

        }

        Arrays.sort(awards);// int[] (변수이름) 해서 선언X 메서드
        int answer = awards[(awards.length-1)];// 마지막 인덱스는 길이 번호가 아닌라는거 항상 명심

        System.out.println(Arrays.toString(awards));
        System.out.println(Arrays.toString(friends_rate));
        System.out.println(answer);
    }
}
