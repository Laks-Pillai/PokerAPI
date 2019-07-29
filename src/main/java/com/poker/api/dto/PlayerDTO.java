package com.gpc.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * DTO obbject for Player
 * @author 
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDTO {

	private Hand hand;
	private int faceValue;
	private Long id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Hand getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}
	
	public setFaceValue(int value) {
		this.faceValue=value;
	}
	
	public int getFaceValue() {
		returnn this.faceValue;
	}
}
