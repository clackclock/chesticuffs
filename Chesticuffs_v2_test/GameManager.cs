using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
   public List<Card> deck = new List <Card>();
   public Transform[] cardSlots;
   public bool[] availableCardSlots;


   public void DrawCard(){
    if(deck.Count >= 1){
        Card randCard = deck[Random.Range(0, deck.Count)];
        
        for(int i = 0; i < availableCardSlots.Length; i++){
            if(availableCardSlots[i] == true){
                randCard.gameObject.SetActive(true);
                randCard.transform.position = cardSlots[i].position;
                availableCardSlots[i] = false;
                deck.Remove(randCard);
                return;

            }
        }
    }
   }
}
