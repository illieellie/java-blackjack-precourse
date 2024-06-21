package domain.card;

import domain.OutputView;
import domain.user.Dealer;
import domain.user.Game;
import domain.user.Gamer;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static domain.user.Gamer.GOAL_VALUE;
import static org.assertj.core.api.Assertions.assertThat;


public class GamerTest {
    final int HEAD = 0;
    List<Gamer> gamers = new ArrayList<>(); // 게임 참가자들
    Game game = new Game();
    int result[];

    @BeforeEach
    void clear(){
        gamers.clear();
        initGamer(); // 게이머 생성
    }

    @Test
    void 시나리오_테스트_1(){
        // 예제와 동일하게
        initCards();// 딜러 및 플레이어 생성, 카드 분배
        repeatLogic();

        Assertions.assertThat(result[1]).isEqualTo(1); // 포비 1승
    }

    @Test
    void 시나리오_테스트_2(){
        // 딜러가 21을 초과할 경우
        initCards2(); // 딜러 및 플레이어 생성, 카드 분배
        repeatLogic();

        Assertions.assertThat(result[0]).isEqualTo(0); // 딜러 승 0
        Assertions.assertThat(result[1]).isEqualTo(1); // 플레이어 승 1
        Assertions.assertThat(result[2]).isEqualTo(1); // 플레이어 승 1
    }
    @Test
    void 시나리오_테스트_3(){
        // 딜러가 카드를 AAA 받은 경우 값을 잘 계산하는지 확인
        initCards3();
        repeatLogic();
        Assertions.assertThat(gamers.get(0).getResultScore()).isEqualTo(13);

    }


    private void repeatLogic(){
        game.setScoreAllUsers(gamers);

        // 결과 출력
        for (int i = 0; i < gamers.size(); i++) {
            Gamer gamer = gamers.get(i);
            OutputView.printResultDetail(gamer.getName(), gamer.getAllCards(), gamer.getResultScore());
        }
        result = game.makeResult(gamers);
    }

    private void initGamer() {
        gamers.add(new Dealer("딜러", 0));
        gamers.add(new Player("pobi", 0));
        gamers.add(new Player("jason", 0));
    }

    void initCards(){
        // 카드 입력
        // 딜러
        gamers.get(0).addCard(new Card(THREE,DIAMOND));
        gamers.get(0).addCard(new Card(NINE,CLUB));
        gamers.get(0).addCard(new Card(EIGHT,DIAMOND));
        // 포비
        gamers.get(1).addCard(new Card(TWO,HEART));
        gamers.get(1).addCard(new Card(EIGHT,SPADE));
        gamers.get(1).addCard(new Card(ACE,CLUB));
        // 제이슨
        gamers.get(2).addCard(new Card(SEVEN,CLUB));
        gamers.get(2).addCard(new Card(KING,SPADE));
    }

    void initCards2(){
        // 카드 입력
        // 딜러
        gamers.get(0).addCard(new Card(TEN,DIAMOND));
        gamers.get(0).addCard(new Card(TEN,CLUB));
        gamers.get(0).addCard(new Card(TEN,DIAMOND));
        // 포비
        gamers.get(1).addCard(new Card(TWO,HEART));
        gamers.get(1).addCard(new Card(EIGHT,SPADE));
        gamers.get(1).addCard(new Card(ACE,CLUB));
        // 제이슨
        gamers.get(2).addCard(new Card(SEVEN,CLUB));
        gamers.get(2).addCard(new Card(KING,SPADE));
    }

    void initCards3(){
        // 딜러
        gamers.get(0).addCard(new Card(ACE,DIAMOND));
        gamers.get(0).addCard(new Card(ACE,CLUB));
        gamers.get(0).addCard(new Card(ACE,DIAMOND));
        // 포비
        gamers.get(1).addCard(new Card(TWO,HEART));
        gamers.get(1).addCard(new Card(EIGHT,SPADE));
        gamers.get(1).addCard(new Card(ACE,CLUB));
        // 제이슨
        gamers.get(2).addCard(new Card(SEVEN,CLUB));
        gamers.get(2).addCard(new Card(KING,SPADE));
    }

}
