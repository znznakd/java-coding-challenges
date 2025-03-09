1) 공주 구하기

수건 돌리기와 비슷한? 문제였다.

N 명의 사람 중 K 라는 특정 수를 외치면 그 사람은 빠지고 한 사람이 남을 때 까지 이어서 진행, 그 사람을 구해내면 되는 간단한 문제였다.

예를 들어 N = 4 / K = 2 라면, [1, 2, 3, 4] -> [1, 3, 4] -> [1, 3] -> [1] 로 진행되어 1번 사람이 마지막에 남게된다.

 

헷갈렸던 부분은 특정 수를 외친 사람이 빠지고 다시 1부터 세야하는 부분 ? 이 조금 헷갈렸지만, Queue 를 사용해 특정 수를 외친 사람을

poll() 을 사용해 큐에서 빼고, 빠진 사람 앞의 사람들을 모두 뒤로 넘겨서 풀어낼 수 있었다. 뭔가 랜덤 댄스 챌린지 같은 ... ㅋㅋ

private static int solution(int n, int k) {
    Queue<Integer> queue = new LinkedList<>();
    int answer = 0;

    for (int i = 1; i <= n; i++) {
        queue.offer(i);
    }

    while (queue.size() != 1) {
    
    	// 숫자 외치지 않는 앞 사람들은 다시 뒤로 보내야한다.
        for (int i = 1; i < k; i++) {
            queue.offer(queue.poll());
        }
        
        // 특정 숫자를 외친 사람은 빼준다.
        queue.poll();
    }

	// 마지막에 남은 사람 추출
    answer = queue.poll();
    return answer;
}
