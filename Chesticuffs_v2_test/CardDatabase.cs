using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CardDatabase : MonoBehaviour
{
    public static List<Card> cardList = new List<Card> ();

    void Awake(){
        cardList.Add (new Card(0, "Basic_Seeds", 4, 2, 2, 2, 2, 3, 2, 4, 1, 4, "plant", "farming"));
        cardList.Add (new Card(1, "Simple_Axe", 4, 3, 3, 3, 3, 4, 3, 5, 2, 4, "tool", "none"));
        cardList.Add (new Card(2, "Feather", 3, 1, 3, 1, 4, 2, 3, 3, 5, 3, "animal", "farming"));
        cardList.Add (new Card(3, "Flaccid_Flower", 2, 1, 1, 1, 1, 3, 1, 3, 1, 2, "flower", "plant"));

    }
}
