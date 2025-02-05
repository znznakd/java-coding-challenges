[문제 설명]

영진이는 약속장소에 가기 위해 버스를 타려고 합니다. 버스에는 좌석이 총 seat개만큼 있습니다. 영진이는 버스 좌석에 앉아서 갈 수 있을지 궁금해합니다. 기점에서 출발한 버스가 영진이가 기다리는 정거장에 도착하기 전에 방문하는 각 정거장에서 승/하차한 승객 정보가 주어질 때, 영진이가 버스에 탄 순간 빈 좌석은 몇 개인지 구해주세요. 영진이가 기다리는 정거장에서는 영진이가 제일 먼저 버스에 탑승하며, 이전 정거장에서 버스에 탑승한 승객들은 남는 좌석이 있다면 항상 앉는다고 가정합니다. 또, 기점에서 출발하는 버스에는 승객이 0명 타고 있습니다.

예를 들어 다음은 좌석이 5개인 버스에 각 정거장에서 승/하차한 승객 정보를 나타냅니다. 영진이는 4번 정거장에서 기다리고 있으며, "On"은 승차한 승객, "Off"는 하차한 승객을 의미합니다.

 - 1번 정거장 : ["On", "On", "On"] (3명 승차, 0명 하차)
 - 2번 정거장 : ["Off", "On", "-"] (1명 승차, 1명 하차)
 - 3번 정거장 : ["Off", "-", "-"] (0명 승차, 1명 하차)

위와 같은 경우, 1번 정거장에서 3명이 승차하고, 2번 정거장에서 1명 승차 1명 하차, 3번 정거장에서 1명이 하차했으므로 4번 정거장에 도착한 버스에는 2명이 타고 있습니다. 4번 정거장에서는 영진이가 가장 먼저 탑승하므로, 남아있는 좌석 수는 3개입니다.

주어진 solution함수는 버스의 좌석 개수 seat, 기점에서 출발한 버스가 순서대로 방문한 정거장에서 승객이 승/하차한 정보를 담은 2차원 문자열 리스트 passengers가 주어질 때, 버스에 남아있는 좌석의 개수를 return 하는 함수입니다. solution 함수가 올바르게 작동하도록 빈칸을 채워 solution함수를 완성해 주세요.




[제한 사항]

1 ≤ seat ≤ 30
1 ≤ passengers의 길이 ≤ 10
1 ≤ passengers[i]의 길이 ≤ 20

- passengers[0]은 1번 정거장, passengers[1]은 2번 정거장, … passengers[i]는 i + 1번 정거장의 정보입니다.
- passengers의 길이가 n이라면, 영진이는 n + 1번 정거장에서 기다리고 있습니다.

passengers[i]의 길이는 모두 동일합니다.
passengers[i]의 원소는 "On", "Off" 또는 "-"입니다.
 - "-"는 배열의 가로(열) 길이를 맞추기 위한 요소로, 아무런 의미도 없습니다.
 - "-"가 "On", "Off" 사이에 있는 경우는 없습니다.



<풀이>

class Solution {
    public int solution(int seat, String[][] passengers) {
        int num_passenger = 0;
        for (int i = 0; i < passengers.length; i++) {
            num_passenger += func4(passengers[i]); // 승차 인원 추가
            num_passenger -= func3(passengers[i]); // 하차 인원 차감
        }
        int answer = seat - num_passenger; // 남은 좌석 계산
        return answer;
    }

    public int func1(int num) {
        if (0 > num) {
            return 0;
        } else {
            return num;
        }
    }

    public int func2(int num) {
        if (num > 0) {
            return 0;
        } else {
            return num;
        }
    }

    public int func3(String[] station) {
        int num = 0;
        for (int i = 0; i < station.length; i++) {
            if (station[i].equals("Off")) {
                num += 1;
            }
        }
        return num;
    }

    public int func4(String[] station) {
        int num = 0;
        for (int i = 0; i < station.length; i++) {
            if (station[i].equals("On")) {
                num += 1;
            }
        }
        return num;
    }
}
