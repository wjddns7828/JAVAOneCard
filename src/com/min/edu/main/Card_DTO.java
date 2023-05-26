package com.min.edu.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.min.edu.card.CardDeck;
import com.min.edu.data.dao.UsersDaoImpl;

public class Card_DTO {
	public List<String> player = new ArrayList<String>() ;
	public List<String> AI = new ArrayList<String>();
	public List<String> deck = new ArrayList<String>();
	public List<String> field = new ArrayList<String>();
	UsersDaoImpl dao = new UsersDaoImpl();
	String putcard = "";
	
	String id = "";
	String pw = "";
	String name = "";
	int money =0;
	int betmoney =0;
}
