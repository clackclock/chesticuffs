using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class cookieCard : MonoBehaviour
{
    public List<Card> thisCard = new List<Card>();
    public int thisID;

    public int id;
    public string cardName;
        // public Sprite thisSprite;
        // public Image thisImage;

    public int uberAtkATK;
    public int uberAtkDEF;
    public int attackATK;
    public int attackDEF;
    public int coredefATK;
    public int coredefDEF;
    public int coreATK;
    public int coreDEF;
    public int defenceATK;
    public int defenceDEF;

    public string typeONE;
    public string typeTWO;
    //public bool precStat;

    // Start is called before the first frame update
    void Start()
    {
       thisCard[0] = CardDatabase.cardList[thisID]; 
    }

    // Update is called once per frame
    void Update()
    {
        id = thisCard[0].id;
        cardName = thisCard[0].cardName;
        
        //thisSprite = thisCard[0].ThisImage;

        uberAtkATK = thisCard[0].uberAtkATK;
        uberAtkDEF = thisCard[0].uberAtkDEF;
        attackATK = thisCard[0].attackATK;
        attackDEF = thisCard[0].attackDEF;
        coredefATK = thisCard[0].coredefATK;
        coredefDEF = thisCard[0].coredefDEF;
        coreATK = thisCard[0].coreATK;
        coreDEF = thisCard[0].coreDEF;
        defenceATK = thisCard[0].defenceATK;
        defenceDEF = thisCard[0].defenceDEF;
        typeONE = thisCard[0].typeONE;
        typeTWO = thisCard[0].typeTWO;
        //precStat = thisCard[0].precStat;
    }
}
