using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class PlayerDeck : MonoBehaviour
{

    public List<Card> deck = new List<Card>();

    public int x;
    public int deckSize;

    // Start is called before the first frame update
    void Start()
    {
        x = 0;
        deckSize = 28;
        
        for (int i = 1; i < deckSize; i++){
            x = Random.Range(1,2);
            deck[i] = CardDatabase.cardList[x];

        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
