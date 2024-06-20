
주의 사항
- 공통 기능은 코드 중복 제거 
  - Gamer로 정의 (카드를 받고, 카드의 합을 체크)

구현
- 입력 : InputView
- 출력 : OutputView
- 공통 기능 구현 : Gamer
- main 구현
  - how to solve 순차적으로 구현 및 commit

how to solve
- List<Card> cards = CardFactory.create(); 를 통해 카드 생성
- 입력 : 게임에 참여할 사람 입력
- 카드를 가져간다. 딜러와 플레이어에게 각각 2장씩 -> (어떻게 랜덤으로 가져가지?) Collection.shuffle() 이용하기
- 출력 : 어떤 카드를 받았는지
- 플레이어 > 딜러 순으로 카드를 한장씩 받겠냐고 물어보고 y 입력이 들어오면 카드를 더 받는다
  딜러는 가지고 있는 카드의 합이 16이하이면 무조건 한장의 카드를 더 받는다
- 카드를 체크한다. 한장의 카드를 더 뽑았다가 21을 초과하면 지는거, 돌리다가 카드의합이 21 또는 21에 근접한 사람이 이김
  * Ace는 1 또는 11로 계산할 수 있음 
