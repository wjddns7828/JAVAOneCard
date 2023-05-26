package com.min.edu.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 모양과 숫자를 조합하여 카드 덱을 만들어내고 셔플을 해서 넘겨줌
 */
public class CardDeck {
	
	public  String[] shape = { "◆", "♥","♠","♣" };
	public  String[] number={"A","2","3","4","5","6","7","8","9","J","Q","K"};
		
	public List<String> CardOne() {
		List<String> deck = new ArrayList<String>();
		for (int i = 0; i <shape.length; i++) {
			for (int j = 0; j < number.length; j++) {
				deck.add(shape[i]+number[j]);
			}
		}
		Collections.shuffle(deck);
		return deck;
	}
}
