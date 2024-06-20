import domain.InputView;
import domain.OutputView;
import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Gamer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static final int GOAL_VALUE = 21;
    public static final int HEAD = 0;
    public static final int DEALER_RULE = 16;

    public static void main(String[] args) {
        // 카드 준비
        List<Card> cards = new ArrayList<>(CardFactory.create());
        Collections.shuffle(cards); // 한번 섞고
        // 참가자 입력
        OutputView.printMent1();
        List<String> temp = InputView.getGamers();
        // 딜러, 플레이어 생성
        List<Gamer> gamers = new ArrayList<>(); // 게임 참가자들
        gamers.add(new Dealer("딜러", 0));
        for (int i = 0; i < temp.size(); i++) {
            gamers.add(new Player(temp.get(i), 0));
        }
        // 카드를 랜덤으로 2장 받는다
        for (int i = 0, j = 0; i < gamers.size() * 2; i++, j++) {
            if (j == gamers.size()) {
                j = 0;
            }
            gamers.get(j).addCard(cards.get(j));
            cards.remove(j);
        }
        // 카드 출력
        for (int i = 0; i < gamers.size(); i++) {
            OutputView.printMent5(gamers.get(i).getName(), gamers.get(i).getAllCards());
        }
        // 돌아가면서 카드를 받을지 물어보며 카드를 받는다
       
        // 플레이어
        for (int i = 1; i < gamers.size(); i++) {
            Gamer gamer = gamers.get(i);
            OutputView.printMent3(gamer.getName());
            String answer = InputView.getCard();
            if (answer.equals("y")) {
                gamer.addCard(cards.get(HEAD));
            }
            cards.remove(HEAD);
            OutputView.printMent5(gamer.getName(), gamer.getAllCards());
        }
        // 딜러는 가지고 있는 카드합이 16이하이면 한장의 카드를 더 받는다.
        Gamer dealer = gamers.get(HEAD);
        int sum = dealer.checkCard();
        if (sum <= DEALER_RULE) {
            dealer.addCard(cards.get(HEAD));
            cards.remove(HEAD);
            OutputView.printMent6();
        }
        // 결과 출력
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
