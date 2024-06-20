package domain.card;

import domain.OutputView;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void clear(){
        gamers.clear();
    }

    @Test
    void 시나리오1(){
        // 예제와 동일하게
        init();// 딜러 및 플레이어 생성, 카드 분배
        result();
    }

    @Test
    void 시나리오2(){
        // 딜러가 21을 초과할 경우
        init2(); // 딜러 및 플레이어 생성, 카드 분배
        result();
    }


    private void initGamer() {
        gamers.add(new Dealer("딜러", 0));
        gamers.add(new Player("pobi", 0));
        gamers.add(new Player("jason", 0));
    }


    void init(){
        initGamer();

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

    void init2(){
        initGamer();
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

    void result(){
        int dealerScore = 0;
        int []result = new int[gamers.size()];
        for(int i= 0; i<gamers.size(); i++){
            Gamer gamer = gamers.get(i);
            int gamerScore = gamer.checkCard();
            OutputView.printResultDetail(gamer.getName(), gamer.getAllCards(), gamerScore);
            if(i==0){dealerScore=gamerScore;}
            else if(i!=0){
                // 플레이어부터 딜러와 비교하여 대결
                // 21을 초과했다면 진거임
                if(dealerScore>GOAL_VALUE) { // 딜러 루즈
                    result[i]++;
                } else if (gamerScore>GOAL_VALUE) { // 플레이어 루즈
                    result[HEAD]++;
                } else if(dealerScore>gamerScore){ // 플레이어 루즈
                    result[HEAD]++;
                } else if(dealerScore<gamerScore){ // 딜러 루즈
                    result[i]++;
                }
            }
        }
        // 최종 승패 출력
        OutputView.printResultSummary(gamers,result);
    }
}
