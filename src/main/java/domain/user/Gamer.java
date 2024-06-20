package domain.user;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
    final List<Card> cards = new ArrayList<>();

    // 게이머들은 아래와 같은 공통 기능을 갖는다
    // 카드를 갖는다.
    public void addCard(Card card) {
        cards.add(card);
    }

    // 카드를 체크한다.

    public void checkCard(){
        // 자신의 카드 체크
        // this.cards
    }

    // 모든 카드를 반환한다.
}
